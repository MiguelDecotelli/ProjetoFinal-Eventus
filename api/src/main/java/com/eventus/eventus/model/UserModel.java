package com.eventus.eventus.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class UserModel implements UserDetails {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false, unique = false)
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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "tickets")
	private Set<UsersTicketsModel> tickets;

	// User Details Methods Override
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

	// Constructors
	public UserModel() {
	}

	public UserModel(int id, String username, String password, String email, String firstName,
			String lastName, Date birthday, UserRole role, String phoneNumber, CityModel city,
			Set<UsersTicketsModel> tickets) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = firstName;
		this.lastname = lastName;
		this.birthday = birthday;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.tickets = tickets;
	}

	// Getters and Setter
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
		return birthday;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public void setCity(CityModel city) {
		this.city = city;
	}

	public CityModel getCity() {
		return city;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Set<UsersTicketsModel> getTickets() {
		return tickets;
	}
	public void setTickets(Set<UsersTicketsModel> tickets) {
		this.tickets = tickets;
	}
}
