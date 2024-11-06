package com.braggbnb117.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb117.domain.Reservation;
import com.braggbnb117.dto.ReservationDTO;
import com.braggbnb117.dto.ReservationSearchDTO;
import com.braggbnb117.dto.ReservationPageDTO;
import com.braggbnb117.dto.ReservationConvertCriteriaDTO;
import com.braggbnb117.service.GenericService;
import com.braggbnb117.dto.common.RequestDTO;
import com.braggbnb117.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ReservationService extends GenericService<Reservation, Integer> {

	List<Reservation> findAll();

	ResultDTO addReservation(ReservationDTO reservationDTO, RequestDTO requestDTO);

	ResultDTO updateReservation(ReservationDTO reservationDTO, RequestDTO requestDTO);

    Page<Reservation> getAllReservations(Pageable pageable);

    Page<Reservation> getAllReservations(Specification<Reservation> spec, Pageable pageable);

	ResponseEntity<ReservationPageDTO> getReservations(ReservationSearchDTO reservationSearchDTO);
	
	List<ReservationDTO> convertReservationsToReservationDTOs(List<Reservation> reservations, ReservationConvertCriteriaDTO convertCriteria);

	ReservationDTO getReservationDTOById(Integer reservationId);







}





