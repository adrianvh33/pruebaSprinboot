package co.com.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import co.com.entity.entities.UserEntity;
import co.com.irepositories.IUserRepository;

@Repository
public class UserRepository  implements IUserRepository{
	
	@Autowired MongoTemplate mongoTemplate;

	@Override
	public UserEntity byEmail(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("correo").is(email));
		return mongoTemplate.findOne(query,UserEntity.class, "usuarios") ;
	}

	@Override
	public UserEntity create(UserEntity userToSave) {
		return mongoTemplate.insert(userToSave);
	}
	
	
}
