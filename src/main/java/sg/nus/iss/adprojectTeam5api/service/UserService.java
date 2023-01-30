package sg.nus.iss.adprojectTeam5api.service;

import sg.nus.iss.adprojectTeam5api.Model.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();
    public User findUserById(int id);
}
