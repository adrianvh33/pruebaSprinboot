package co.com.contoller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.com.entity.request.UserRequestDTO;
import co.com.iservices.IUserService;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired private IUserService userService;
	
	@RequestMapping(value="signup", method = RequestMethod.POST)
	public ResponseEntity<String> sinup( @RequestBody(required=true) UserRequestDTO userInformation ) throws ConstraintViolationException, Exception {
		return userService.signup(userInformation);
	}

}
