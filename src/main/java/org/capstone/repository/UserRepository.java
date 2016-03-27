package org.capstone.repository;

import org.capstone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/*
	 * Default functions
	 */

	@Override
	public <S extends User> S save(@Param("user") S user);

	@Override
	public void delete(@Param("user") User user);

	

	User findByPhonenumber(String phonenumber);
}
