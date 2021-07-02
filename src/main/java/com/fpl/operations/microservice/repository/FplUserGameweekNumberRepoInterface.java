package com.fpl.operations.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpl.operations.microservice.entities.FplUserGameweekNumber;

public interface FplUserGameweekNumberRepoInterface extends JpaRepository<FplUserGameweekNumber, Long>{

}
