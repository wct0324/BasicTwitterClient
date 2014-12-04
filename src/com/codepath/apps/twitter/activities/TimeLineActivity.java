package com.codepath.apps.twitter.activities;

import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.activeandroid.util.Log;
import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApplication;
import com.codepath.apps.twitter.fragments.HomeTimeLineFragment;
import com.codepath.apps.twitter.fragments.MentionsTimeLineFragment;
import com.codepath.apps.twitter.listeners.FragmentTabListener;
import com.codepath.apps.twitter.models.User;
import com.codepath.apps.twitter.utils.TwitterUtility;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimeLineActivity extends FragmentActivity {

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_line);
		this.setupTabs();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.actions, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menuTweet:
			this.intent = new Intent(TimeLineActivity.this, TweetActivity.class);
			this.startActivity(this.intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	public void onProfileView(MenuItem item) {
		TwitterApplication.getRestClient().getMyInfo(
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject json) {
						User user = User.fromJSON(json);
						intent = new Intent(TimeLineActivity.this,
								ProfileActivity.class);
						intent.putExtra("userID", user.getUid());
						startActivity(intent);
					}

					@Override
					public void onFailure(Throwable e, String s) {
						Log.d("debug", e.toString());
						Log.d("debug", s.toString());
					}

				});

	}

	public void onProfileView(View view) {
		ImageView ivProfileImage = (ImageView) view
				.findViewById(R.id.ivProfileImage);
		this.intent = new Intent(TimeLineActivity.this, ProfileActivity.class);
		this.intent.putExtra("userID",
				Long.valueOf(ivProfileImage.getTag().toString()));
		this.startActivity(intent);
	}

	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
				.newTab()
				.setText("Home")
				.setIcon(R.drawable.ic_home)
				.setTag("HomeTimeLineFragment")
				.setTabListener(
						new FragmentTabListener<HomeTimeLineFragment>(
								R.id.flContainer, this, "home",
								HomeTimeLineFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
				.newTab()
				.setText("Mentions")
				.setIcon(R.drawable.ic_mentions)
				.setTag("MentionsTimeLineFragment")
				.setTabListener(
						new FragmentTabListener<MentionsTimeLineFragment>(
								R.id.flContainer, this, "mentions",
								MentionsTimeLineFragment.class));

		actionBar.addTab(tab2);
	}

}
