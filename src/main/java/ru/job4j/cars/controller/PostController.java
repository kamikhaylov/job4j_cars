package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.common.UserSession;
import ru.job4j.cars.common.model.user.User;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    @GetMapping("/list")
    public String getPostList(Model model, HttpSession httpSession) {
        User user = UserSession.getUser(model, httpSession);
        model.addAttribute("user", user);

        return "post/post";
    }

    @GetMapping("/my")
    public String getMyPostList(Model model, HttpSession httpSession) {
        User user = UserSession.getUser(model, httpSession);
        model.addAttribute("user", user);

        return "post/my";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession httpSession) {
        User user = UserSession.getUser(model, httpSession);
        model.addAttribute("user", user);

        return "post/create";
    }

}
