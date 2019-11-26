package app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter
	private int version;
	
	@Getter
	private Integer deckId;
	
	@Builder.Default
	@Getter
	private String sideA = "";
	
	@Builder.Default
	@Getter
	private String sideB = "";
	
	@Builder.Default
	@Getter
	private String notes = "";
	
	@Builder.Default
	@Getter
	private String tags = "";
}
