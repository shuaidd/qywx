package com.github.shuaidd;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 core 的「零 Spring」静态守卫：依赖、源码、资源三处都不允许出现 Spring
 * <p>
 * {@link PlainJavaSmoke#assertNoSpringOnClasspath()} 走 {@code java.class.path}，surefire 下它是 booter jar，只能在手工运行时生效；
 * 这里改用类加载器探测与源码树扫描，保证 {@code mvn test} 每次都能拦住三类回归：
 * <ol>
 *     <li>pom 里（含传递）混入 Spring 构件：探测一组代表性类名，必须全部 {@code ClassNotFoundException}；</li>
 *     <li>{@code src/main/java} 里出现 {@code org.springframework} 引用：逐文件全文匹配，连注释都算；</li>
 *     <li>{@code src/main/resources} 里出现 Spring 装配描述文件（{@code META-INF/spring.factories}、
 *     {@code META-INF/spring/*.imports}）。</li>
 * </ol>
 * 模块目录靠 surefire 注入的 {@code basedir} 定位；IDE 直接运行时回退到 {@code user.dir}（模块目录或仓库根均可）。
 *
 * @author ddshuai
 * date 2026-11-28 10:30
 **/
class NoSpringGuardTest {

    private static final String SPRING_PACKAGE = "org.springframework";

    /** 5.x 用过的、以及一旦有人把 Spring 拉回来最先会用到的类型；逐个探测，比按 jar 名猜更贴近实际 */
    private static final List<String> SPRING_TYPES = Collections.unmodifiableList(Arrays.asList(
            "org.springframework.context.ApplicationContext",
            "org.springframework.context.annotation.Configuration",
            "org.springframework.beans.factory.annotation.Autowired",
            "org.springframework.stereotype.Service",
            "org.springframework.cache.CacheManager",
            "org.springframework.web.multipart.MultipartFile",
            "org.springframework.boot.context.properties.ConfigurationProperties",
            "org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean",
            "org.springframework.cloud.openfeign.FeignClient"));

    @Test
    void springTypesAreNotReachableFromClasspath() {
        List<String> found = new ArrayList<String>();
        ClassLoader loader = NoSpringGuardTest.class.getClassLoader();
        for (String type : SPRING_TYPES) {
            try {
                Class.forName(type, false, loader);
            } catch (ClassNotFoundException expected) {
                continue;
            }
            found.add(type + " <- " + sourceOf(loader, type));
        }
        assertTrue(found.isEmpty(), "core 的测试 classpath 上出现了 Spring 类型（直接或传递依赖混入）：" + found);
    }

    @Test
    void sourceTreeNeverMentionsSpring() throws IOException {
        Path dir = moduleDir();
        Path root = dir.resolve("src/main/java");
        assertTrue(Files.isDirectory(root), "源码目录不存在：" + root);
        List<String> hits = new ArrayList<String>();
        try (Stream<Path> files = Files.walk(root)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".java")).collect(Collectors.toList())) {
                List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).contains(SPRING_PACKAGE)) {
                        hits.add(rel(dir, file) + ":" + (i + 1));
                    }
                }
            }
        }
        assertTrue(hits.isEmpty(), "core 源码里出现了 " + SPRING_PACKAGE + " 引用：" + hits);
    }

    @Test
    void resourcesCarryNoSpringWiring() throws IOException {
        Path dir = moduleDir();
        Path root = dir.resolve("src/main/resources");
        List<String> hits = new ArrayList<String>();
        if (Files.isDirectory(root)) {
            try (Stream<Path> files = Files.walk(root)) {
                for (Path file : files.filter(Files::isRegularFile).collect(Collectors.toList())) {
                    String rel = rel(dir, file);
                    if (rel.equals("META-INF/spring.factories") || rel.startsWith("META-INF/spring/")) {
                        hits.add(rel);
                    }
                }
            }
        }
        assertTrue(hits.isEmpty(), "core 资源里出现了 Spring 装配描述文件：" + hits);
    }

    @Test
    void pomDeclaresNoSpringArtifact() throws IOException {
        Path dir = moduleDir();
        Path pom = dir.resolve("pom.xml");
        assertTrue(Files.isRegularFile(pom), "找不到模块 pom：" + pom);
        List<String> lines = Files.readAllLines(pom, StandardCharsets.UTF_8);
        List<String> hits = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("<groupId>" + SPRING_PACKAGE + "</groupId>")
                    || line.contains("<artifactId>spring-boot-starter")
                    || line.contains("<artifactId>spring-cloud-")) {
                hits.add((i + 1) + ": " + line.trim());
            }
        }
        assertTrue(hits.isEmpty(), "core 的 pom 里声明了 Spring 构件：" + hits);
    }

    private static String sourceOf(ClassLoader loader, String type) {
        URL url = loader.getResource(type.replace('.', '/') + ".class");
        return url == null ? "?" : url.toString();
    }

    /**
     * 定位 qywx-core 模块目录：surefire 注入的 basedir 优先，其次当前工作目录，再次仓库根下的 qywx-core
     */
    private static Path moduleDir() {
        Path userDir = Paths.get(System.getProperty("user.dir")).toAbsolutePath();
        String basedir = System.getProperty("basedir");
        List<Path> candidates = new ArrayList<Path>();
        if (basedir != null) {
            candidates.add(Paths.get(basedir));
        }
        candidates.add(userDir);
        candidates.add(userDir.resolve("qywx-core"));
        for (Path candidate : candidates) {
            Path dir = candidate.normalize();
            if (Files.isDirectory(dir.resolve("src/main/java")) && Files.isRegularFile(dir.resolve("pom.xml"))) {
                return dir;
            }
        }
        throw new IllegalStateException("找不到 qywx-core 模块目录（basedir=" + basedir + "，user.dir=" + userDir + "）");
    }

    private static String rel(Path dir, Path file) {
        return dir.relativize(file).toString().replace(File.separatorChar, '/');
    }
}
