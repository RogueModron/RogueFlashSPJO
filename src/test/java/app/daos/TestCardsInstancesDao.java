package app.daos;

import java.time.OffsetDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.daos.CardInstancesDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCardsInstancesDao {
	@Autowired
	private CardInstancesDao cardsInstancesDao;
	
	@Test(expected = Test.None.class)
	public void countCardsInstancesToReview() {
		cardsInstancesDao.countCardsInstancesToReview(2, OffsetDateTime.now());
	}
	
	@Test(expected = Test.None.class)
	public void getNextCardInstanceToReview() {
		cardsInstancesDao.getNextCardInstanceToReview(2, OffsetDateTime.now());
	}
}
