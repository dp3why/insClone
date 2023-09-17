package dp3why.inst.Repository;

import dp3why.inst.Entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<Users, Integer> {

    Users findByUid(String userId);
    Users findById(int id);

    void deleteByUid(String userId);
    void deleteById(int id);

}


