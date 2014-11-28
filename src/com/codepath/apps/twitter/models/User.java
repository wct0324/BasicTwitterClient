package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	private long uid;
	private String name;
	private String profileImageURL;
	private String screenName;

	public static User fromJSON(JSONObject jsonObject) {
		User user = new User();

		try {
			user.uid = jsonObject.getLong("id");
			user.name = jsonObject.getString("name");
			user.profileImageURL = jsonObject.getString("profile_image_url");
			user.screenName = jsonObject.getString("screen_name");
		} catch (JSONException e) {
			e.printStackTrace();
			user = null;
		}

		return user;
	}

	public long getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getProfileImageURL() {
		return profileImageURL;
	}

	public String getScreenName() {
		return screenName;
	}

}
