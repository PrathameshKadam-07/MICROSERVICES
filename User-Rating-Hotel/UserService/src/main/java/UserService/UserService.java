package UserService;

import java.util.List;

import entity.User;

public interface UserService {
//		create
		User saveUser(User user);
		
//		get all user
		List<User> getalluser();
		
//		get user by userid
		User getuserByUserId(String id);
		
//		delete user
		String deletUserById(String id);
		
}
