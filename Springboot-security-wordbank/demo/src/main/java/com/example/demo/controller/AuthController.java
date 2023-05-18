package com.example.demo.controller;

import com.example.demo.dto.request.SignInForm;
import com.example.demo.dto.request.SignUpForm;
import com.example.demo.dto.response.ResponseMessage;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.model.RoleName;

import com.example.demo.security.userPrincipal.UserPrinciple;
import com.example.demo.service.impl.RoleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) boolean error) {

        if (error) {
            model.addAttribute("message", "Login Fail!!!");
        }
        return "login";
    }


    @RequestMapping("/scrapingForm")
    public String homepage(Model model, HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        Optional<UserEntity> user = userService.findByEmail(username);

        model.addAttribute("bankCount", userService.bankCount(user.get()));
        model.addAttribute("user", user.get());
        session.setAttribute("bankCount",userService.bankCount(user.get()));

        return "scraper";
    }
    @RequestMapping("/reviewForm")
    public String review(Model model, HttpSession session){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        Optional<UserEntity> user = userService.findByEmail(username);
        model.addAttribute("listToReview", userService.listToReview(user.get()));
        model.addAttribute("bankCount", userService.bankCount(user.get()));
        model.addAttribute("user", user.get());
        session.setAttribute("bankCount",userService.bankCount(user.get()));
        session.setAttribute("progress",userService.getProgress(user.get()));
        return "review";
    }



}
