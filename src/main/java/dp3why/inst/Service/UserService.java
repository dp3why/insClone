package dp3why.inst.Service;
import dp3why.inst.Entity.Users;
import dp3why.inst.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public Users submitMetaDataOfUser(Users user) {
        userRepo.save(user);
        return user;
    }

    public Users displayUserMetaData(String userId) {
        return userRepo.findByUserId(userId);
    }

    public ArrayList<Users> getUsers() {
        return (ArrayList<Users>) userRepo.findAll();
    }

    public void deleteUser(int id){
        userRepo.deleteById(id);
    }

}
