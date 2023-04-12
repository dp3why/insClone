package dp3why.inst.Controller;

import dp3why.inst.Entity.Post;
import dp3why.inst.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    private Post submitUserPost(@RequestBody Post post) {
        return postService.submitPostToDataBase(post);
    }


    // with username
    @GetMapping("")
    private ArrayList<Post> getAllPost() {
        return postService.retrievePosts();
    }
    // raw without username
    @GetMapping("/all")
    private ArrayList<Post> allPost() {
        return postService.retrieveAll();
    }


    @DeleteMapping("/delete/{postId}")
    public void deleteStatus(@PathVariable String postId) {
        postService.deletePost(Integer.parseInt(postId));
    }

    @GetMapping("/{postId}")
    private Post getSinglePost(@PathVariable("postId") String postId) {
        return postService.findSinglePost(postId);
    }


}
