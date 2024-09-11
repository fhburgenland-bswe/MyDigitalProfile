// UserController.java
package mydigitalprofile.controller;

import mydigitalprofile.model.User;
import mydigitalprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.getUserDetails(userDetails.getUsername());
        model.addAttribute("user", user);
        return "home";
    }
}