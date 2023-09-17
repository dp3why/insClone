package dp3why.inst.Service;

import dp3why.inst.DTO.LikeDTO;
import dp3why.inst.Entity.Likes;
import dp3why.inst.Entity.Post;
import dp3why.inst.Entity.Users;
import dp3why.inst.Repository.LikesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikesRepo likesRepo;
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public List<Likes> findAllLikes() {
        return likesRepo.findAll();
    }

    public List<Likes> findLikesByUser(Users user) {
        return likesRepo.findByUser(user);
    }
    public List<Likes> findLikesByPost(Post post) {
        return likesRepo.findByPost(post);
    }

    public List<Likes> findLikesByPostId(String postId) {
        return likesRepo.findByLikePostId(postId);
    }
    public List<Likes> findLikesByUserId(String userId) {

        return likesRepo.findByLikeUserId(userId);
    }

    public List<Users> findUsersByPostId(String postId) {
        List<Users> userList = new ArrayList<>();
        Post post = postService.findSinglePostByPostId(postId);
        if  (post == null) {
            return userList;
        }
        List<Likes> likes = likesRepo.findByLikePostId(postId);
        for (Likes like : likes) {
            String userId = like.getLikeUserId();
            userList.add(userService.getUserByUid(userId));
        }
        return userList;
    }

    public List<Post> findPostsByUserId(String userId) {
        List<Post> postList = new ArrayList<>();
        Users user = userService.getUserByUid(userId);
        if  (user == null) {
            return postList;
        }
        List<Likes> likes = likesRepo.findByLikeUserId(userId);
        for (Likes like : likes) {
            String postId = like.getLikePostId();
            postList.add(postService.findSinglePostByPostId(postId));
        }
        return postList;
    }

    public Likes findByLikePostIdAndLikeUserId(String postId, String userId) {
        if (postId == null || userId == null) {
            return null;
        }
        for (Likes likeInLikes : findLikesByPostId(postId)) {
            if (likeInLikes.getLikeUserId().equals(userId)) {
                return likeInLikes;
            }
        }
        return null;
    }


    public ResponseEntity<String> deleteLikeById(int id) {
        Likes like = likesRepo.findById(id);
        if (like == null) {
            return ResponseEntity.badRequest().body("Invalid request.");
        }
        likesRepo.deleteById(id);
        return ResponseEntity.ok("Like deleted successfully.");
    }

    // For DELETE: Delete a like
    public ResponseEntity<String> deleteLike(LikeDTO likeDTO) {
        // Fetch the like based on likeDTO.postId and likeDTO.userId
        String postId = likeDTO.getPostId();
        String userId = likeDTO.getUserId();
        Likes like = findByLikePostIdAndLikeUserId(postId, userId);
        if (like == null) {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found response if the like doesn't exist.
        }
        likesRepo.deleteById(like.getId());
        return ResponseEntity.ok("Like deleted successfully.");
    }


    // For POST: Like Post
    public Likes likePost(LikeDTO likeDTO) {
        // Fetch the post and user based on likeDTO.postId and likeDTO.userId
        String postId = likeDTO.getPostId();
        Post post = postService.findSinglePostByPostId(postId);
        Users user = userService.getUserMetaData(likeDTO.getUserId());

        if (post == null || user == null) {
            return null; // Return a 404 Not Found response if the post or user doesn't exist.
        }

        // Check if the user has already liked the post to prevent duplicate likes
        for (Users userInLikes : findUsersByPostId(postId)) {
            if (userInLikes.getUid().equals(user.getUid()) ) {
                return null;
            }
        }

        // Create a new like and associate it with the post and user
        Likes newLike = new Likes();
        newLike.setPost(post);
        newLike.setUser(user);
        newLike.setLikePostId(postId);
        newLike.setLikeUserId(user.getUid());
        newLike.setTimestamp(new Timestamp(System.currentTimeMillis()));
        likesRepo.save(newLike);
        // update post like count
        postService.updateLikeCount(post);

        return newLike;
    }
    // For post dislike post
    public Boolean dislikePost(LikeDTO likeDTO) {
        // Fetch the post and user based on likeDTO.postId and likeDTO.userId
        String postId = likeDTO.getPostId();
        Post post = postService.findSinglePostByPostId(postId);
        Users user = userService.getUserMetaData(likeDTO.getUserId());

        if (post == null || user == null) {
            return false; // Return a 404 Not Found response if the post or user doesn't exist.
        }

        // Check if the user has already liked the post to prevent duplicate likes
        Likes like = findByLikePostIdAndLikeUserId(postId, user.getUid());
        if (like == null) {
            return false;
        }
        likesRepo.deleteById(like.getId());
        // update post like count
        postService.updateDislikeCount(post);
        return true;
    }

}
