package co.com.entity.request;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	private String id_usuario;
	private String nombre;
	private String apellido;
	private String correo;
	private String password;
	private String role;
	private int telefono;
	private String carrera;
	private String fecha_ingreso;
	/*private int[] id_proyectos;*/
	
}
