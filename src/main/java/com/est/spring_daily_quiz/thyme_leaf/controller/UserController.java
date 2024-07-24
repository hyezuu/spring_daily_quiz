package com.est.spring_daily_quiz.thyme_leaf.controller;

import com.est.spring_daily_quiz.thyme_leaf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserController {
    @GetMapping("/admin")
    public String adminInfo(Model model) {
        User admin = new User("강혜주", "hyezuu@Kang", true, "asdasd");
        model.addAttribute("user", admin);
        return "userInfo";
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        User user = new User("강혜주", "hyezuu@Kang", false, "asdasd");
        model.addAttribute("user", user);
        return "userInfo";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping
    public String postUsers(@ModelAttribute("user") User user) {
        user.setAdmin(false);
        System.out.println("유저의 이름은 " + user.getUserName() + "입니다");
        System.out.println("유저의 이메일은 " + user.getEmail() + "입니다");
        System.out.println("유저의 비밀번호는 " + user.getPassword() +"입니다");
        return "userInfo";
    }
}
