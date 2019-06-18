package com.liferay.powwow.occurrence;

import java.util.Objects;

/**
 * @author Vu Ho
 */
public enum OccurrenceStatus {

	AVAILABLE("available"), DELETE("deleted"), COMPLETED("completed");

	public static OccurrenceStatus parse(String value) {
		if (Objects.equals(AVAILABLE.getValue(), value)) {
			return AVAILABLE;
		}
		else if (Objects.equals(DELETE.getValue(), value)) {
			return DELETE;
		}
		else if (Objects.equals(COMPLETED.getValue(), value)) {
			return COMPLETED;
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public String getValue() {

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
