package UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.User;
import exception.ResourceNotFoundException;
import repository.UserRepo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo ur;
	
	@Override
	public User saveUser(User user) {
			
		return ur.save(user);
	}

	@Override
	public List<User> getalluser() {
		
		return ur.findAll();
	}

	@Override
	public User getuserByUserId(String id) {
		
		return ur.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
	}

	@Override
	public String deletUserById(String id) {
		String message = null;
		if(ur.findById(id) != null) {
			ur.deleteById(id);
			 message = "User Successfully Deleted";
		}
		else {
			 message = "User Not Found";
		}
		
		return message;
	}

}
