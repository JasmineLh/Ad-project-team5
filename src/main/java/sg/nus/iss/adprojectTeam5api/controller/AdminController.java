package sg.nus.iss.adprojectTeam5api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.nus.iss.adprojectTeam5api.Model.User;
import sg.nus.iss.adprojectTeam5api.Repository.UserRepository;
import sg.nus.iss.adprojectTeam5api.service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/userList")
    public String manageUser(Model model) {

        List<User> userList = userService.findAllUser();
        model.addAttribute("users", userList);
        return "user-list";
    }


    @RequestMapping(value = "/banUser", method = RequestMethod.GET)
    public String banUser( int userId, Model model) {
        User user = userService.findUserById(userId);
        user.setIsActive(false);
        userRepository.saveAndFlush(user);
        return "redirect:/admin/userList";

    }




}
