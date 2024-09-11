package mydigitalprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "mydigitalprofile.repository")
public class MyDigitalProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyDigitalProfileApplication.class, args);
    }
}