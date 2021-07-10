package ru.aseev.jm231.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aseev.jm231.dao.UserDAOImpl;
import ru.aseev.jm231.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAOImpl userDAOImpl;

    @Autowired
    public UsersController(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userDAOImpl.allMyUsers());
        return "users/allUsers";
    }
//
    @GetMapping("/{id}")
    public String getUniqueUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userDAOImpl.uniqueMyUser(id));
        return "users/uniqueUser";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        } else {
            userDAOImpl.saveNewUser(user);
            return "redirect:/users";
        }
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDAOImpl.uniqueMyUser(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        } else {
            userDAOImpl.changeUser(id, user);
            return "redirect:/users";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userDAOImpl.dropUser(id);
        return "redirect:/users";
    }
}
