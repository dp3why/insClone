package dp3why.inst.Entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

@Entity(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {

	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	private String uid;
	private String userName;
	private String name;
	private String profileImage;

}
