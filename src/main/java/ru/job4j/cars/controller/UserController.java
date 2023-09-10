package ru.job4j.cars.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.common.UserSession;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "user/registration";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute User user) {
        Optional<User> result = userService.findByLogin(user);
        if (result.isEmpty()) {
            userService.create(user);
            return "redirect:/user/success";
        }
        model.addAttribute("message", "");
        return "redirect:/user/fail";
    }

    @GetMapping("/success")
    public String success(@ModelAttribute User user) {
        return "user/success";
    }

    @GetMapping("/fail")
    public String fail(@ModelAttribute User user) {
        return "user/fail";
    }

    @GetMapping("/authorization")
    public String auth(Model model,
                       @RequestParam(name = "fail", required = false) Boolean fail) {
        model.addAttribute("fail", nonNull(fail));
        return "user/authorization";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> result = userService.findByLogin(user);
        if (result.isEmpty()) {
            return "redirect:/user/authorization?fail=true";
        }
        UserSession.create(result.get(), req);
        return "redirect:/post/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        UserSession.invalidate(session);
        return "redirect:/user/authorization";
    }
}
