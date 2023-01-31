package sg.nus.iss.adprojectTeam5api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sg.nus.iss.adprojectTeam5api.Model.RoleEnum;
import sg.nus.iss.adprojectTeam5api.Model.User;
import sg.nus.iss.adprojectTeam5api.Repository.UserRepository;

@SpringBootApplication
public class AdprojectTeam5apiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdprojectTeam5apiApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(UserRepository userRepository

    ) {
        return (args) -> {
            User ei= userRepository.saveAndFlush(new User(1,"EiNgon","password",true,RoleEnum.ADMIN,"eingonpwint13254@gmail.com",null,null));

            userRepository.saveAndFlush(ei);
        };

    }
}
