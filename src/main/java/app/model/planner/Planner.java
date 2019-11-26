package app.model.planner;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public class Planner {
	public static final int PASSED_DAYS_MAX = 10000;
	public static final int PASSED_DAYS_MIN = 0;

	private static int calculateNextDays(
			ReviewValues value,
			int passedDays) {
		assert checkValue(value);
		assert checkPassedDaysLimits(passedDays);

		if (value.equals(ReviewValues.VALUE_0)) {
			return 0;
		}

		double daysNext = Math.floor(passedDays * value.getDaysMultiplier() + value.getDaysBase());
		return (int) Math.round(daysNext);
	}

	public static PlannerResult planNext(
			ReviewValues value,
			OffsetDateTime valueDate,
			OffsetDateTime previousDate) {
		if (!checkValue(value)) {
			throw new IllegalArgumentException();
		}

		if (valueDate == null) {
			throw new IllegalArgumentException();
		}

		int passedDays = 0;
		if (previousDate != null) {
			long tmp = previousDate.until(valueDate, ChronoUnit.DAYS);
			
			if (!checkPassedDaysLimits(tmp)) {
				throw new IllegalArgumentException();
			}
			
			passedDays = (int) tmp;
		}

		int nextDays = calculateNextDays(value, passedDays);
		OffsetDateTime nextDate = valueDate.plus(nextDays, ChronoUnit.DAYS);

		return new PlannerResult(
				nextDate,
				nextDays,
				passedDays);
	}

	private static boolean checkValue(ReviewValues value) {
		return value != null;
	}

	private static boolean checkPassedDaysLimits(long passedDays) {
		return PASSED_DAYS_MIN <= passedDays && passedDays <= PASSED_DAYS_MAX;
	}
}
