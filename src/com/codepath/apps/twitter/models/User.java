package com.codepath.apps.twitter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

	private int following;
	private int followers;
	private long uid;
	private String name;
	private String profileImageURL;
	private String screenName;
	private String description;

	public static User fromJSON(JSONObject jsonObject) {
		User user = new User();

		try {
			user.following = jsonObject.getInt("followers_count");
			user.followers = jsonObject.getInt("friends_count");
			user.uid = jsonObject.getLong("id");
			user.name = jsonObject.getString("name");
			user.profileImageURL = jsonObject.getString("profile_image_url");
			user.screenName = jsonObject.getString("screen_name");
			user.description = jsonObject.getString("description");
		} catch (JSONException e) {
			e.printStackTrace();
			user = null;
		}

		return user;
	}

	public int getFollowing() {
		return following;
	}

	public int getFollowers() {
		return followers;
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

	public String getDescription() {
		return description;
	}

}
