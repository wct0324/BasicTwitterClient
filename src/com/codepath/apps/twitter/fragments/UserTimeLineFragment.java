package com.codepath.apps.twitter.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.activeandroid.util.Log;
import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.listeners.EndlessScrollListener;
import com.codepath.apps.twitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimeLineFragment extends TweetListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.populateTimeLine();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tweet_list, container,
				false);

		this.lvTweets = (ListView) view.findViewById(R.id.lvTweets);
		this.lvTweets.setAdapter(this.tweetAdapter);
		this.lvTweets.setOnScrollListener(new EndlessScrollListener() {

			@Override
			public void onLoadMore(int a, int b) {
				populateTimeLine();
			}
		});

		return view;
	}

	private void populateTimeLine() {
		long uid = this.getActivity().getIntent().getLongExtra("userID", 0);
		String key = this.getResources().getString(R.string.config_key);

		this.spConfig = this.getActivity().getSharedPreferences(key,
				Context.MODE_PRIVATE);
		this.client.getUserTimeLine(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONArray json) {
				ArrayList<Tweet> al = Tweet.fromJSONArray(json);
				addAll(al);
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}

		}, this.spConfig, uid);
	}

}
