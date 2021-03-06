package com.capgemini.jpa.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import com.capgemini.jpa.repositories.EventRepository;
import com.capgemini.jpa.services.ServerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.jpa.entities.Event;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Task2 {

	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void shouldFindOneEntryBetweenDatesThatMustBeAnalyzed() throws Exception {
		// given
		LocalDateTime start = LocalDateTime.of(2018, 4, 9, 3, 25);
		LocalDateTime end = LocalDateTime.of(2018, 4, 9, 3, 26);
		boolean toBeAnalyzed = false;
		
		// when
		List<Event> result = eventRepository.findAllByTimeBetweenAndAnalysisRequired(start, end, toBeAnalyzed);

		// then
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}
