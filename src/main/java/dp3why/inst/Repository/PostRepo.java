package dp3why.inst.Repository;

import dp3why.inst.Entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends CrudRepository <Post, Integer>{
    List<Post> findAll();
    Post findByPostId(String postId);
    Post findById(int id);
    void deleteByPostId(String postId);
    void deleteById(int id);

    List<Post> findByUserId(String uid);

}
