package com.fpl.operations.microservice.filtering;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public interface FilteringInterface {
	public SimpleFilterProvider includeAllBut(Set<String> list, Class<?> c, String classFilterName) ;
	public SimpleFilterProvider excludeAllBut(Set<String> list, Class<?> c, String classFilterName) ;
}
