package app.model.views;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CardSummary implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int cardId = 0;
	
	@Getter
	private String sideA = "";

	@Getter
	private String sideB = "";
	
	@Getter
	private String notes = "";
	
	@Getter
	private String tags = "";
	
	@Getter
	private OffsetDateTime lastDate = null;
}
