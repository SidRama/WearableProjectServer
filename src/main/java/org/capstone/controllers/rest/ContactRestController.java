package org.capstone.controllers.rest;

import org.capstone.entities.GpsLocation;
import org.capstone.entities.User;
import org.capstone.services.LocationService;
import org.capstone.services.UserService;
import org.capstone.utils.GeoCodingUtil;
import org.capstone.utils.locationRec;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ContactRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	LocationService locationService;

	@RequestMapping(value="/uploadlocation", method=RequestMethod.POST, produces="application/json")
	public String uploadLocation (@RequestBody locationRec requestBody) {
		JSONObject jsonObject=null;
		jsonObject = new JSONObject(requestBody);
		JSONObject jsonResponseObject = new JSONObject();
		String phonenumber = null, latitude = null, longitude = null;
		try {
			phonenumber = jsonObject.getString("phonenumber");
			latitude = jsonObject.getString("latitude");
			longitude = jsonObject.getString("longitude");
		}
		catch(JSONException e){
			e.printStackTrace();
		}
		User user = null;
		try{
			user = userService.findByNumber(phonenumber);
		}
		catch(Exception e) {
			e.printStackTrace();
			try{
			jsonResponseObject.put("response", "User not found");
			}
			catch(Exception e2){
			}
			return jsonResponseObject.toString();
		}
		GpsLocation location = new GpsLocation();
		location.setLatitude(Double.parseDouble(latitude));
		location.setLongitude(Double.parseDouble(longitude));
		location.setUser(user);
		String address = GeoCodingUtil.reverseGeoCoding(String.valueOf(longitude), String.valueOf(latitude));
		location.setAddress(address);
		System.out.println("address: "+address);
		locationService.saveLocation(location);
		try {
			jsonResponseObject.put("response", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResponseObject.toString();
	}
	
	
	@RequestMapping(value="/emergency", method=RequestMethod.POST, produces="application/json")
	public String emergency(@RequestBody locationRec requestBody) {
		JSONObject jsonObject=null;
		jsonObject = new JSONObject(requestBody);
		JSONObject jsonResponseObject = new JSONObject();
		String phonenumber = null, latitude = null, longitude = null;
		try {
			phonenumber = jsonObject.getString("phonenumber");
			latitude = jsonObject.getString("latitude");
			longitude = jsonObject.getString("longitude");
		}
		catch(JSONException e){
			e.printStackTrace();
		}
		User user = null;
		try{
			user = userService.findByNumber(phonenumber);
		}
		catch(Exception e) {
			e.printStackTrace();
			try{
			jsonResponseObject.put("response", "User not found");
			}
			catch(Exception e2){
			}
			return jsonResponseObject.toString();
		}
		GpsLocation location = new GpsLocation();
		location.setLatitude(Double.parseDouble(latitude));
		location.setLongitude(Double.parseDouble(longitude));
		location.setUser(user);
		String address = GeoCodingUtil.reverseGeoCoding(String.valueOf(longitude), String.valueOf(latitude));
		location.setAddress(address);
		System.out.println("address: "+address);
		locationService.saveLocation(location);
		// Do awesome stuff here!!!!!
		
		
		
		try {
			jsonResponseObject.put("response", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonResponseObject.toString();
	}
	
	
}
