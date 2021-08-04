package com.javaPro.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.javaPro.address.Address;
import com.javaPro.address.AddressDTO;
import com.javaPro.constants.Constants;
import com.javaPro.util.MongoUtil;

@Service
public class UserService {

	@Inject
	private UserRepository repo;

	public UserDTO create(UserDTO dto) throws Exception {
		if (dto == null)
			throw new Exception("User not found");
		try {
			User domain = new User();
			domain.setRecordStatus(Constants.RECORD_STATUS_CREATED);
			domain.setCreatedDate(new java.util.Date());
			domain.setLastModifiedDate(new java.util.Date());
			domain = repo.save(dtoToDomain(dto, domain));
			dto = DomainToDTO(domain, dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public Page<User> getAllUsers(Map<String, String> hmap, Pageable pageable) {
		Page<User> page = null;
		try {
			String id = hmap.get("id");
			Criteria cr = MongoUtil.getCriteria();
			if (MongoUtil.isNotEmptyOrNull(id))
				cr.and("_id").is(id);

			page = MongoUtil.getData(cr, User.class, hmap, pageable);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public UserDTO update(UserDTO dto) throws Exception {
		User user = repo.findOne(dto.getId());
		if (user == null)
			throw new Exception("User not found");
		user.setRecordStatus(Constants.RECORD_STATUS_UPDATED);
		user.setLastModifiedDate(new java.util.Date());
		user = dtoToDomain(dto, user);
		return DomainToDTO(repo.save(user), null);
	}

	public UserDTO delete(String id) throws Exception {
		User user = repo.findOne(id);
		if (user == null)
			throw new Exception("User not found");
		user.setRecordStatus(Constants.RECORD_STATUS_DELETED);
		user.setLastModifiedDate(new java.util.Date());
		return DomainToDTO(repo.save(user), null);
	}

	private User dtoToDomain(UserDTO dto, User user) {
		user.setAddress(new Address(dto.getAddress()));
		user.setAge(dto.getAge());
		user.setGrade(dto.getGrade());
		user.setName(dto.getName());
		user.setPhoneNos(dto.getPhoneNos());
		user.setRollNo(dto.getRollNo());
		user.setSalary(dto.getSalary());
		user.setUserId(dto.getUserId());
		return user;
	}

	private UserDTO DomainToDTO(User user, UserDTO dto) {
		if (dto == null)
			dto = new UserDTO();
		dto.setId(user.getId());
		dto.setAddress(new AddressDTO(user.getAddress()));
		dto.setAge(user.getAge());
		dto.setGrade(user.getGrade());
		dto.setName(user.getName());
		dto.setPhoneNos(user.getPhoneNos());
		dto.setRollNo(user.getRollNo());
		dto.setSalary(user.getSalary());
		dto.setUserId(user.getUserId());
		dto.setRecordStatus(user.getRecordStatus());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setLastModifiedDate(user.getLastModifiedDate());
		return dto;
	}

	public List<UserDTO> domainToDTOs(List<User> users) {
		List<UserDTO> dto = new ArrayList<UserDTO>();
		if (users == null || users.size() == 0)
			return dto;
		for (User user : users) {
			dto.add(DomainToDTO(user, null));
		}
		return dto;
	}

}
