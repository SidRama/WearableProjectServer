package org.capstone.controllers.rest;

import org.capstone.entities.User;
import org.capstone.repository.UserRepository;
import org.capstone.security.AuthenticatedUser;
import org.capstone.services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String test() {
		//List<User> user = userRepository.findByPhonenumber("test");
		AuthenticatedUser authUser;
		authUser = (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//userRepository.findOne(Utils.getSecurityPrincipal().getUserId());
		User user = userRepository.findOne(authUser.getId());
		System.out.println(user.getPhonenumber());
		System.out.println(user.getPassword());
		return "login";
	}

	@RequestMapping( value="/login", method = RequestMethod.POST)
	public String login(@RequestBody String requestBody) {
		System.out.println("inside");
		JSONObject responseJsonObject = new JSONObject();
		String phonenumber;
		String password;
		try{
			JSONObject object = new JSONObject(requestBody);
			phonenumber = object.getString("phonenumber");
			password = object.getString("password");
			User user = userService.findByNumber(phonenumber);
			String sha256Password = passwordEncoder.encode(password);
			if(sha256Password.equals(user.getPassword())){
				responseJsonObject.put("response", "Login Successful");
			}
			else {
				responseJsonObject.put("response", "Login failed");
			}
		}
		catch (Exception e){
			e.printStackTrace();
			try {
				responseJsonObject.put("response", "Invalid Credentials");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
		}
		return responseJsonObject.toString();
	}
	
	
	
	


}
