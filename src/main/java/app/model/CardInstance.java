package app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class CardInstance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter
	private Integer cardId;
	
	@Getter
	private boolean sideAToB;
	
	@Getter
	private boolean disabled;
}
