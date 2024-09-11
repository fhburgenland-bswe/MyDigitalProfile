package mydigitalprofile.repository;

import mydigitalprofile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmployeeNumber(String employeeNumber);
}