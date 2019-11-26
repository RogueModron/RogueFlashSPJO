package app.model.views;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CurrentReviewDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private int currentReviewId = 0;
	
	@Getter
	private String sideA = "";
	
	@Getter
	private String sideB = "";
	
	@Getter
	private String notes = "";
}
