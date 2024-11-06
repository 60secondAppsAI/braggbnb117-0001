package com.braggbnb117.dao;

import java.util.List;

import com.braggbnb117.dao.GenericDAO;
import com.braggbnb117.domain.Payment;





public interface PaymentDAO extends GenericDAO<Payment, Integer> {
  
	List<Payment> findAll();
	






}


