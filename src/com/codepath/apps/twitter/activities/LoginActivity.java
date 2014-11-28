package com.codepath.apps.twitter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.TwitterClient;
import com.codepath.oauth.OAuthLoginActivity;

public class LoginActivity extends OAuthLoginActivity<TwitterClient> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onLoginFailure(Exception e) {
		e.printStackTrace();
	}

	@Override
	public void onLoginSuccess() {
		Intent i = new Intent(this, TimeLineActivity.class);
		startActivity(i);
	}

	public void loginToRest(View view) {
		getClient().connect();
	}

}
