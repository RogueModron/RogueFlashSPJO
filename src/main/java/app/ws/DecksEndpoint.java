package app.ws;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import app.WsConfiguration;
import app.model.Deck;
import app.services.DecksService;
import app.ws.schema.GetDeckRequest;
import app.ws.schema.GetDeckResponse;
import app.ws.schema.ObjectFactory;

@Endpoint
public class DecksEndpoint {
	@Autowired
	private DecksService decksService;
	
	
	@PayloadRoot(
			namespace = WsConfiguration.NAMESPACE,
			localPart = "getDeckRequest")
	@ResponsePayload
	public GetDeckResponse getDeck(@RequestPayload GetDeckRequest request) {
		Optional<Deck> optionalDeck = decksService.getDeck(request.getId());
		
		if (!optionalDeck.isPresent()) {
			throw new RuntimeException("Deck not found.");
		}
		
		ObjectFactory factory = new ObjectFactory();
		app.ws.schema.Deck deckForResponse = factory.createDeck();
		
		Deck deck = optionalDeck.get();
		deckForResponse.setDescription(deck.getDescription());
		deckForResponse.setId(deck.getId());
		deckForResponse.setNotes(deck.getNotes());
		
		GetDeckResponse response = factory.createGetDeckResponse();
		response.setDeck(deckForResponse);
		
		return response;
	}
}
