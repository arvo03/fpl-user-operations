package com.fpl.operations.microservice.entities;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FPL_USER_GAMEWEEK_RECORD")
public class FplUserGameweekRecord {

	@Id
	@SequenceGenerator(name = "FPL_USER_GAMEWEEK_RECORD_SEQ", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(generator = "FPL_USER_GAMEWEEK_RECORD_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "JPA_ID")
	private long id;
	
	@NotNull(message = "Gameweek cannot be null")
	@Column(name = "GAMEWEEK")
	private int gameweek;
	
	@Column(name = "GAMEWEEK_POINTS")
	private long gameweekPoints;
	
	
	@JoinColumn(name = "FPL_USER_JPA_ID")
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JsonIgnore
	private FplUser fplUser;


	public void setFplUser(FplUser fplUser) {
		this.fplUser = fplUser;
	}

	public FplUser getFplUser() {
		return this.fplUser;
	}

	public int getGameweek() {
		return gameweek;
	}


	public void setGameweek(int gameweek) {
		this.gameweek = gameweek;
	}


	public long getGameweekPoints() {
		return gameweekPoints;
	}


	public void setGameweekPoints(long gameweekPoints) {
		this.gameweekPoints = gameweekPoints;
	}


	public long getId() {
		return id;
	}
	
	
	
}
