package app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class Deck implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;

	@Getter
	private int version;

	@Builder.Default
	@Getter
	private String description = "";
	
	@Builder.Default
	@Getter
	private String notes = "";
}
