package com.app.auth.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private boolean isAuthenticated() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
            return false;
        if (AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass()))
            return false;
        return auth.isAuthenticated();
    }

    @GetMapping(value={"/home", "/"})
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        if (isAuthenticated())
            return "redirect:secure";
        return "login";
    }

    @GetMapping("/secure")
    public String secure() {
        return "secure";
    }
}
