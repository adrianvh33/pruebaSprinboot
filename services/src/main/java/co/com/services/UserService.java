package co.com.services;

import java.util.Objects;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.common.encrypt.Encryption;
import co.com.common.exception.NetC2ServiceException;
import co.com.common.messages.MessageUtils;
import co.com.common.response.FinalResponse;
import co.com.common.response.IResponse;
import co.com.common.utils.Utils;
import co.com.entity.entities.UserEntity;
import co.com.entity.request.UserRequestDTO;
import co.com.irepositories.IUserRepository;
import co.com.iservices.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired  IUserRepository userRepository;
	@Autowired FinalResponse finalResponse;
	@Autowired MessageUtils messages;
	
	IResponse response;
	
	HttpHeaders responseHeaders = new HttpHeaders();
	
	@Override
	public ResponseEntity<String> signup(UserRequestDTO user) throws ConstraintViolationException, Exception {
		
		try {
			UserEntity userSearch = userRepository.byEmail(user.getCorreo());
			if( Objects.isNull(userSearch)) {
				
				UserEntity  userToSave = UserEntity.builder()
											.nombre(user.getNombre() )
											.correo(user.getCorreo() )
											.password(Encryption.encrypt(user.getPassword()) )
											.role(user.getRole())
											.apellido(user.getApellido())
											.carrera(user.getCarrera())
											.id_usuario(user.getId_usuario())
											.telefono(user.getTelefono())
											.build();
				UserEntity userSaved = userRepository.create(userToSave);
				
				if(Objects.nonNull(userSaved)) {
					String token = Utils.encodeAdditionalJWTToken(userSaved.getCorreo(), userSaved.getNombre(), userSaved.getId_usuario(), userSaved.getRole());
					responseHeaders.add("Authorization", "Bearer"+token);
					response = finalResponse.getResponse(messages.getMessage("USER_CREATED_SUCCESS", userSaved.getNombre()), responseHeaders, HttpStatus.OK);
				}else {
					
					throw new NetC2ServiceException(messages.getMessage("USER_CREATED_UNSUCESS",user.getNombre()),HttpStatus.CONFLICT);
					
				}
			}else {
				throw new NetC2ServiceException(messages.getMessage("USER_EXISTS",user.getCorreo()),HttpStatus.CONFLICT);
			}
		}catch(ConstraintViolationException e) {
			throw new NetC2ServiceException(e.getMessage(),HttpStatus.CONFLICT);
		}
		
		return null;
	}

	
	
}
