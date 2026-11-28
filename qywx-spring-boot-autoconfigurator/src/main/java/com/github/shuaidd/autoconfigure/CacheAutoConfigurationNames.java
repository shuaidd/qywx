package com.github.shuaidd.autoconfigure;

/**
 * 自动配置排序用的 {@code CacheAutoConfiguration} 类名。按名字（{@code @AutoConfiguration(afterName = ...)}）而不是 class 声明：
 * Boot 4 把 {@code CacheAutoConfiguration} 搬进了 {@code spring-boot-cache} 模块、换了包名，而 README 承诺的 Boot 3.x 用户
 * 仍靠旧包名拿到「缓存 starter 先登记」的顺序；用 class 字面量引用会让 Boot 3 用户丢掉排序保证，也会在模块缺失时留下
 * {@code TypeNotPresentException} 隐患。单独放一个类而不是写在 {@link WeChatAutoConfiguration} 里，是因为 javac 不接受
 * 类注解里用简单名引用本类后声明的常量。
 */
final class CacheAutoConfigurationNames {

    /** Boot 4 起（spring-boot-cache 模块）的包名 */
    static final String BOOT_4 = "org.springframework.boot.cache.autoconfigure.CacheAutoConfiguration";

    /** Boot 3 及更早版本的包名 */
    static final String LEGACY = "org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration";

    private CacheAutoConfigurationNames() {
    }
}
