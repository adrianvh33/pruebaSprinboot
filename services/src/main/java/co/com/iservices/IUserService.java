package co.com.iservices;

import javax.validation.ConstraintViolationException;

import org.springframework.http.ResponseEntity;

import co.com.entity.request.UserRequestDTO;

public interface IUserService {
	
	public ResponseEntity<String> signup(UserRequestDTO user) throws ConstraintViolationException, Exception;

}
