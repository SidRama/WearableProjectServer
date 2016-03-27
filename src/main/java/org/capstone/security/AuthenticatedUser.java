package org.capstone.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.capstone.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	int id;
	String username;
	String password;
	List<GrantedAuthority> authorities;
	
	public AuthenticatedUser(User user) {
		id = user.getId();
		username = user.getPhonenumber();
		password = user.getPassword();
		Set<String> authoritiesSet = new HashSet<String>();
		authoritiesSet.add("ADMIN");
		authorities = AuthorityUtils.createAuthorityList(authoritiesSet.toArray(new String[authoritiesSet.size()]));
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

} 
