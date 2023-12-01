package ru.magpie.KATAMVCWithBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.magpie.KATAMVCWithBoot.Service.UserService;
import ru.magpie.KATAMVCWithBoot.model.User;


import javax.validation.Valid;


@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/list")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "list";
    }

    @GetMapping("/")
    public String showUserById(@RequestParam("id") long id, ModelMap model) {
        model.addAttribute("user", userService.readUser(id));
        return "showById";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.readUser(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "/edit";

        userService.updateUser(user, id);
        return "redirect:/users/list";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUserById(id);
        return "redirect:/users/list";
    }
}