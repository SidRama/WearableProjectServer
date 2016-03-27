package org.capstone.controllers.rest;

import org.capstone.entities.Emergency;
import org.capstone.entities.User;
import org.capstone.repository.EmergencyRepository;
import org.capstone.repository.UserRepository;
import org.capstone.services.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class RegisterRestController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	EmergencyRepository emergencyRepository;
	
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(@RequestParam String password, @RequestParam String number, @RequestParam String contact){
		System.out.println("saving..");
		String shaPassword = passwordEncoder.encode(password);
		User user = new User();
		user.setPhonenumber(number);
		user.setPassword(shaPassword);
		userRepository.save(user);
		Emergency emergencyContact = new Emergency(contact,user);
		emergencyRepository.save(emergencyContact);
		
		return "login";
	}
	
	@RequestMapping(value="/registeruser", method=RequestMethod.GET, produces="application/json")
	public String registerUser(@RequestParam String password, @RequestParam String number, @RequestParam String contact){
		System.out.println("saving..");
		JSONObject responseJsonObject = new JSONObject();
		String shaPassword = passwordEncoder.encode(password);
		User user = new User();
		user.setPhonenumber(number);
		user.setPassword(shaPassword);
		userRepository.save(user);
		Emergency emergencyContact = new Emergency(contact,user);
		emergencyRepository.save(emergencyContact);
		try {
			responseJsonObject.put("result","success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJsonObject.toString();
	}
	
	@RequestMapping( value="/loginuser", method = RequestMethod.POST,produces="application/json")
	public String loginUser(@RequestBody User requestBody) {
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

			if(passwordEncoder.matches(password, user.getPassword())){
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
