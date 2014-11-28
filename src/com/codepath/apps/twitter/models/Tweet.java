package com.codepath.apps.twitter.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {

	private static final String twitterDateFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			twitterDateFormat);

	private long createdAt;
	private long uid;
	private String body;
	private User user;

	public static Tweet fromJSON(JSONObject jsonObject) {
		Date date;
		Tweet tweet = new Tweet();

		try {
			date = sdf.parse(jsonObject.getString("created_at"));

			tweet.uid = jsonObject.getLong("id");
			tweet.body = jsonObject.getString("text");
			tweet.createdAt = date.getTime();
			tweet.user = User.fromJSON(jsonObject.getJSONObject("user"));
		} catch (JSONException e1) {
			e1.printStackTrace();
			tweet = null;
		} catch (ParseException e2) {
			e2.printStackTrace();
			tweet = null;
		}

		return tweet;
	}

	public static ArrayList<Tweet> fromJSONArray(JSONArray json) {
		sdf.setLenient(true);
		ArrayList<Tweet> tweetList = new ArrayList<Tweet>();

		for (int i = 0; i < json.length(); i++) {
			JSONObject jsonObject = null;
			try {
				jsonObject = json.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Tweet tweet = Tweet.fromJSON(jsonObject);
			if (tweet != null) {
				tweetList.add(tweet);
			}
		}

		return tweetList;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public long getUid() {
		return uid;
	}

	public String getBody() {
		return body;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return this.body + " - " + this.user.getScreenName();
	}

}
