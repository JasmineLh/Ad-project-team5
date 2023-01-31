package sg.nus.iss.adprojectTeam5api.validator;

import com.example.demo.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		String pass = user.getPassword();
		String confirmPass = user.getConfirmPassword();
		if(!pass.equalsIgnoreCase(confirmPass))
		{
			errors.rejectValue("password", "error.password", "Not match");
		}
	}

}
