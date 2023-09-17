package dp3why.inst.Controller;

import dp3why.inst.DTO.PostDTO;
import dp3why.inst.Entity.Post;
import dp3why.inst.Service.LikeService;
import dp3why.inst.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    LikeService likeService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    // For POST CREATE
    @PostMapping("")
    private Post submitUserPost(@RequestBody PostDTO postData) {
        return postService.submitPostToDataBase(postData);
    }

    // FOR GET with username
    @GetMapping("")
    private List<Post> getAllPost() {
        return postService.retrievePosts();
    }
    // raw without username
    @GetMapping("/all")
    private List<Post> allPost() {
        return postService.retrieveAll();
    }
    // DELETE by postId
    @DeleteMapping("/delete/pid/{postId}")
    public ResponseEntity<String> deleteByPostId(@PathVariable String postId) {
        return postService.deletePostByPostId(postId);
    }
    // DELETE by post id
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        return postService.deletePostById(id);
    }
    // get single post by postId
    @GetMapping("/pid/{postId}")
    private Post getSinglePostByPostId(@PathVariable("postId") String postId) {
        return postService.findSinglePostByPostId(postId);
    }
    // get single post by post id
    @GetMapping("/id/{pId}")
    private Post getSinglePostById(@PathVariable("pId") int pId) {
        return postService.findSinglePostById(pId);
    }
    // get a list of posts by user uid
    @GetMapping("/user/{uid}")
    private List<Post> getSinglePostById(@PathVariable("uid") String uid) {
        return postService.findPostsByUserId(uid);
    }

}
