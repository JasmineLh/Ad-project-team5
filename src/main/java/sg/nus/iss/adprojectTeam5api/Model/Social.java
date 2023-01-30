package sg.nus.iss.adprojectTeam5api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Social {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socialid")
    private int social_id;

    @Column(name="userid")
    private int user_id;

    @Column(name="followerid")
    private int follower_id;
  
}
