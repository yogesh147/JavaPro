package com.javaPro.user;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserResource {
	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Inject
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		log.info("On Saving User ::");
		UserDTO userDto = userService.create(dto);
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> get() {
		log.info("On Getting User ::");
		List<UserDTO> userDtos = userService.read();
		return new ResponseEntity<List<UserDTO>>(userDtos, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UserDTO> get(@RequestBody UserDTO dto) throws Exception {
		log.info("On Updating User ::");
		UserDTO userDto = userService.update(dto);
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity<UserDTO> get(@RequestParam String id) throws Exception {
		log.info("On Deleting User ::");
		UserDTO userDto = userService.delete(id);
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}

}
