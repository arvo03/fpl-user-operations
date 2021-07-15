package com.fpl.operations.microservice.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "FPL_USER_GAMEWEEK_NUMBER")
public class FplUserGameweekNumber {
	@Id
	@SequenceGenerator(name = "FPL_USER_GAMEWEEK_NUMBER_SEQ", allocationSize = 1, initialValue = 1000)
	@GeneratedValue(generator = "FPL_USER_GAMEWEEK_NUMBER_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "JPA_ID")
	private long id;
	
	@Column(name = "GAMEWEEK")
	private int gameweek;
	
	@Column(name = "CRE_DT")
	private Date createdDate;
	
	@Column(name = "UPDT_DT")
	private Date updatedDate;
	
	@JoinColumn(name = "FPL_USER_JPA_ID")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JsonBackReference 
	private FplUser fplUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getGameweek() {
		return gameweek;
	}

	public void setGameweek(int gameweek) {
		this.gameweek = gameweek;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Lazy
	public FplUser getFplUser() {
		return fplUser;
	}

	public void setFplUser(FplUser fplUser) {
		this.fplUser = fplUser;
	} 
	
	
	
}
