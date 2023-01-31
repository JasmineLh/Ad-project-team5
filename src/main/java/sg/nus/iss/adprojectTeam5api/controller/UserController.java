package sg.nus.iss.adprojectTeam5api.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController 
{
	@Autowired
	private UserService userservice;
	
	@Autowired
	public void setUserService(UserServiceImp userserviceImp)
	{
		this.userservice=userserviceImp;
	}
	
	
	
	@GetMapping("/add")
	public String Form(Model model)
	{
		User user = new User();
		model.addAttribute("user",user);
		return "registerform";
	}
	
	@PostMapping("/add")
	public String saveAccount(@Valid @ModelAttribute("user") User user,BindingResult bindingResult,Model model)
	{
		if(bindingResult.hasErrors())
		{
			return "registerform";
					
		}
		userservice.saveAccount(user);
		return "redirect:/list";

	}
	
	@RequestMapping(value="/list")
	public String list(Model model)
	{
		model.addAttribute("users",userservice.findAllUsers());
		return "hello";
	}
	
	
	
	
	
}
