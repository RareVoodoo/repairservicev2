package test.repairservice.repairservicev2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import test.repairservice.repairservicev2.dto.request.AuthRequest;
import test.repairservice.repairservicev2.model.User;
import test.repairservice.repairservicev2.service.BaseAuthService;
import test.repairservice.repairservicev2.service.impl.AuthService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private BaseAuthService authService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login-enter")
    public void login(@ModelAttribute(name = "user") User user, HttpServletRequest request){
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(user.getUsername());
        authRequest.setPassword(user.getPassword());
        authService.login(authRequest);
    }

    @GetMapping("/registration")
    public String loadRegistrationView(){
        return "registration";
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerNewUser( User user){
        authService.registerUser(user);
        return "redirect:login";
    }
}
