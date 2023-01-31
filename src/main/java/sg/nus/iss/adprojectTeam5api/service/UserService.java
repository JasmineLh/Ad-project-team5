package sg.nus.iss.adprojectTeam5api.service;

import sg.nus.iss.adprojectTeam5api.Model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();
    public User findUserById(int id);


    public boolean saveAccount(User user);
    public User check(String name, String password);

    public void updateResetPasswordToken(String token,String email) throws UserNotFoundException;



    public void updatePassword(User user,String newPassword);



    public User getByResetPasswordToken(String token);
}
