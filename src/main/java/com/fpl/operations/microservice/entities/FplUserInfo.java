package com.fpl.operations.microservice.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "FPL_USER_INFO")
public class FplUserInfo {
	
	@Id
	@SequenceGenerator(name = "FPL_USER_INFO_SEQ", allocationSize = 1, initialValue = 100)
	@GeneratedValue(generator = "FPL_USER_INFO_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "JPA_ID")
	private long jpaId;
	
	@Min(value = 18, message = "Age should be minimum 18 Years")
	@Column(name = "AGE")
	private int age;
	
	@Size(min = 2, message = "favouriteTeam minimum length = 2")
	@Column(name = "FAV_TEAM")
	private String favouriteTeam;
	
	@JoinColumn(name = "FPL_USER_JPA_ID")
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JsonIgnore
	private FplUser fplUser;

	public long getJpaId() {
		return jpaId;
	}

	public void setJpaId(long jpaId) {
		this.jpaId = jpaId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFavouriteTeam() {
		return favouriteTeam;
	}

	public void setFavouriteTeam(String favouriteTeam) {
		this.favouriteTeam = favouriteTeam;
	}

	public void setFplUser(FplUser fplUser) {
		this.fplUser = fplUser;
	}
	
	public FplUser getFplUser() {
		return this.fplUser;
	}
	
	
	
}
