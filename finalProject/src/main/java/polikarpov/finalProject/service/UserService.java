package polikarpov.finalProject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import polikarpov.finalProject.dao.UserRepository;
import polikarpov.finalProject.domain.User;
import polikarpov.finalProject.domain.UserRole;

@Service
public class UserService{
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public void save(User user) {
    	logger.debug("Create user object : " + user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        user.setRole(UserRole.ROLE_USER);
        userRepository.save(user);
    }
    
    public User findByEmail(String email) {
    	logger.debug("Get user object by email : " + email);
    	return userRepository.findByEmail(email).get();
    }

}
