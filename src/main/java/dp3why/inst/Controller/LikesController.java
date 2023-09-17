package dp3why.inst.Controller;

import dp3why.inst.DTO.LikeDTO;
import dp3why.inst.Entity.Likes;
import dp3why.inst.Entity.Post;
import dp3why.inst.Entity.Users;
import dp3why.inst.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    LikeService likeService;
    @GetMapping("")
    public List<Likes> getAllLikes() {
        return likeService.findAllLikes();
    }

    @GetMapping("/uid/{userId}")
    public List<Post> getAllLikesByUserId(@PathVariable String userId) {
        return likeService.findPostsByUserId(userId);
    }

    @GetMapping("/pid/{postId}")
    public List<Users> getAllLikesByPostId(@PathVariable String postId) {
        return likeService.findUsersByPostId(postId);
    }

    @PostMapping("/check")
    public List<String>  getLikesByPostIdAndUserId(@RequestBody LikeDTO likeDTO) {
        List<String> result = new ArrayList<>();
        String postId = likeDTO.getPostId();
        String uid = likeDTO.getUserId();
        Likes like = likeService.findByLikePostIdAndLikeUserId(postId, uid);
        if (like != null) {
            // Return a 200 OK response with the Likes object in JSON format
            result.add(postId);
            result.add(uid);
        } else {

        }
        return result;
    }

    @PostMapping("/update")
    public ResponseEntity<List<String>> updateLikeState(@RequestBody LikeDTO likeDTO) {
        List<String> result = new ArrayList<>();
        String postId = likeDTO.getPostId();
        String uid = likeDTO.getUserId();
        Likes like = likeService.findByLikePostIdAndLikeUserId(postId, uid);
        if (like == null) {
             Likes likeRes = likeService.likePost(likeDTO);
             if (likeRes != null) {
                 result.add(likeRes.getLikeUserId());
                 result.add(likeRes.getLikeUserId());
                 return ResponseEntity.ok(result);
             }
        } else {
            if (likeService.dislikePost(likeDTO) ) {
                return ResponseEntity.ok(result);
            };
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @PostMapping("/new")
    public ResponseEntity<Likes> likePostController(@RequestBody LikeDTO likeDTO) {
        Likes likeRes = likeService.likePost(likeDTO);
        return ResponseEntity.ok(likeRes);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Boolean> canceLikePostController(@RequestBody LikeDTO likeDTO) {
        Boolean res =  likeService.dislikePost(likeDTO);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLikeById(@PathVariable int id) {
        return likeService.deleteLikeById(id);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteLike(@RequestBody LikeDTO likeDTO) {
        if (likeDTO == null || likeDTO.getPostId() == null ||
                likeDTO.getUserId() == null ||
                likeDTO.getPostId().equals("") ||
                likeDTO.getUserId().equals("")) {
            return ResponseEntity.badRequest().body("Invalid request.");
        }
        return likeService.deleteLike(likeDTO);
    }

}
