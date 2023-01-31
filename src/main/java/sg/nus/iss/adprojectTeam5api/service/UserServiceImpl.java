package sg.nus.iss.adprojectTeam5api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.nus.iss.adprojectTeam5api.Model.User;
import sg.nus.iss.adprojectTeam5api.Repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean saveAccount(User user) {
        // TODO Auto-generated method stub
        if (userRepository.save(user) != null)
            return true;
        else
            return false;
    }

    @Override
    public User check(String name, String pwd) {
        // TODO Auto-generated method stub
        return userRepository.findUserByEmailPwd(name, pwd);
    }

    @Override
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException("Could not find any user with the email" + email);
        }

    }


    @Override
    public void updatePassword(User user, String newPassword) {
        // TODO Auto-generated method stub
        user.setPassword(newPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        // TODO Auto-generated method stub
        return userRepository.findByResetPasswordToken(token);
    }
}
