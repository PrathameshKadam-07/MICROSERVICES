package com.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.exception.ResourceNotFoundException;
import com.repository.UserRepo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo ur;
	
	@Override
	public User saveUser(User user) {
//		Generate Unique Id
			String randomUserid = UUID.randomUUID().toString();
			user.setUserid(randomUserid);
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
