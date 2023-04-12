package dp3why.inst.Service;

import dp3why.inst.Entity.Status;
import dp3why.inst.Repository.StatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StatusService {
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    UserService userService;
    public Status submitDataIntoDB(Status status) {
        return statusRepo.save(status);
    }

    public ArrayList<Status> retrieveStatus(){
        ArrayList<Status> statusList = statusRepo.findAll();
        for (Status statusItem : statusList) {
            statusItem.setUserName(
                    userService.displayUserMetaData(statusItem.getUserId())
                            .getUserName()
            );
        }
        return statusList;
    }

    public Status findSingleStatus(String statusId) {
        return statusRepo.findByStatusId(statusId);
    }
    public ArrayList<Status> getAllStatus(){

        return statusRepo.findAll();
    }

    public void deleteStatus(int id){

        statusRepo.deleteById(id);
    }


}

