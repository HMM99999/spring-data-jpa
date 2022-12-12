package org.chapter02.controller;

import org.chapter02.bean.User;
import org.chapter02.bean.UserPagingAndSortingRepository;
import org.chapter02.bean.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @PostMapping(path = "/add")
    @ResponseBody
    public User addNewUser(@RequestParam String name, @RequestParam String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return user;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/info")
    @ResponseBody
    public User findOne(@RequestParam Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping(path = "delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path = "byPage")
    @ResponseBody
    public Page<User> getAllUserByPage() {
        return userPagingAndSortingRepository.findAll(PageRequest.of(1, 2,
                Sort.by(new Sort.Order(Sort.Direction.DESC, "name"))));
    }
}
