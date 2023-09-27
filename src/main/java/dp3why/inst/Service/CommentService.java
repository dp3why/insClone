package dp3why.inst.Service;

import dp3why.inst.Entity.Comments;
import dp3why.inst.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserService userService;

    public Comments submitCommentToDB(Comments comment) {
        if (comment.getComment().length() == 0) {
            return null;
        }
        return commentRepo.save(comment);
    }


    public ArrayList<Comments> allComments() {
        return commentRepo.findAll();
    }




    public ArrayList<Comments> getAllComments(String postId) {
        ArrayList<Comments> commentList = commentRepo.findAllByPostId(postId);

        for (Comments commentItem : commentList) {
            commentItem.setUserName(userService.getUserMetaData(commentItem.getUserId())
                    .getUserName()
            );
        }

        return commentList;
    }

    public Boolean deleteComment(int commentId){
        Comments comment = commentRepo.findById(commentId).orElse(null);
        if (comment == null) {
            return false;
        }
        commentRepo.deleteById(commentId);
        return true;
    }


}
