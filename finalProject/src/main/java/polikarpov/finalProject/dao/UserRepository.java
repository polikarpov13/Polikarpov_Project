package polikarpov.finalProject.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import polikarpov.finalProject.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
	
}
