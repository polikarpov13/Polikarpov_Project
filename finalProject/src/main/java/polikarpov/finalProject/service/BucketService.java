package polikarpov.finalProject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polikarpov.finalProject.dao.BucketRepository;
import polikarpov.finalProject.domain.Bucket;

@Service
public class BucketService {
	
	private Logger logger = LoggerFactory.getLogger(BucketService.class);

	@Autowired
	private BucketRepository bucketRepository;

	public List<Bucket> getAll() {
		logger.debug("Get all bucket items");
		return bucketRepository.findAll();
	}

	public void delete(Bucket bucket) {
		logger.debug("Delete bucket : " + bucket);
		bucketRepository.delete(bucket);
	}

	public Bucket add(Bucket bucket) {
		logger.debug("Create bucket item : " + bucket);
		return bucketRepository.save(bucket);
	}
}
