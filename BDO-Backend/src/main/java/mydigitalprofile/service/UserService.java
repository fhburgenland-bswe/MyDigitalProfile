// UserService.java
package mydigitalprofile.service;

import mydigitalprofile.model.User;
import org.springframework.stereotype.Service;
import java.util.Date;
@Service
public class UserService {

    /**
     * Retrieves user details for the given username.
     * @param username the username of the user
     * @return the user details
     */
    public User getUserDetails(String username) {
        // Mock user data for demonstration purposes
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setAddress("123 Main St");
        user.setEmployeeNumber("EMP12345");
        user.setBirthDate(new Date());
        user.setLocation("New York");
        user.setState("NY");
        user.setCareerLevel("Senior Developer");
        user.setSkills("Java, Spring Boot");
        user.setTeam("Development");
        return user;
    }
}