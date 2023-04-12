package dp3why.inst.Repository;

import dp3why.inst.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {
    Users findByUserId(String userId);



}


