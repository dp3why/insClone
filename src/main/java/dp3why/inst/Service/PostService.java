package dp3why.inst.Service;

import dp3why.inst.Entity.Post;
import dp3why.inst.Repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    public Post submitPostToDataBase(Post post) {
        return postRepo.save(post);
    }

    public Post findSinglePost(String postId) {
        return postRepo.findByPostId(postId);
    }

    // fina raw Post: without userName
    public ArrayList<Post> retrieveAll(){
        return postRepo.findAll();
    }


    // find Post: with userName
    public ArrayList<Post> retrievePosts(){
        ArrayList<Post> postList = postRepo.findAll();
        for (Post postItem : postList) {
            postItem.setUserName(
                    userService.displayUserMetaData(postItem.getUserId())
                            .getUserName()
            );
        }
        postList.sort((a, b) -> b.getId() - a.getId());
        return postList;
    }


    public void deletePost(int postId){
        postRepo.deleteById(postId);
    }


}
