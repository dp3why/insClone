package dp3why.inst.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.sql.Timestamp;
@Entity(name = "Post")
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String postId;
    private String userId;

    private String userName;

    private String postPath;
    private Timestamp timestamp;

    private String likeCount;

    public Post() {
        super();
    }

    public Post(int id, String postId, String userId, String postPath, Timestamp timestamp, String likeCount) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.postPath = postPath;
        this.timestamp = timestamp;
        this.likeCount = likeCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostPath() {
        return postPath;
    }

    public void setPostPath(String postPath) {
        this.postPath = postPath;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }
}
