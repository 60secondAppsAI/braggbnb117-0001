package com.braggbnb117.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb117.domain.Amenity;
import com.braggbnb117.dto.AmenityDTO;
import com.braggbnb117.dto.AmenitySearchDTO;
import com.braggbnb117.dto.AmenityPageDTO;
import com.braggbnb117.dto.AmenityConvertCriteriaDTO;
import com.braggbnb117.service.GenericService;
import com.braggbnb117.dto.common.RequestDTO;
import com.braggbnb117.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AmenityService extends GenericService<Amenity, Integer> {

	List<Amenity> findAll();

	ResultDTO addAmenity(AmenityDTO amenityDTO, RequestDTO requestDTO);

	ResultDTO updateAmenity(AmenityDTO amenityDTO, RequestDTO requestDTO);

    Page<Amenity> getAllAmenitys(Pageable pageable);

    Page<Amenity> getAllAmenitys(Specification<Amenity> spec, Pageable pageable);

	ResponseEntity<AmenityPageDTO> getAmenitys(AmenitySearchDTO amenitySearchDTO);
	
	List<AmenityDTO> convertAmenitysToAmenityDTOs(List<Amenity> amenitys, AmenityConvertCriteriaDTO convertCriteria);

	AmenityDTO getAmenityDTOById(Integer amenityId);







}





