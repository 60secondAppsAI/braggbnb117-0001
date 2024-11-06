package com.braggbnb117.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.braggbnb117.domain.Listing;
import com.braggbnb117.dto.ListingDTO;
import com.braggbnb117.dto.ListingSearchDTO;
import com.braggbnb117.dto.ListingPageDTO;
import com.braggbnb117.dto.ListingConvertCriteriaDTO;
import com.braggbnb117.service.GenericService;
import com.braggbnb117.dto.common.RequestDTO;
import com.braggbnb117.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ListingService extends GenericService<Listing, Integer> {

	List<Listing> findAll();

	ResultDTO addListing(ListingDTO listingDTO, RequestDTO requestDTO);

	ResultDTO updateListing(ListingDTO listingDTO, RequestDTO requestDTO);

    Page<Listing> getAllListings(Pageable pageable);

    Page<Listing> getAllListings(Specification<Listing> spec, Pageable pageable);

	ResponseEntity<ListingPageDTO> getListings(ListingSearchDTO listingSearchDTO);
	
	List<ListingDTO> convertListingsToListingDTOs(List<Listing> listings, ListingConvertCriteriaDTO convertCriteria);

	ListingDTO getListingDTOById(Integer listingId);







}





