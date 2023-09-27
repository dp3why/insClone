package dp3why.inst.Controller;

import dp3why.inst.Entity.Comments;
import dp3why.inst.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    // Create a comment
    @PostMapping("")
    private ResponseEntity<Comments> submitComment(@RequestBody Comments comment){
        Comments savedComment = commentService.submitCommentToDB(comment);
        if (savedComment == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(savedComment);
    }

    // Get all comments for a post
    @GetMapping("/{postId}")
    private ArrayList<Comments> getCommentsForPost(@PathVariable("postId") String postId ) {
        return commentService.getAllComments(postId);
    }

    // Get all comments
    @GetMapping("/all")
    private ArrayList<Comments> allComments( ) {
        return commentService.allComments();
    }

    // Delete a comment
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable("commentId") String commentId) {
        Boolean deleted =commentService.deleteComment(Integer.parseInt(commentId));
        if (deleted) {
            return ResponseEntity.ok("Comment deleted sucessfully.");
        } else {
            return ResponseEntity.badRequest().body("Comment not found.");
        }
    }


}
