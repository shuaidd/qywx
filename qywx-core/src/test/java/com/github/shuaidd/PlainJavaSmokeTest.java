package com.github.shuaidd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * 描述 纯 Java 冒烟的 JUnit 入口：在 surefire 里跑一遍与 {@code java -cp} 相同的场景
 * <p>
 * 「classpath 上没有 Spring」的断言只在 {@link PlainJavaSmoke#main} 里做——surefire 的 {@code java.class.path} 是它的
 * booter jar，看不到真实 classpath；完整冒烟（含该断言）按 {@link PlainJavaSmoke} 类注释里的命令手工运行。
 *
 * @author ddshuai
 * date 2026-11-26 07:06
 **/
public class PlainJavaSmokeTest {

    /**
     * 零 Spring 的 core 在进程内假服务上完成取号、缓存、注入、令牌失效重试与二次失败抛出
     */
    @Test
    public void plainJavaSmokePasses() {
        assertDoesNotThrow(PlainJavaSmoke::run);
    }
}
