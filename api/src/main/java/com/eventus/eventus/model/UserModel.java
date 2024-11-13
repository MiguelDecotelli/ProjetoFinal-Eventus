package com.eventus.eventus.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false, unique = true)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "first_name")
	private String name;

	@Column(name = "last_name")
	private String lastname;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "role")
	private UserRole role;

	@Column(name = "phone_number")
	private String phoneNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city")
	private CityModel city;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == UserRole.ADMIN) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_USER"));
		}
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}