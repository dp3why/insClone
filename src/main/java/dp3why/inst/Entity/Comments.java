package dp3why.inst.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity(name = "Comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comments {
    @Id
    @GeneratedValue
    private int id;
    private String commentId;
    private String userId;
    private String userName;

    private String postId;
    private Timestamp timestamp;
    private String comment;
}
