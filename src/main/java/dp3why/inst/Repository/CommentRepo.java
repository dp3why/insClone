package dp3why.inst.Repository;

import dp3why.inst.Entity.Comments;
import dp3why.inst.Entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentRepo extends CrudRepository<Comments, Integer> {
    ArrayList<Comments> findAll();
    ArrayList<Comments> findAllByPostId(String postId);
}
