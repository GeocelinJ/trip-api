package com.sirius.trip.tripapi.utils;

/**
 * 
 * @author georg
 *
 */
public class TripApiUtils {

	private TripApiUtils() {
		throw new RuntimeException("Private Util");
	}

	public static String toString(Object o) {

		if (o == null) {
			return null;
		} else if (o instanceof String) {
			return String.class.cast(o);
		}

		throw new IllegalArgumentException("<<< Supports only string casting <<<");

	}

	public static Integer toInteger(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof Integer) {
			return Integer.class.cast(o);
		}else if (o instanceof String) {
			return Integer.parseInt(String.class.cast(o));
		}

		throw new IllegalArgumentException("<<< Supports only integer casting <<<");
	}
}
