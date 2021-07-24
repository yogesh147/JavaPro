package com.javaPro.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.javaPro.address.Address;

@Service
public class UserService {

	@Inject
	private UserRepository repo;

	public UserDTO create(UserDTO dto) {
		User user = repo.save(new User(dto));
		return new UserDTO(user);
	}

	public List<UserDTO> read() {
		List<User> users = repo.findAll();
		List<UserDTO> dtos = new ArrayList<>();
		dtos = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return dtos;
	}

	public UserDTO update(UserDTO dto) throws Exception {
		User user = repo.findOne(dto.getId());
		user = dtoTODomain(dto, user);
		return new UserDTO(repo.save(user));
	}

	private User dtoTODomain(UserDTO dto, User user) throws Exception {
		if (user == null)
			throw new Exception("User not found");
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

	public UserDTO delete(String id) throws Exception {
		User user = repo.findOne(id);
		if (user == null)
			throw new Exception("User not found");
		repo.delete(id);
		return new UserDTO(user);
	}
}
