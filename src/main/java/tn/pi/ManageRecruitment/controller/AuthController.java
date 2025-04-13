package tn.pi.ManageRecruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.pi.ManageRecruitment.model.User;
import tn.pi.ManageRecruitment.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String password,
                         @RequestParam String role) throws Exception {
        return authService.register(name, email, password, role);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email,
                      @RequestParam String password) throws Exception {
        return authService.login(email, password);
    }
}