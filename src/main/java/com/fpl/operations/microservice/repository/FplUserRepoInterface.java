package com.fpl.operations.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpl.operations.microservice.entities.FplUser;

public interface FplUserRepoInterface extends JpaRepository<FplUser, Long>{

}
