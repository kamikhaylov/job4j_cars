package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.common.UserSession;
import ru.job4j.cars.common.dto.PhotoDto;
import ru.job4j.cars.common.dto.PostDto;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.user.User;
import ru.job4j.cars.service.BrandService;
import ru.job4j.cars.service.CategoryService;
import ru.job4j.cars.service.ColorService;
import ru.job4j.cars.service.EngineService;
import ru.job4j.cars.service.PostService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final CategoryService categoryService;
    private final BrandService brandService;
    private final EngineService engineService;
    private final ColorService colorService;
    private final PostService postService;

    @GetMapping("/list")
    public String getPostList(Model model, HttpSession httpSession) {
        User user = UserSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAll());
        return "post/post";
    }

    @GetMapping("/my")
    public String getMyPostList(Model model, HttpSession httpSession) {
        User user = UserSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("posts", postService.getAll());
        return "post/post";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession httpSession) {
        User user = UserSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("brands", brandService.getAll());
        model.addAttribute("engines", engineService.getAll());
        model.addAttribute("colors", colorService.getAll());
        return "post/create";
    }

    @PostMapping("/create")
    public String create(
            @SessionAttribute User user,
            @ModelAttribute Car car,
            @RequestParam String description,
            @RequestParam Integer price,
            @RequestParam("file") MultipartFile file,
            @RequestParam Integer categoryId) throws IOException {
        postService.create(new PostDto(
                user, car, description, BigDecimal.valueOf(price),
                new PhotoDto(file.getOriginalFilename(), file.getBytes()),
                categoryId));
        return "redirect:/post/my";
    }

}
