/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.powwow.provider.zoom;

import com.liferay.calendar.recurrence.Frequency;
import com.liferay.calendar.recurrence.PositionalWeekday;
import com.liferay.calendar.recurrence.Recurrence;
import com.liferay.calendar.recurrence.Weekday;
import com.liferay.calendar.util.JCalendarUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.powwow.provider.PowwowServiceProviderUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Tang Hieu Ha
 */
public class ZoomRecurrenceSerializer {

	public static String toJSONString(Recurrence recurrence, Calendar startTime) {

		if (Validator.isNull(recurrence)) {
			return StringPool.BLANK;
		}

		if (Validator.isNull(recurrence.getUntilJCalendar()) && recurrence.getCount() < 1) {
			return StringPool.BLANK;
		}

		Frequency frequency = recurrence.getFrequency();

		if (frequency.equals(Frequency.YEARLY)) {
			return StringPool.BLANK;
		}

		if(Validator.isNull(startTime)) {
			startTime = CalendarFactoryUtil.getCalendar();
		}

		JSONObject jsonRecurrence = JSONFactoryUtil.createJSONObject();

		List<Integer> weekdayNums = new ArrayList<>();

		List<PositionalWeekday> positionalWeekdays = recurrence.getPositionalWeekdays();

		if(Frequency.WEEKLY.equals(frequency)) {
			for (PositionalWeekday positionalWeekday : positionalWeekdays) {
				int zoomWeekday = _weekdayMap.get(positionalWeekday.getWeekday());

				weekdayNums.add(zoomWeekday);
			}

			jsonRecurrence.put("weekly_days", StringUtil.merge(weekdayNums));
		}
		else if (Frequency.MONTHLY.equals(frequency)) {
			if (!positionalWeekdays.isEmpty()) {

				// Zoom only supports one weekday in monthly recurrence

				PositionalWeekday positiionWeekday = positionalWeekdays.get(0);
				int zoomWeekday = _weekdayMap.get(positiionWeekday.getWeekday());

				jsonRecurrence.put("monthly_week_day", zoomWeekday);
				jsonRecurrence.put("monthly_week", positiionWeekday.getPosition());
			}
			else {
				Calendar startTimeUTC = JCalendarUtil.getJCalendar(startTime, TimeZone.getTimeZone(StringPool.UTC));
				jsonRecurrence.put("monthly_day", startTimeUTC.get(Calendar.DATE));
			}
		}

		jsonRecurrence.put("type", _frequencyMap.get(frequency));

		jsonRecurrence.put("repeat_interval", recurrence.getInterval());

		Calendar untilJCalendar = recurrence.getUntilJCalendar();

		if (Validator.isNotNull(untilJCalendar) && recurrence.getCount() < 1) {

			untilJCalendar.set(Calendar.HOUR, 23);
			untilJCalendar.set(Calendar.MINUTE, 59);
			untilJCalendar.set(Calendar.SECOND, 59);
			untilJCalendar.set(Calendar.MILLISECOND, 990);

			String zoomDateTimeUTC = PowwowServiceProviderUtil.toZoomDateTimeUTC(untilJCalendar);
			jsonRecurrence.put("end_date_time", zoomDateTimeUTC);
		}
		else {
			jsonRecurrence.put("end_times", recurrence.getCount());
		}

		return jsonRecurrence.toString();
	}

	private static final Map<Frequency, Integer> _frequencyMap = new HashMap<>();

	private static final Log _log = LogFactoryUtil.getLog(
		ZoomRecurrenceSerializer.class);

	private static final Map<Weekday, Integer> _weekdayMap = new HashMap<>();

	static {
		_frequencyMap.put(Frequency.DAILY, 1);
		_frequencyMap.put(Frequency.WEEKLY, 2);
		_frequencyMap.put(Frequency.MONTHLY, 3);

		_weekdayMap.put(Weekday.SUNDAY, 1);
		_weekdayMap.put(Weekday.MONDAY, 2);
		_weekdayMap.put(Weekday.TUESDAY, 3);
		_weekdayMap.put(Weekday.WEDNESDAY, 4);
		_weekdayMap.put(Weekday.THURSDAY, 5);
		_weekdayMap.put(Weekday.FRIDAY, 6);
		_weekdayMap.put(Weekday.SATURDAY, 7);
	}

}