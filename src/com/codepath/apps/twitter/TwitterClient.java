package com.codepath.apps.twitter;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.content.SharedPreferences;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class TwitterClient extends OAuthBaseClient {

	public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class;

	public static final String REST_URL = "https://api.twitter.com/1.1";
	public static final String REST_CONSUMER_KEY = "rlgNwIRQK7QvedcCfFKAQi9dY";
	public static final String REST_CONSUMER_SECRET = "sYXb3uhCQuDaorK5iZETRoRKbt9tshvBa5easZx1sZwNkdxuVF";
	public static final String REST_CALLBACK_URL = "oauth://cpbasictweets";

	public RequestParams params;

	public TwitterClient(Context context) {
		super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY,
				REST_CONSUMER_SECRET, REST_CALLBACK_URL);
	}

	public void getHomeTimeLine(AsyncHttpResponseHandler handler,
			SharedPreferences spConfig) {
		String apiUrl = this.getApiUrl("statuses/home_timeline.json");

		long maxID = spConfig.getLong("maxID", Long.MAX_VALUE);
		long sinceID = spConfig.getLong("sinceID", Long.MIN_VALUE);
		String count = this.context.getResources().getString(
				R.string.twitter_time_line_count);

		this.params = new RequestParams();
		this.params.put("count", count);
		if (maxID < Long.MAX_VALUE) {
			this.params.put("max_id", Long.toString(maxID));
		}

		client.get(apiUrl, this.params, handler);
	}

	public void getUser(AsyncHttpResponseHandler handler) {
		String apiUrl = this.getApiUrl("users/show.json");

		String screenName = this.context.getResources().getString(
				R.string.twitter_screen_name);

		this.params = new RequestParams();
		this.params.put("screen_name", screenName);

		client.get(apiUrl, this.params, handler);
	}

	public void postTweet(AsyncHttpResponseHandler handler, String status) {
		String apiUrl = this.getApiUrl("statuses/update.json");

		this.params = new RequestParams();
		this.params.put("status", status);

		client.post(apiUrl, this.params, handler);
	}

}