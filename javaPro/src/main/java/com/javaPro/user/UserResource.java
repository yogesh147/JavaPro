package com.javaPro.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaPro.util.MongoUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
public class UserResource {
	private static final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Inject
	private UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
		log.info("On Saving User ::");
		try {
			UserDTO userDto = userService.create(dto);
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserDTO>(dto, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllUser(HttpServletRequest httpReq, Pageable pageable) {
		log.info("On Getting User ::");
		try {
			Map<String, String> hmap = new HashMap<>();

			hmap.put("id", httpReq.getHeader("id"));
			hmap.put("fields", httpReq.getHeader("fields"));
			Page<User> page = userService.getAllUsers(hmap, pageable);

			HttpHeaders headers = MongoUtil.getPaginationHeader(page, "api/user");
			return new ResponseEntity<>(userService.domainToDTOs(page.getContent()), headers, HttpStatus.OK);
		} catch (Exception e) {
			log.info("On Getting User ::", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO dto) throws Exception {
		log.info("On Updating User ::");
		UserDTO userDto = userService.update(dto);
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity<UserDTO> delete(@RequestParam String id) throws Exception {
		log.info("On Deleting User ::");
		UserDTO userDto = userService.delete(id);
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}

}
