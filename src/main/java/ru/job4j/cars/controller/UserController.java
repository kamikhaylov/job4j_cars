package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.cars.common.security.UserSession;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * Контроллер пользователей
 */
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserSession userSession;

    @GetMapping("/registration")
    public String registration(Model model, HttpSession httpSession,
                               @RequestParam(name = "fail", required = false) Boolean fail,
                               @RequestParam(name = "success", required = false) Boolean success) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("fail", nonNull(fail));
        model.addAttribute("success", nonNull(success));
        return "user/registration";
    }

    @PostMapping("/create")
    public String create(Model model, @ModelAttribute User user) {
        Optional<User> result = userService.findByLogin(user);
        if (result.isEmpty()) {
            userService.create(user);
            return "redirect:/user/registration?success=true";
        }
        model.addAttribute("message", "");
        return "redirect:/user/registration?fail=true";
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
    public String authorization(Model model, HttpSession httpSession,
                       @RequestParam(name = "fail", required = false) Boolean fail) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("fail", nonNull(fail));
        return "user/authorization";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> result = userService.findByLoginAndPassword(user);
        if (result.isEmpty()) {
            return "redirect:/user/authorization?fail=true";
        }
        userSession.create(result.get(), req);
        return "redirect:/post/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        userSession.invalidate(session);
        return "redirect:/user/authorization";
    }
}
