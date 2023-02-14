package com.nexcode.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nexcode.model.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String fullname;
	private String mobileNumber;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
		return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
//		if (this.lock == true) {
//			return false;
//		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;

	}

	@Override
	public boolean isEnabled() {
//		if (this.lock == true) {
//			return false;
//		}
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserPrincipal user = (UserPrincipal) o;
		return Objects.equals(id, user.id);
	}

}
