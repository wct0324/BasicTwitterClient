package com.codepath.apps.twitter.activities;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.util.Log;
import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApplication;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.apps.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

public class TweetActivity extends Activity {

	private int tweetLimit;

	private EditText etTweet;
	private TextView tvCounter;
	private TwitterClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet);

		this.client = TwitterApplication.getRestClient();
		this.getUser();

		this.tweetLimit = Integer.valueOf(getResources().getString(
				R.string.twitter_tweet_limit));

		this.etTweet = (EditText) this.findViewById(R.id.etTweet);
		this.tvCounter = (TextView) this.findViewById(R.id.tvCounter);

		this.etTweet.addTextChangedListener(this.twCounter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.update, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuUpdate:
			this.postTweet(this.etTweet.getText().toString());
			Intent i = new Intent(TweetActivity.this, TimeLineActivity.class);
			this.startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	private void getUser() {
		this.client.getMyInfo(new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject json) {
				User self = User.fromJSON(json);
				attach(self);
			}

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}

		});
	}

	private void postTweet(String tweet) {
		this.client.postTweet(new JsonHttpResponseHandler() {

			@Override
			public void onFailure(Throwable e, String s) {
				Log.d("debug", e.toString());
				Log.d("debug", s.toString());
			}

		}, tweet);
	}

	private void attach(User self) {
		ImageView ivSelfImage = (ImageView) this.findViewById(R.id.ivSelfImage);
		TextView tvOwnName = (TextView) this.findViewById(R.id.tvOwnName);
		TextView tvOwnScreenName = (TextView) this
				.findViewById(R.id.tvOwnScreenName);

		ivSelfImage.setImageResource(0);
		Picasso.with(this).load(self.getProfileImageURL()).into(ivSelfImage);

		tvOwnName.setText(self.getName());
		tvOwnScreenName.setText("@" + self.getScreenName());
	}

	private TextWatcher twCounter = new TextWatcher() {
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			tvCounter.setText(String.valueOf(tweetLimit - s.length()));
		}

		public void afterTextChanged(Editable s) {
		}
	};

}
