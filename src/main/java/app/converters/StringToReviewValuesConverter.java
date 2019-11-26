package app.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import app.model.planner.ReviewValues;

@Component
public class StringToReviewValuesConverter implements Converter<String, ReviewValues> {
	@Override
	public ReviewValues convert(String source) {
		int ordinal = 0;
		try {
			ordinal = Integer.parseUnsignedInt(source);
		} catch (NumberFormatException e) {
			//
		}
		return ReviewValues.getFromOrdinal(ordinal);
	}
}
