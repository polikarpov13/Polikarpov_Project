package polikarpov.finalProject.test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import polikarpov.finalProject.dao.BucketRepository;
import polikarpov.finalProject.dao.PeriodicalRepository;
import polikarpov.finalProject.dao.UserRepository;
import polikarpov.finalProject.domain.Bucket;
import polikarpov.finalProject.domain.Periodical;
import polikarpov.finalProject.domain.User;
import polikarpov.finalProject.domain.UserRole;
import polikarpov.finalProject.service.BucketService;
import polikarpov.finalProject.service.PeriodicalsService;
import polikarpov.finalProject.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
//@DataJpaTest
public class ApplicationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private PeriodicalsService periodicalsService;

	@Autowired
	private BucketService bucketService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PeriodicalRepository periodicalRepository;

	@Autowired
	private BucketRepository bucketRepository;

	@Test
	public void testSaveUser() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User userFromDb = users.get(0);
		assertTrue(userFromDb.getEmail().equals(user.getEmail()));
		assertTrue(userFromDb.getFirstName().equals(user.getFirstName()));
		assertTrue(userFromDb.getLastName().equals(user.getLastName()));
		assertTrue(userFromDb.getRole().equals(user.getRole()));
	}

	@Test
	public void testFindByEmail() {
		List<User> users = userRepository.findAll();
		assertThat(users, hasSize(0));

		User user = new User();
		user.setEmail("myCustomEmail@gmail.com");
		user.setFirstName("2");
		user.setLastName("2");
		user.setPassword("2");
		user.setPasswordConfirm("2");
		user.setRole(UserRole.ROLE_USER);

		userRepository.save(user);

		users = userRepository.findAll();
		assertThat(users, hasSize(1));

		User findByEmail = userService.findByEmail(user.getEmail());

		assertTrue(findByEmail.getEmail().equals(user.getEmail()));
		assertTrue(findByEmail.getFirstName().equals(user.getFirstName()));
		assertTrue(findByEmail.getLastName().equals(user.getLastName()));
		assertTrue(findByEmail.getRole().equals(user.getRole()));
	}

	@Test
	public void testSavePeriodical() {
		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		periodicalsService.save(periodical);

		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(1));

		Periodical periodicalFromDb = periodicals.get(0);
		assertTrue(periodicalFromDb.getName().equals(periodical.getName()));
		assertTrue(periodicalFromDb.getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromDb.getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromDb.getPrice().equals(periodical.getPrice()));
	}

	@Test
	public void testFindById() {
		List<Periodical> periodicals = periodicalRepository.findAll();
		periodicals.removeAll(periodicals);
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		periodicalRepository.save(periodical);

		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(1));

		Periodical periodicalFromDb = periodicalsService.findById(periodicals.get(0).getId());

		assertTrue(periodicalFromDb.getName().equals(periodical.getName()));
		assertTrue(periodicalFromDb.getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalFromDb.getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalFromDb.getPrice().equals(periodical.getPrice()));
	}

	@Test
	public void testGetAllPeriodicals() {
		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		Periodical periodical2 = new Periodical();
		periodical2.setName("12");
		periodical2.setDescription("12");
		periodical2.setEncodedImage("12");
		periodical2.setPrice(12d);

		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2));

		periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(2));

		List<Periodical> periodicalsFromDb = periodicalsService.getAllPeriodicals();

		assertTrue(periodicalsFromDb.get(0).getName().equals(periodical.getName()));
		assertTrue(periodicalsFromDb.get(0).getDescription().equals(periodical.getDescription()));
		assertTrue(periodicalsFromDb.get(0).getEncodedImage().equals(periodical.getEncodedImage()));
		assertTrue(periodicalsFromDb.get(0).getPrice().equals(periodical.getPrice()));

		assertTrue(periodicalsFromDb.get(1).getName().equals(periodical2.getName()));
		assertTrue(periodicalsFromDb.get(1).getDescription().equals(periodical2.getDescription()));
		assertTrue(periodicalsFromDb.get(1).getEncodedImage().equals(periodical2.getEncodedImage()));
		assertTrue(periodicalsFromDb.get(1).getPrice().equals(periodical2.getPrice()));
	}

	@Test
	public void testAddToBucket() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDb = userRepository.findAll().stream().findFirst().get();

		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		periodicalsService.save(periodical);

		Periodical periodicalFromDb = periodicalRepository.findAll().stream().findFirst().get();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setPeriodical(periodicalFromDb);
		bucket.setUser(userFromDb);
		bucket.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketService.add(bucket);

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));

		Bucket bucketFromDb = buckets.get(0);

		assertTrue(bucketFromDb.getPeriodical().equals(periodicalFromDb));
		assertTrue(bucketFromDb.getUser().equals(userFromDb));
		assertTrue(bucketFromDb.getPurchaseDate().equals(now));
	}

	@Test
	public void testDeleteFromBucket() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDb = userRepository.findAll().stream().findFirst().get();

		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		Periodical periodical2 = new Periodical();
		periodical.setName("12");
		periodical.setDescription("12");
		periodical.setEncodedImage("12");
		periodical.setPrice(12d);

		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2));

		List<Periodical> periodicalsFromDb = periodicalRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setPeriodical(periodicalsFromDb.get(0));
		bucket.setUser(userFromDb);
		bucket.setPurchaseDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setPeriodical(periodicalsFromDb.get(1));
		bucket2.setUser(userFromDb);
		bucket2.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(2));

		bucketService.delete(buckets.get(0));

		buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(1));
	}

	@Test
	public void testGetAll() {
		User user = new User();
		user.setEmail("1@gmail.com");
		user.setFirstName("1");
		user.setLastName("1");
		user.setPassword("1");
		user.setPasswordConfirm("1");
		user.setRole(UserRole.ROLE_USER);

		userService.save(user);

		User userFromDb = userRepository.findAll().stream().findFirst().get();

		List<Periodical> periodicals = periodicalRepository.findAll();
		assertThat(periodicals, hasSize(0));

		Periodical periodical = new Periodical();
		periodical.setName("1");
		periodical.setDescription("1");
		periodical.setEncodedImage("1");
		periodical.setPrice(1d);

		Periodical periodical2 = new Periodical();
		periodical.setName("12");
		periodical.setDescription("12");
		periodical.setEncodedImage("12");
		periodical.setPrice(12d);

		periodicalRepository.saveAll(Arrays.asList(periodical, periodical2));

		List<Periodical> periodicalsFromDb = periodicalRepository.findAll();

		Date now = new Date();
		Bucket bucket = new Bucket();
		bucket.setPeriodical(periodicalsFromDb.get(0));
		bucket.setUser(userFromDb);
		bucket.setPurchaseDate(now);

		Bucket bucket2 = new Bucket();
		bucket2.setPeriodical(periodicalsFromDb.get(1));
		bucket2.setUser(userFromDb);
		bucket2.setPurchaseDate(now);

		List<Bucket> buckets = bucketRepository.findAll();
		assertThat(buckets, hasSize(0));

		bucketRepository.saveAll(Arrays.asList(bucket, bucket2));

		List<Bucket> bucketsFromDb = bucketService.getAll();
		assertThat(bucketsFromDb, hasSize(2));
	}

}
