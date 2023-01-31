package sg.nus.iss.adprojectTeam5api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.iss.adprojectTeam5api.Model.User;

public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("Select u From User u where (u.Name =:name Or u.email=:name) And u.password=:password")
    User findUserByEmailPwd(@Param("name")String name, @Param("password")String pwd);

    @Query("Select u From User u where u.email = ?1")
    User findByEmail(String email);

    User findByResetPasswordToken(String token);


}
