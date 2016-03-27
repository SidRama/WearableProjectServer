package org.capstone.repository;

import java.util.List;

import org.capstone.entities.GpsLocation;
import org.capstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GpsLocationRepository extends JpaRepository<GpsLocation, Integer> {
	@Override
	public <S extends GpsLocation> S save(@Param("gpsLocation") S gpsLocation);
	
	//public List <GpsLocation> getGpsLocationOrderByTime();
	public List <GpsLocation> findByUserOrderByTimeDesc(User user);
}
