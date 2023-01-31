package sg.nus.iss.adprojectTeam5api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.nus.iss.adprojectTeam5api.Model.RoleEnum;
import sg.nus.iss.adprojectTeam5api.Model.User;
import sg.nus.iss.adprojectTeam5api.Model.UserSession;
import sg.nus.iss.adprojectTeam5api.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {
	@Autowired
	private UserService userservice;
	
	@GetMapping("/login")
	public String Login(Model model)
	{
		User user = new User();
		model.addAttribute("user",user);
		return "loginNew1";
		
	}
	 @GetMapping("/app")
	  public String home() {
	    return "home";
	  }
	
	
	@PostMapping("/login")
	public String autheticate(HttpServletRequest request, Model model, HttpSession session)
	{
		
		String check = request.getParameter("check");
		String password = request.getParameter("password");
		User u = userservice.check(check,password);
		if(u==null)
		{
			model.addAttribute("message","Incorrect email/password");
			return "loginNew1";
		}
		{
			UserSession usersession= new UserSession();
			usersession.setUser(u);
			session.setAttribute("userSession", usersession);
			
				if(u.getRole() ==RoleEnum.ADMIN)
				{
					return "newPage";
				}
				else
				{
					return "movie";
				}
			
		}
		
		
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:/app";
	}
}
