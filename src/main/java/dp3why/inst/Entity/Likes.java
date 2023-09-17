package dp3why.inst.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Likes {
    @Id
    @GeneratedValue
    private int id;

    private String likeUserId;
    private String likePostId;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "uid")
    private Users user;

    private Timestamp timestamp;
}
