package com.braggbnb117.dao;

import java.util.List;

import com.braggbnb117.dao.GenericDAO;
import com.braggbnb117.domain.Host;





public interface HostDAO extends GenericDAO<Host, Integer> {
  
	List<Host> findAll();
	






}


