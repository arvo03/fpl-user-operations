package com.fpl.operations.microservice.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonFilter;

@Entity
@JsonFilter("userPasswordFilter")
@Table(name = "FPL_USER")
public class FplUser {

	public FplUser() {
	}
	
	public FplUser(String userName, String userPassword, char emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	@Id
	@SequenceGenerator(name = "FPL_USER_SEQ", allocationSize = 1, initialValue = 100)
	@GeneratedValue(generator = "FPL_USER_SEQ", strategy = GenerationType.SEQUENCE)
	@Column(name = "JPA_ID")
	private long id;

	@Size(min = 5, message = "UserName minimum length = 5")
	@Column(name = "USER_NAME")
	private String userName;

	@Size(min = 8, message = "userPassword minimum length = 8")
	@Column(name = "USERPASSWORD")
	private String userPassword;

	@Column(name = "USER_EMAIL_VERIFICATION")
	private char emailVerificationStatus;
	
	@Column(name = "TEAM_CREATED")
	private char teamCreated;
	
	@Column(name = "TEAM_CREATED_GAMEWEEK")
	private Integer teamCreatedInGameweekNumber;
	
	@Column(name = "WILDCARD_PLAYED")
	private char wildcardPlayed;
	
	@Column(name = "TRIPLE_CAPTAIN_PLAYED")
	private char tripleCaptainPlayed;
	
	@Column(name = "USER_ENABLED")
	private char enabled;
	
	@Column(name = "ROLES")
	private String authorities;
	
	@Valid
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = FplUserGameweekRecord.class, fetch = FetchType.LAZY, mappedBy = "fplUser")
	private List<FplUserGameweekRecord> fplUserGameweekRecord = new ArrayList<>();
	
	@Valid
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = FplUserInfo.class, fetch = FetchType.LAZY, mappedBy = "fplUser")
	private FplUserInfo fplUserInfo;
	
	@Valid
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = FplUserGameweekNumber.class, fetch = FetchType.LAZY, mappedBy = "fplUser")
	private List<FplUserGameweekNumber> fplUserGameweekNumberList = new ArrayList<>();
	
	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public char getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(char emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public FplUserInfo getFplUserInfo() {
		return fplUserInfo;
	}

	public void setFplUserInfo(FplUserInfo fplUserInfo) {
		this.fplUserInfo = fplUserInfo;
	}
	
	public List<FplUserGameweekRecord> getFplUserGameweekRecord() {
		return fplUserGameweekRecord;
	}

	public void setFplUserGameweekRecord(List<FplUserGameweekRecord> fplUserGameweekRecord) {
		this.fplUserGameweekRecord = fplUserGameweekRecord;
	}
	
	public List<FplUserGameweekNumber> getFplUserGameweekNumberList() {
		return fplUserGameweekNumberList;
	}

	public void setFplUserGameweekNumberList(List<FplUserGameweekNumber> fplUserGameweekNumberList) {
		this.fplUserGameweekNumberList = fplUserGameweekNumberList;
	}

	public char getTeamCreated() {
		return teamCreated;
	}

	public void setTeamCreated(char teamCreated) {
		this.teamCreated = teamCreated;
	}

	public Integer getTeamCreatedInGameweekNumber() {
		return teamCreatedInGameweekNumber;
	}

	public void setTeamCreatedInGameweekNumber(Integer teamCreatedInGameweekNumber) {
		this.teamCreatedInGameweekNumber = teamCreatedInGameweekNumber;
	}

	public char getWildcardPlayed() {
		return wildcardPlayed;
	}

	public void setWildcardPlayed(char wildcardPlayed) {
		this.wildcardPlayed = wildcardPlayed;
	}

	public char getTripleCaptainPlayed() {
		return tripleCaptainPlayed;
	}

	public void setTripleCaptainPlayed(char tripleCaptainPlayed) {
		this.tripleCaptainPlayed = tripleCaptainPlayed;
	}

	public char getEnabled() {
		return enabled;
	}

	public void setEnabled(char enabled) {
		this.enabled = enabled;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
}
