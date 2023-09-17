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

    public Users getUserMetaData(String uid) {
        return userRepo.findByUid(uid);
    }

    public Users getUserById(int userId) {
        return userRepo.findById(userId);
    }

    public Users getUserByUid(String uid) {
        return userRepo.findByUid(uid);
    }

    public ArrayList<Users> getUsers() {
        return (ArrayList<Users>) userRepo.findAll();
    }

    public void deleteUserByUserId(String uid){
        userRepo.deleteByUid(uid);
    }

    public void deleteUserById(int userId){
        if (userRepo.findById(userId) == null) {
            return;
        }
        userRepo.deleteById(userId);
    }


}
