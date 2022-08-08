package polikarpov.finalProject.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polikarpov.finalProject.dao.PeriodicalRepository;
import polikarpov.finalProject.domain.Periodical;

@Service
public class PeriodicalsService {
	
	private Logger logger = LoggerFactory.getLogger(PeriodicalsService.class);
	
	@Autowired
	private PeriodicalRepository periodicalRepository;
	
	public Periodical save(Periodical periodical) {
		logger.debug("Create periodical item : " + periodical);
		return periodicalRepository.save(periodical);
	}
	
	public List<Periodical> getAllPeriodicals(){
		logger.debug("Get all periodical items");
		return periodicalRepository.findAll();
	}
	
	public Periodical findById(Integer id) {
		logger.debug("Get periodical item by id : " + id);
		return periodicalRepository.findById(id).get();
	}
	
}
