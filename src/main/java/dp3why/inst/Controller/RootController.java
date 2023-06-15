package dp3why.inst.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("")
public class RootController {
    @GetMapping("")
    private String runningCheck() {
        return "Spring App is running.";
    }
}
