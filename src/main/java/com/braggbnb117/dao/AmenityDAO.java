package com.braggbnb117.dao;

import java.util.List;

import com.braggbnb117.dao.GenericDAO;
import com.braggbnb117.domain.Amenity;





public interface AmenityDAO extends GenericDAO<Amenity, Integer> {
  
	List<Amenity> findAll();
	






}


