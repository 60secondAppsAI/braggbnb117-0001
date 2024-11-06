package com.braggbnb117.dao;

import java.util.List;

import com.braggbnb117.dao.GenericDAO;
import com.braggbnb117.domain.Listing;





public interface ListingDAO extends GenericDAO<Listing, Integer> {
  
	List<Listing> findAll();
	






}


