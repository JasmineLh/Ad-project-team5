package sg.nus.iss.adprojectTeam5api.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sg.nus.iss.adprojectTeam5api.service.UserService;
import sg.nus.iss.adprojectTeam5api.service.UserServiceImpl;
import sg.nus.iss.adprojectTeam5api.validator.UserValidator;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
public class ForgetPasswordController {
	@Autowired
	private UserValidator userVal;
	
	@Autowired
	private UserService userService;
	@Autowired
	public void setUserService(UserServiceImpl userServiceImp)
	{
		this.userService=userServiceImp;
	}
	@Autowired
	private JavaMailSender mailSender;
	  
	  @InitBinder
	  private void initCustomerBinder(WebDataBinder binder) {
	    binder.addValidators(userVal);
	  }

	
	@GetMapping("/forget_password")
	public String showForgetPasswordForm(Model model)
	{
		model.addAttribute("pageTitle","Forget Password");
		return "forget_password_form";
	}
	@PostMapping("/forget_password")
	public String processForgetPasswordForm(HttpServletRequest request,Model model)
	{

		String email = request.getParameter("email");
		String token = UUID.randomUUID().toString();
		try {
			userService.updateResetPasswordToken(token,email);
			
			//generate reset password link
			
			String resetPasswordLink =Utility.getSiteURL(request) +"/reset_password?token="+token;
			//System.out.println("-----");
			System.out.println("test"+resetPasswordLink);
			//send email
			sendEmail(email,resetPasswordLink);
			
		} catch (UserNotFoundException ex) {
		
			model.addAttribute("message",ex.getMessage());
			return "forget_password_form";
			
		} catch (UnsupportedEncodingException  | MessagingException e) 
		{
			 model.addAttribute("error", "Error while sending email");
			 return "forget_password_form";
		}
		
		return "home";
		
		
	}
	public void sendEmail(String email, String resetPasswordLink) 
			throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("contact@gmail.com","Movie website");
		helper.setTo(email);
		String subject ="Here is the link to reset your password";
		
		String content = "<p>Hello,</p>"
	            + "<p>You have requested to reset your password.</p>"
	            + "<p>Click the link below to change your password:</p>"
	            + "<p><a href=\"" + resetPasswordLink+ "\">Change my password</a></p>"
	            + "<br>"
	            + "<p>Ignore this email if you do remember your password, "
	            + "or you have not made the request.</p>";
		
		helper.setSubject(subject);
		helper.setText(content,true);
		mailSender.send(message);
		
	}
	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value="token") String token,Model model)
	{
		User u = userService.getByResetPasswordToken(token);
		User u1 = new User();
		if(u == null)
		{
			//model.addAttribute("message","Invalid token");
			return "message";
		}
		model.addAttribute("token",token);
		model.addAttribute("user",u1);
		return "reset_password_form";
		
	}
	@PostMapping("/reset_password")
	public String processPassword(@Valid @ModelAttribute ("user") User user,BindingResult bindingResult,@Param(value="token") String token,Model model)
	{
	    if (bindingResult.hasErrors()) {
	        return "reset_password_form";
	      }
	    
	      
	   
	    String password = user.getPassword();
	   // System.out.println(token);
		//String token= request.getParameter("token");
		//String password= request.getParameter("password");
		
		User u = userService.getByResetPasswordToken(token);
		if(u == null)
		{
			model.addAttribute("message","Invalid token");
			return "home";
		}
		else
		{
			userService.updatePassword(u, password);
			model.addAttribute("message","You have successfully changed your password.");
		}
		return "home";
	}
		
}
