package org.capstone.services;

import java.util.List;

import org.capstone.entities.GpsLocation;
import org.capstone.entities.User;
import org.capstone.repository.GpsLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
	@Autowired
	GpsLocationRepository gpsLocationRepository;
	
	public void saveLocation (GpsLocation gpsLocation) {
		gpsLocationRepository.save(gpsLocation);
	}
	
	/*public GpsLocation getLatestLocation(User user) {
		List <GpsLocation> list = gpsLocationRepository.getGpsLocationOrderByTime();
		return list.get(0);
	}*/
	public GpsLocation getLatestLocation(User user) {
		List <GpsLocation> list = gpsLocationRepository.findByUserOrderByTimeDesc(user);
		return list.get(0);
	}
}
