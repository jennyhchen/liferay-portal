package com.liferay.powwow.occurrence;

/**
 * @author Vu Ho
 */
public enum OccurrenceStatus {

	AVAILABLE("available"), DELETE("deleted"), COMPLETED("completed");

	public static OccurrenceStatus parse(String value) {

		if (AVAILABLE.getValue().equals(value)) {
			return AVAILABLE;
		}

		if (DELETE.getValue().equals(value)) {
			return DELETE;
		}

		if (COMPLETED.getValue().equals(value)) {
			return COMPLETED;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public final String getValue() {

		return _value;
	}

	@Override
	public String toString() {

		return _value;
	}

	private OccurrenceStatus(String value) {

		_value = value;
	}

	private final String _value;
}
