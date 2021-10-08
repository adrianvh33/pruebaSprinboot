package co.com.entity.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Document("usuarios")
public class UserEntity {
	
	@Id
	private String id_usuario;
	
	@NotBlank(message="field can't be blank")
	private String nombre;
	
	@NotBlank(message="field can't be blank")
	private String apellido;
	
	@NotBlank(message="field can't be blank")
	@Email(message="field must be a valid email")
	@Indexed(unique=true) 
	private String correo;
	
	@NotBlank(message="field can't be blank")
	@Length(min=5,message="field must be at least 5 characters")
	private String password;
	
	@NotBlank(message="field can't be blank")
	private String role;
	
	@NotBlank(message="field can't be blank")
	private int telefono;
	
	@NotBlank(message="field can't be blank")
	private String carrera;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private String fecha_ingreso;
	
	/*private int[] id_proyectos;*/

}
