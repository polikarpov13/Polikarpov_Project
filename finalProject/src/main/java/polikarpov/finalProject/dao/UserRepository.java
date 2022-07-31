package polikarpov.finalProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import polikarpov.finalProject.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByEmail(String email);
	
}
