package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.model.planner.ReviewValues;

@Component
public class ReviewValuesToStringConverter implements Converter<ReviewValues, String> {
	@Override
	public String convert(ReviewValues source) {
		return Integer.toString(source.ordinal());
	}
}
