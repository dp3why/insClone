package dp3why.inst.DTO;

import lombok.*;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String userId;
    private String postId;
    private String postText;
    private String postPath;
    private Timestamp timestamp;
    private int likeCount;

}
