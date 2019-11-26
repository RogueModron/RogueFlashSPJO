package app.controllers.params;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class FilterParams implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@NotNull
	private String descriptionFilter = "";
	
	@Getter
	@Setter
	@PositiveOrZero
	private int firstResult = 0;
	
	@Getter
	@Setter
	@Min(1)
	@Max(100)
	private int maxResults = 10;
}
