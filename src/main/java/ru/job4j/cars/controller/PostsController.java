package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.common.security.UserSession;
import ru.job4j.cars.service.PostsService;

import javax.servlet.http.HttpSession;

/**
 * Контроллер списка объявлений
 */
@Controller
@AllArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;
    private final UserSession userSession;

    @GetMapping("/list")
    public String getPostList(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postsService.getAllIsNotSold());
        return "post/post";
    }

    @GetMapping("/list/day")
    public String getPostListForDay(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postsService.getAllForDay());
        return "post/post";
    }

    @GetMapping("/list/withMileage")
    public String getPostListWithMileage(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postsService.findAllPostByCategoryName("С пробегом"));
        return "post/post";
    }

    @GetMapping("/list/new")
    public String getPostListNew(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postsService.findAllPostByCategoryName("Новые"));
        return "post/post";
    }

    @GetMapping("/list/all")
    public String getPostListAll(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postsService.getAll());
        return "post/post";
    }

    @GetMapping("/my")
    public String getMyPostList(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postsService.getAllByUserId(user.getId()));
        return "post/my";
    }
}
