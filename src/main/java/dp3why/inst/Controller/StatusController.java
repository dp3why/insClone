package dp3why.inst.Controller;

import dp3why.inst.Entity.Status;
import dp3why.inst.Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("")
    private Status submitStatus(@RequestBody Status status) {
        return statusService.submitDataIntoDB(status);
    }
    @GetMapping("")
    private ArrayList<Status> getAllStatus(){
        return statusService.retrieveStatus();
    }

    @GetMapping("/{statusId}")
    private Status singleStatus(@PathVariable("statusId") String statusId) {
        return statusService.findSingleStatus(statusId);
    }


    @GetMapping("/all")
    private ArrayList<Status> allStatus(){
        return statusService.getAllStatus();
    }

    @DeleteMapping("/delete/{statusId}")
    public void deleteStatus(@PathVariable String statusId) {
        statusService.deleteStatus(Integer.parseInt(statusId));
    }
}


