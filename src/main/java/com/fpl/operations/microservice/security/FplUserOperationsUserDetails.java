package com.fpl.operations.microservice.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fpl.operations.microservice.entities.FplUser;

public class FplUserOperationsUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1852711229972308346L;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	private boolean enabled;

	public FplUserOperationsUserDetails() {
	}

	public FplUserOperationsUserDetails(FplUser fplUser) {
		this.username = fplUser.getUserName();
		this.password = fplUser.getUserPassword();
		this.authorities = Arrays.stream(fplUser.getAuthorities().split(","))
								 .map(SimpleGrantedAuthority::new)
								 .collect(Collectors.toList());
		this.enabled = ((fplUser.getEnabled()!='0')?true:false);
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
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
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
		return enabled;
	}

}
