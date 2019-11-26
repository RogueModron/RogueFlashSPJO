package app.model.planner;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PlannerResult implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private OffsetDateTime nextDate;
	
	@Getter
	@Setter
	private int nextDays;

	@Getter
	@Setter
	private int passedDays;
}
