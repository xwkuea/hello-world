package com.gamfe.chen.util;

import java.util.Calendar;

public class TimeUtil {

	public static String getTime() {
		String timeStr = "";
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		if (hour < 10) {
			timeStr += '0';
		}
		timeStr += hour + ":";
		if (minute < 10) {
			timeStr += '0';
		}
		timeStr += minute+":";
		if (second < 10) {
			timeStr += '0';
		}
		timeStr += second;
		return timeStr;

	}

}
