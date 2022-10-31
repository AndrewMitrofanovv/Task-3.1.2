package com.spring.example.web.controller;

import com.spring.example.web.model.User;
import com.spring.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView("all-users");

        List<User> userList = userService.getAllUsers();
        modelAndView.addObject("userList", userList);

        return modelAndView;
    }

    @RequestMapping("/add-user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new-user-form";
    }


    @RequestMapping("/form-user")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userUpdate", userService.findUser(id));
        return "edit-user-form";
    }

    @RequestMapping("/update/{id}")
    public String saveEditedUser(@ModelAttribute("userUpdate") User userUpdate) {
        userService.update(userUpdate);
        return "redirect:/";
    }

    @RequestMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }


}
