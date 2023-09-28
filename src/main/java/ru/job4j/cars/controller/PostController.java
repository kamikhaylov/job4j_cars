package ru.job4j.cars.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cars.common.security.UserSession;
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

/**
 * Контроллер объявлений
 */
@Controller
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final CategoryService categoryService;
    private final BrandService brandService;
    private final EngineService engineService;
    private final ColorService colorService;
    private final PostService postService;
    private final UserSession userSession;

    @GetMapping("/details/{id}")
    public String getDetails(Model model, HttpSession httpSession, @PathVariable("id") int id) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("post", postService.getById(id, user.getId()));
        return "post/details";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession httpSession) {
        User user = userSession.getUser(model, httpSession);
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
            @RequestParam String text,
            @RequestParam Integer price,
            @RequestParam("file") MultipartFile file,
            @RequestParam Integer categoryId) throws IOException {
        postService.create(new PostDto(
                user, car, text, BigDecimal.valueOf(price),
                new PhotoDto(file.getOriginalFilename(), file.getBytes()),
                categoryId));
        return "redirect:/posts/my";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, HttpSession httpSession, @PathVariable("id") int id) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        model.addAttribute("post", postService.getById(id, user.getId()));
        return "post/update";
    }

    @PostMapping("/update")
    public String update(
            Model model,
            HttpSession httpSession,
            @RequestParam Integer id,
            @RequestParam String text,
            @RequestParam Integer price) throws IOException {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        postService.update(id, text, BigDecimal.valueOf(price));
        return "redirect:/post/details/" + id;
    }

    @GetMapping("/isSold/{id}")
    public String isSold(
            Model model,
            HttpSession httpSession,
            @PathVariable("id") int id) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        postService.updateIsSold(id);
        return "redirect:/post/details/" + id;
    }

    @GetMapping("/isNotSold/{id}")
    public String isNotSold(
            Model model,
            HttpSession httpSession,
            @PathVariable("id") int id) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        postService.updateIsNotSold(id);
        return "redirect:/post/details/" + id;
    }

    @GetMapping("/delete/{id}")
    public String delete(
            Model model,
            HttpSession httpSession,
            @PathVariable("id") int id) {
        User user = userSession.getUser(model, httpSession);
        model.addAttribute("user", user);
        postService.delete(id);
        return "redirect:/posts/my/";
    }
}
