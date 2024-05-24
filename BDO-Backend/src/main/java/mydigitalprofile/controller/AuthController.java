package mydigitalprofile.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import mydigitalprofile.model.dto.LoginDto;

@RestController
public class AuthController {

    @Operation(summary = "Login", description = "User login endpoint")
    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto) {
        // This method is just for Swagger documentation
    }


    @Operation(summary = "Logout", description = "User logout endpoint")
    @PostMapping("/logout")
    public void logout() {
        // This method is just for Swagger documentation
    }

}

