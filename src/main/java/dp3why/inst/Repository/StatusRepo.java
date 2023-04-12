package dp3why.inst.Repository;

import dp3why.inst.Entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StatusRepo extends CrudRepository<Status, Integer> {
    Status findByStatusId(String statusId);
    ArrayList<Status> findAll();
}
