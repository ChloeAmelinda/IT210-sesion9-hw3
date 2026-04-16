package org.example.hw3.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ThemeController {
    @PostMapping("/change-theme")
    public String changeTheme(
            @RequestParam("theme") String theme,
            HttpServletResponse response
    ) {

        Cookie cookie = new Cookie("theme", theme);

        cookie.setMaxAge(60 * 60 * 24 * 30);

        cookie.setHttpOnly(true);

        cookie.setPath("/");

        response.addCookie(cookie);

        return "redirect:/";
    }

    @GetMapping("/")
    public String home(
            HttpServletRequest request,
            Model model
    ) {

        String theme = "light"; // mặc định
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("theme".equals(c.getName())) {
                    theme = c.getValue();
                }
            }
        }

        model.addAttribute("theme", theme);

        return "home";
    }
}