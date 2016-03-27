package org.capstone.services;

import org.capstone.entities.User;
import org.capstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User findByNumber(String phonenumber) {
		return userRepository.findByPhonenumber(phonenumber);
	}
	
	
	
}
