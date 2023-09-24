package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.common.security.UserSession;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpSession;

/**
 * Контроллер списка объявлений
 */
@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostService postService;
    private final UserSession userSession;

    @GetMapping("/list")
    public String getPostList(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAllIsNotSold());
        return "post/post";
    }

    @GetMapping("/list/day")
    public String getPostListForDay(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAllForDay());
        return "post/post";
    }

    @GetMapping("/list/withMileage")
    public String getPostListWithMileage(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAllPostByCategoryName("С пробегом"));
        return "post/post";
    }

    @GetMapping("/list/new")
    public String getPostListNew(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.findAllPostByCategoryName("Новые"));
        return "post/post";
    }

    @GetMapping("/list/all")
    public String getPostListAll(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAll());
        return "post/post";
    }

    @GetMapping("/my")
    public String getMyPostList(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAllByUserId(user.getId()));
        return "post/my";
    }
}
