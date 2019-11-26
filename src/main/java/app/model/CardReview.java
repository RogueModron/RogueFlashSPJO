package app.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import app.model.planner.ReviewValues;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
public class CardReview implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private Integer id;
	
	@Getter
	private Integer cardInstanceId;
	
	@Builder.Default
	@Getter
	private ReviewValues value = ReviewValues.VALUE_0;
	
	@Getter
	private OffsetDateTime dateTime;
}
