package sg.nus.iss.adprojectTeam5api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.adprojectTeam5api.Model.Movie;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    
}
