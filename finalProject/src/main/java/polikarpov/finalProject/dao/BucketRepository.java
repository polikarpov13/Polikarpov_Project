package polikarpov.finalProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import polikarpov.finalProject.domain.Bucket;

public interface BucketRepository extends JpaRepository<Bucket, Integer>{
	
	
	
}
