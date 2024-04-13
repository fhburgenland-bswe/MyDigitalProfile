package mydigitalprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Application class serves as the entry point for the Spring Boot application.
 *
 * @author Khaled Alnahhas
 */
@SpringBootApplication
public class MyDigitalProfileApplication {

    /**
     * The main method which will be executed when the application starts.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MyDigitalProfileApplication.class, args);
    }

}