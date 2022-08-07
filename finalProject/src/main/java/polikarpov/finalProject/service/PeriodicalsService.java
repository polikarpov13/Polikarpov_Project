package polikarpov.finalProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import polikarpov.finalProject.dao.PeriodicalRepository;
import polikarpov.finalProject.domain.Periodical;

@Service
public class PeriodicalsService {
	
	@Autowired
	private PeriodicalRepository periodicalRepository;
	
	public Periodical save(Periodical periodical) {
		return periodicalRepository.save(periodical);
	}
	
	public List<Periodical> getAllPeriodicals(){
		return periodicalRepository.findAll();
	}
	
	
	
}
