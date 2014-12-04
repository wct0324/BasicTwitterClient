package com.codepath.apps.twitter.activities;

import java.text.DecimalFormat;

import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.util.Log;
import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterApplication;
import com.codepath.apps.twitter.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends FragmentActivity {

	DecimalFormat df = new DecimalFormat("#,###,###,##0");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		this.loadProfile();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void onProfileView(View view) {
		ImageView ivProfileImage = (ImageView) view
				.findViewById(R.id.ivProfileImage);
		Toast.makeText(this, ivProfileImage.getTag().toString(),
				Toast.LENGTH_SHORT).show();
	}

	private void loadProfile() {
		long uid = this.getIntent().getLongExtra("userID", 0);
		TwitterApplication.getRestClient().getUser(
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONObject json) {
						User user = User.fromJSON(json);
						getActionBar().setTitle("@" + user.getScreenName());
						populateProfileHeader(user);
					}

					@Override
					public void onFailure(Throwable e, String s) {
						Log.d("debug", e.toString());
						Log.d("debug", s.toString());
					}

				}, uid);
	}

	private void populateProfileHeader(User user) {
		ImageView ivProfileImage = (ImageView) this
				.findViewById(R.id.ivProfileImage);
		TextView tvProfileName = (TextView) this
				.findViewById(R.id.tvProfileName);
		TextView tvProfileTagLine = (TextView) this
				.findViewById(R.id.tvProfileTagLine);
		TextView tvFollowers = (TextView) this.findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) this.findViewById(R.id.tvFollowing);

		ivProfileImage.setImageResource(0);
		ivProfileImage.setTag(user.getUid());
		Picasso.with(this).load(user.getProfileImageURL()).into(ivProfileImage);

		tvProfileName.setText(user.getName());
		tvProfileTagLine.setText(user.getDescription());
		tvFollowers.setText(df.format(user.getFollowers()) + " Followers");
		tvFollowing.setText(df.format(user.getFollowing()) + " Following");
	}
}
