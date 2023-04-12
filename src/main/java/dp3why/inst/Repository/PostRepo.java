package dp3why.inst.Repository;

import dp3why.inst.Entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface PostRepo extends CrudRepository <Post, Integer>{
    ArrayList<Post> findAll();
    Post findByPostId(String postId);

}
