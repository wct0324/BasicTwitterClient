package com.codepath.apps.twitter.fragments;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApplication;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.adapter.TweetArrayAdapter;
import com.codepath.apps.twitter.models.Tweet;

public class TweetListFragment extends Fragment {

	protected ArrayAdapter<Tweet> tweetAdapter;
	protected ListView lvTweets;
	protected SharedPreferences spConfig;
	protected TwitterClient client;

	private ArrayList<Tweet> tweetList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String key = this.getResources().getString(R.string.config_key);
		this.spConfig = this.getActivity().getSharedPreferences(key,
				Context.MODE_PRIVATE);
		this.spConfig.edit().clear().commit();

		this.tweetList = new ArrayList<Tweet>();
		this.tweetAdapter = new TweetArrayAdapter(this.getActivity(),
				this.tweetList);
		this.client = TwitterApplication.getRestClient();
	}

	public void addAll(ArrayList<Tweet> tweetList) {
		this.tweetAdapter.addAll(tweetList);
	}

}
