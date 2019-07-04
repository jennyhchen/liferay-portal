package com.liferay.powwow.occurrence;

/**
 * @author Vu Ho
 */
public enum OccurrenceStatus {

		AVAILABLE("available", "scheduled"), DELETE("deleted", "deleted"),
		COMPLETED("completed", "completed");

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

	public final String getLanguageKey() {

		return _languageKey;
	}

	public final String getValue() {

		return _value;
	}

	@Override
	public String toString() {

		return _value;
	}

	private OccurrenceStatus(String value, String languageKey) {

		_languageKey = languageKey;
		_value = value;
	}

	private final String _languageKey;
	private final String _value;
}
