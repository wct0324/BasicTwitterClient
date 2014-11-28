package com.codepath.apps.twitter.activities;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.util.Log;
import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApplication;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.adapter.TweetArrayAdapter;
import com.codepath.apps.twitter.listeners.EndlessScrollListener;
import com.codepath.apps.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimeLineActivity extends Activity {

	private ArrayAdapter<Tweet> tweetAdapter;
	private ArrayList<Tweet> tweetList;
	private ListView lvTweets;
	private SharedPreferences spConfig;
	private TwitterClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);

		String key = this.getResources().getString(R.string.config_key);
		this.spConfig = this.getSharedPreferences(key, Context.MODE_PRIVATE);
		this.spConfig.edit().clear().commit();

		this.client = TwitterApplication.getRestClient();
		this.populateTimeLine();

		this.tweetList = new ArrayList<Tweet>();
		this.tweetAdapter = new TweetArrayAdapter(this, this.tweetList);
		this.lvTweets = (ListView) this.findViewById(R.id.lvTweets);
		this.lvTweets.setAdapter(this.tweetAdapter);

		this.lvTweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int a, int b) {
				populateTimeLine();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.tweet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuTweet:
			Intent i = new Intent(TimeLineActivity.this, TweetActivity.class);
			this.startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	private void populateTimeLine() {
		String key = this.getResources().getString(R.string.config_key);

		this.spConfig = this.getSharedPreferences(key, Context.MODE_PRIVATE);
		this.client.getHomeTimeLine(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				ArrayList<Tweet> al = Tweet.fromJSONArray(json);
				tweetAdapter.addAll(al);
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}

		}, this.spConfig);
	}

}
