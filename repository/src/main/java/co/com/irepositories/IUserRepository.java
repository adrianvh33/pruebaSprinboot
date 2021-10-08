package co.com.irepositories;

import co.com.entity.entities.UserEntity;

public interface IUserRepository {
	
	public UserEntity byEmail (String email);
	
	public UserEntity create (UserEntity userToSave);
}
