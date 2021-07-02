package com.fpl.operations.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpl.operations.microservice.entities.FplUser;

@Repository
public interface FplUserRepoInterface extends JpaRepository<FplUser, Long>{
	List<FplUser> findDistinctFplUserByUserPassword(String password);
	FplUser findByUserName(String userName);
}
