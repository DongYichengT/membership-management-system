package dongyicheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 标记该类为一个 Spring Boot 应用程序主类，@SpringBootApplication 是一个组合注解，
// 它包含了 @Configuration、@EnableAutoConfiguration 和 @ComponentScan 等注解，
// 用于启用 Spring Boot 的自动配置、组件扫描等功能，从而简化 Spring 应用的开发。
@SpringBootApplication
public class UserApplication {
    // 程序的入口方法，Java 应用程序从这里开始执行。
    // 当应用程序启动时，SpringApplication 会根据传递的参数来运行 UserApplication 类。
    // args 参数是一个字符串数组，通常用于接收命令行传入的参数。
    public static void main(String[] args) {
        // 启动 Spring Boot 应用程序，run 方法会创建一个 Spring 应用上下文，
        // 并加载所有的配置和组件，使应用程序能够正常运行。
        // 第一个参数 UserApplication.class 告诉 SpringApplication 要加载的主配置类，
        // 第二个参数 args 则是传递给应用程序的命令行参数。
        SpringApplication.run(UserApplication.class, args);
    }
}