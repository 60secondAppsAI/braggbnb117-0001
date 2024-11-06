package com.braggbnb117.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.braggbnb117.dao.GenericDAO;
import com.braggbnb117.service.GenericService;
import com.braggbnb117.service.impl.GenericServiceImpl;
import com.braggbnb117.dao.UserDAO;
import com.braggbnb117.domain.User;
import com.braggbnb117.dto.UserDTO;
import com.braggbnb117.dto.UserSearchDTO;
import com.braggbnb117.dto.UserPageDTO;
import com.braggbnb117.dto.UserConvertCriteriaDTO;
import com.braggbnb117.dto.common.RequestDTO;
import com.braggbnb117.dto.common.ResultDTO;
import com.braggbnb117.service.UserService;
import com.braggbnb117.util.ControllerUtils;





@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDao;

	


	@Override
	public GenericDAO<User, Integer> getDAO() {
		return (GenericDAO<User, Integer>) userDao;
	}
	
	public List<User> findAll () {
		List<User> users = userDao.findAll();
		
		return users;	
		
	}

	public ResultDTO addUser(UserDTO userDTO, RequestDTO requestDTO) {

		User user = new User();

		user.setUserId(userDTO.getUserId());


		user.setFirstName(userDTO.getFirstName());


		user.setLastName(userDTO.getLastName());


		user.setEmail(userDTO.getEmail());


		user.setHashedPassword(userDTO.getHashedPassword());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		user = userDao.save(user);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<User> getAllUsers(Pageable pageable) {
		return userDao.findAll(pageable);
	}

	public Page<User> getAllUsers(Specification<User> spec, Pageable pageable) {
		return userDao.findAll(spec, pageable);
	}

	public ResponseEntity<UserPageDTO> getUsers(UserSearchDTO userSearchDTO) {
	
			Integer userId = userSearchDTO.getUserId(); 
 			String firstName = userSearchDTO.getFirstName(); 
 			String lastName = userSearchDTO.getLastName(); 
 			String email = userSearchDTO.getEmail(); 
 			String hashedPassword = userSearchDTO.getHashedPassword(); 
 			String sortBy = userSearchDTO.getSortBy();
			String sortOrder = userSearchDTO.getSortOrder();
			String searchQuery = userSearchDTO.getSearchQuery();
			Integer page = userSearchDTO.getPage();
			Integer size = userSearchDTO.getSize();

	        Specification<User> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, userId, "userId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, firstName, "firstName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, lastName, "lastName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			
			spec = ControllerUtils.andIfNecessary(spec, hashedPassword, "hashedPassword"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("firstName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("lastName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("hashedPassword")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<User> users = this.getAllUsers(spec, pageable);
		
		//System.out.println(String.valueOf(users.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(users.getTotalPages()));
		
		List<User> usersList = users.getContent();
		
		UserConvertCriteriaDTO convertCriteria = new UserConvertCriteriaDTO();
		List<UserDTO> userDTOs = this.convertUsersToUserDTOs(usersList,convertCriteria);
		
		UserPageDTO userPageDTO = new UserPageDTO();
		userPageDTO.setUsers(userDTOs);
		userPageDTO.setTotalElements(users.getTotalElements());
		return ResponseEntity.ok(userPageDTO);
	}

	public List<UserDTO> convertUsersToUserDTOs(List<User> users, UserConvertCriteriaDTO convertCriteria) {
		
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		
		for (User user : users) {
			userDTOs.add(convertUserToUserDTO(user,convertCriteria));
		}
		
		return userDTOs;

	}
	
	public UserDTO convertUserToUserDTO(User user, UserConvertCriteriaDTO convertCriteria) {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUserId(user.getUserId());

	
		userDTO.setFirstName(user.getFirstName());

	
		userDTO.setLastName(user.getLastName());

	
		userDTO.setEmail(user.getEmail());

	
		userDTO.setHashedPassword(user.getHashedPassword());

	

		
		return userDTO;
	}

	public ResultDTO updateUser(UserDTO userDTO, RequestDTO requestDTO) {
		
		User user = userDao.getById(userDTO.getUserId());

		user.setUserId(ControllerUtils.setValue(user.getUserId(), userDTO.getUserId()));

		user.setFirstName(ControllerUtils.setValue(user.getFirstName(), userDTO.getFirstName()));

		user.setLastName(ControllerUtils.setValue(user.getLastName(), userDTO.getLastName()));

		user.setEmail(ControllerUtils.setValue(user.getEmail(), userDTO.getEmail()));

		user.setHashedPassword(ControllerUtils.setValue(user.getHashedPassword(), userDTO.getHashedPassword()));



        user = userDao.save(user);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public UserDTO getUserDTOById(Integer userId) {
	
		User user = userDao.getById(userId);
			
		
		UserConvertCriteriaDTO convertCriteria = new UserConvertCriteriaDTO();
		return(this.convertUserToUserDTO(user,convertCriteria));
	}







}
