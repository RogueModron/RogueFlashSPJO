package app.controllers;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.model.Deck;
import app.services.DecksService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestDecksController {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private DecksService decksService;
	
	@Test
	public void testGetDecks() throws IOException {
		Deck deck = Deck.builder().description("Test Get Decks").build();
		decksService.createDeck(deck);
		
		ResponseEntity<String> response = restTemplate.getForEntity(
				"/decks",
				String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(response.getBody());
		assertTrue(json.isArray());
	}
	
	@Test
	public void testGetDeckSummaries() throws IOException {
		Deck deck = Deck.builder().description("Test Get Deck Summaries").build();
		decksService.createDeck(deck);
		
		ResponseEntity<String> response = restTemplate.getForEntity(
				"/decks/summaries",
				String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(response.getBody());
		assertTrue(json.isArray());
	}
	
	@Test
	public void testGetDeck() {
		Deck deck = Deck.builder().description("Test Get Deck").build();
		deck = decksService.createDeck(deck);
		
		ResponseEntity<String> response = restTemplate.getForEntity(
				"/decks/" + deck.getId(),
				String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test
	public void testCreateDeck() {
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("description", "testCreateDeck");
		
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts);
		
		ResponseEntity<String> response = restTemplate.postForEntity(
				"/decks",
				request,
				String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.CREATED));
	}
	
	@Test
	public void testUpdateDeck() {
		Deck deck = Deck.builder().description("Test Update Deck").build();
		deck = decksService.createDeck(deck);
		
		MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
		parts.add("description", "testUpdateDeck");
		parts.add("notes", "testUpdateDeck");
		
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(parts);
		
		ResponseEntity<String> response = restTemplate.exchange(
				"/decks/" + deck.getId(),
				HttpMethod.PUT,
				request,
				String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
	}
	
	@Test
	public void testDeleteDeck() {
		Deck deck = Deck.builder().description("Test Delete Deck").build();
		deck = decksService.createDeck(deck);
		
		ResponseEntity<String> response = restTemplate.exchange(
				"/decks/" + deck.getId(),
				HttpMethod.DELETE,
				null,
				String.class);
		
		assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
}
