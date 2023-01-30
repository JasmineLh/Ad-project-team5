package sg.nus.iss.adprojectTeam5api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.adprojectTeam5api.Model.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer>{
    
}
