package dp3why.inst.Controller;

import dp3why.inst.Entity.Users;
import dp3why.inst.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;


	public UserController(UserService userService) {
		this.userService = userService;
	}
	@PostMapping("")
	private Users submitUser(@RequestBody Users users) {
		return userService.submitMetaDataOfUser(users);
	}

	@GetMapping("/{userid}")
	private Users getUserDetails(@PathVariable("userid") String userId) {
		return userService.getUserMetaData(userId);
	}

	@GetMapping("")
	private ArrayList<Users> getUsers() {
		return userService.getUsers();
	}

	@DeleteMapping("/delete/id/{id}")
	private void deleteUserByUid(@PathVariable int id) {
		userService.deleteUserById(id);
	}

	@DeleteMapping("/delete/uid/{uid}")
	private void deleteUserById(@PathVariable String uid) {
		userService.deleteUserByUserId(uid);
	}


}
