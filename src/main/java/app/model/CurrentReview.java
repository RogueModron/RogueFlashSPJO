package app.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class CurrentReview implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter
	private Integer deckId;
	
	@Getter
	private Integer cardInstanceId;
	
	@Getter
	private OffsetDateTime lastDateTime;
}
