package dp3why.inst.Service;

import dp3why.inst.DTO.PostDTO;
import dp3why.inst.Entity.Post;
import dp3why.inst.Entity.Users;
import dp3why.inst.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;


    // For POST: Create Post to DataBase
    public Post submitPostToDataBase(PostDTO postData) {
        String uid = postData.getUserId();
        if (uid == null) {
            return null;
        }
        Post post = new Post();
        Users curUser = userService.getUserMetaData(uid);
        post.setUser(curUser);
        post.setUserId(uid);
        post.setTimestamp(postData.getTimestamp());
        post.setPostPath(postData.getPostPath());
        post.setPostId( postData.getPostId());
        post.setPostText(postData.getPostText());
        post.setLikeCount(0);
        return postRepo.save(post);
    }

    // For GET: find Post with id
    public Post findSinglePostById(int pId) {
        return postRepo.findById(pId);
    }

    public Post findSinglePostByPostId(String postId) {
        return postRepo.findByPostId(postId);
    }

    // fina raw Post: without userName
    public List<Post> retrieveAll(){
        return  postRepo.findAll();
    }

    // find Post: with userName
    public List<Post> retrievePosts(){
        List<Post> postList = postRepo.findAll();
        postList.sort((a, b) -> b.getId() - a.getId());
        return postList;
    }

    public List<Post> findPostsByUserId(String uid){
        List<Post> postList = postRepo.findByUserId(uid);
        postList.sort((a, b) -> b.getId() - a.getId());
        return postList;
    }

    public void updateLikeCount(Post post){
        int likeCount = post.getLikeCount();
        post.setLikeCount(likeCount + 1);
        postRepo.save(post);
    }

    public void updateDislikeCount(Post post){
        int likeCount = post.getLikeCount();
        if (likeCount == 0) {
            return;
        }
        post.setLikeCount(likeCount - 1);
        postRepo.save(post);
    }

    // For DELETE: delete Post by postId
    public ResponseEntity<String> deletePostByPostId(String postId){
        if (postRepo.findByPostId(postId) == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        postRepo.deleteByPostId(postId);
        return ResponseEntity.ok("Post Deleted");
    }

    // For DELETE: delete Post by id
    public ResponseEntity<String> deletePostById(int id){
        if (postRepo.findById(id) == null) {
            return ResponseEntity.badRequest().body("Post not found");
        }
        postRepo.deleteById(id);
        return ResponseEntity.ok("Post Deleted");
    }

}
