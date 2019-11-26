package app.ws;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import app.WsConfiguration;
import app.model.Deck;
import app.services.DecksService; 

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestDecksEndpoint {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private DecksService decksService;
	
	private MockWebServiceClient mockClient;
	
	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}
	
	@Test
	public void testGetDeck() throws Exception {
		Deck deck = Deck.builder().description("Test getDecks WS").build();
		deck = decksService.createDeck(deck);
		
		Source requestPayload = new StringSource(
				"<ws:getDeckRequest xmlns:ws='" + WsConfiguration.NAMESPACE +"'>" +
						"<ws:id>" + deck.getId() + "</ws:id>" +
				"</ws:getDeckRequest>");
		
		mockClient
				.sendRequest(withPayload(requestPayload))
				.andExpect(noFault());
	}
}
