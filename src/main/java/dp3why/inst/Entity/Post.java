package dp3why.inst.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "Post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String postId;
    private String postText;

    @Column(updatable = false)
    private String userId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "uid")
    private Users user;

    private String postPath;
    private Timestamp timestamp;
    private int likeCount;
}
