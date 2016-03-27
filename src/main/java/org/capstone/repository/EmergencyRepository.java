package org.capstone.repository;

import org.capstone.entities.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EmergencyRepository extends JpaRepository<Emergency, Integer> {
	@Override
	public <S extends Emergency> S save(@Param("emergency") S emergency);
}