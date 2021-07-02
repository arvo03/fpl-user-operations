package com.fpl.operations.microservice.filtering;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Component
public class Filtering implements FilteringInterface {

	@Override
	public SimpleFilterProvider includeAllBut(Set<String> list, Class<?> c, String classFilterName) {

		Set<String> allFieldsOfClass = new HashSet<>();
		for (Field f : c.getDeclaredFields())
			allFieldsOfClass.add(f.getName());
		allFieldsOfClass.removeAll(list);
		SimpleBeanPropertyFilter fieldsRequired = SimpleBeanPropertyFilter.filterOutAllExcept(allFieldsOfClass);
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter(classFilterName, fieldsRequired);

		return filters;
	}

	@Override
	public SimpleFilterProvider excludeAllBut(Set<String> list, Class<?> c, String classFilterName) {
		
		SimpleBeanPropertyFilter fieldsRequired = SimpleBeanPropertyFilter.filterOutAllExcept(list);
		SimpleFilterProvider filters = new SimpleFilterProvider().addFilter(classFilterName, fieldsRequired);
		return filters;
	}

}
