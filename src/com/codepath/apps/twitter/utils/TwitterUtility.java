package com.codepath.apps.twitter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.codepath.apps.twitter.R;

public class TwitterUtility {

	public static String formatDate(String relativeDateTimeString) {
		int indexD = relativeDateTimeString.indexOf(" day");
		int indexH = relativeDateTimeString.indexOf(" hour");
		int indexM = relativeDateTimeString.indexOf(" minute");

		if (indexD > 0) {
			relativeDateTimeString = relativeDateTimeString
					.substring(0, indexD) + "d";
		} else if (indexH > 0) {
			relativeDateTimeString = relativeDateTimeString
					.substring(0, indexH) + "h";
		} else if (indexM > 0) {
			relativeDateTimeString = relativeDateTimeString
					.substring(0, indexM) + "m";
		}

		return relativeDateTimeString;
	}

	public static void setConfiguration(Context context, long uid) {
		String key = context.getResources().getString(R.string.config_key);
		SharedPreferences spConfig = context.getSharedPreferences(key,
				Context.MODE_PRIVATE);
		Editor editor = spConfig.edit();
		long maxID = spConfig.getLong("maxID", Long.MAX_VALUE);
		long sinceID = spConfig.getLong("sinceID", Long.MIN_VALUE);
		if (uid < maxID) {
			editor.putLong("maxID", uid);
			editor.commit();
		}

		if (uid > sinceID) {
			editor.putLong("sinceID", uid);
			editor.commit();
		}
	}

}
