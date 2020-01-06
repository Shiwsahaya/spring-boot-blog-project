package net.blog.post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BlogApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class, args);
    }
}
