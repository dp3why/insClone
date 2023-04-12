package dp3why.inst.Controller;

import dp3why.inst.Entity.Comments;
import dp3why.inst.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("")
    private Comments submitComment(@RequestBody Comments comment){
        return commentService.submitCommentToDB(comment);
    }

    @GetMapping("/{postId}")
    private ArrayList<Comments> getCommentsForPost(@PathVariable("postId") String postId ) {
        return commentService.getAllComments(postId);
    }

    @GetMapping("/all")
    private ArrayList<Comments> allComments( ) {
        return commentService.allComments();
    }


    @DeleteMapping("/delete/{commentId}")
    public void deleteStatus(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(Integer.parseInt(commentId));
    }


}
