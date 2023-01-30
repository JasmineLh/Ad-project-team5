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
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
    @Transactional
    @Override
    public User findUserById(int id){
        return  userRepository.findById(id).orElse(null);
    }
}
