package org.novdev.library.service;

import java.util.List;
import java.util.Optional;

import org.novdev.library.domain.User;
import org.novdev.library.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger( UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		logger.info("Create user's card number :" + user);
		user.setCardNumber(createCardNumber(user));
		logger.info("Save user item:" + user);
		return userRepository.save(user);
	}
	
	public User readById(Integer id) {
		logger.info("Find user by id:" + id);
		User user = new User();
		Optional<User> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()) {
			user = optionalUser.get();
		}
		return user;
	}
	
	public void update(User user, String name, String surname, Integer birthYear) {
		logger.info("Update user:" + user);
		if(!userRepository.getById(user.getId()).equals(null)) {
			User userDB = userRepository.getById(user.getId());
			userDB.setName(name);
			userDB.setSurname(surname);
			userDB.setBirthYear(birthYear);
			userDB.setCardNumber(createCardNumber(userDB));
			logger.info("Update user item:" + user + "to " + userDB);
			userRepository.save(userDB);
		}else {
			throw new NullPointerException( user +  "this user don`t find in DB");
		}

	}
	
	public void delete(User user) {
		logger.info("Delete user item:" + user);
		userRepository.delete(user);
	}
	
	public List<User> getAll(){
		logger.info("Get all users items");
		return userRepository.findAll();
	}
	
	private String createCardNumber(User user) {
		String cardNumber = user.getId().toString() + user.getName().charAt(0) + user.getSurname().charAt(0) + user.getBirthYear().toString();
		return cardNumber;
	}
}
