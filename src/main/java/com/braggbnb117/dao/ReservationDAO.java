package com.braggbnb117.dao;

import java.util.List;

import com.braggbnb117.dao.GenericDAO;
import com.braggbnb117.domain.Reservation;





public interface ReservationDAO extends GenericDAO<Reservation, Integer> {
  
	List<Reservation> findAll();
	






}


