package dp3why.inst.Repository;

import dp3why.inst.Entity.Likes;
import dp3why.inst.Entity.Post;
import dp3why.inst.Entity.Users;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikesRepo extends CrudRepository<Likes, Integer> {

    Likes findById(int id);
    List<Likes> findByUser(Users user);
    List<Likes> findByPost(Post post);
    List<Likes> findByLikePostId(String postId);
    List<Likes> findByLikeUserId(String userId);

    List<Likes> findAll();

    void deleteById(int id);

}
