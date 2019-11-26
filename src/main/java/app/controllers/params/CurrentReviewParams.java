package app.controllers.params;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import app.model.planner.ReviewValues;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CurrentReviewParams implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotNull
	private int currentReviewId = 0;
	
	@Getter
	@Setter
	@NotNull
	private ReviewValues value = null;
}
