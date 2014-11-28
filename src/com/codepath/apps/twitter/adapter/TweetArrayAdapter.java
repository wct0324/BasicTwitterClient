package com.codepath.apps.twitter.adapter;

import java.util.List;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitter.R;
import com.codepath.apps.twitter.models.Tweet;
import com.codepath.apps.twitter.utils.TwitterUtility;
import com.squareup.picasso.Picasso;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {

	public TweetArrayAdapter(Context context, List<Tweet> objects) {
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Tweet tweet = this.getItem(position);
		View view;

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			view = inflater.inflate(R.layout.twitter_item, parent, false);
		} else {
			view = convertView;
		}

		ImageView ivProfileImage = (ImageView) view
				.findViewById(R.id.ivProfileImage);
		TextView tvName = (TextView) view.findViewById(R.id.tvName);
		TextView tvScreenName = (TextView) view.findViewById(R.id.tvScreenName);
		TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
		TextView tvTime = (TextView) view.findViewById(R.id.tvTime);

		CharSequence cs = DateUtils.getRelativeDateTimeString(
				this.getContext(), tweet.getCreatedAt(),
				DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0);

		ivProfileImage.setImageResource(0);
		Picasso.with(getContext()).load(tweet.getUser().getProfileImageURL())
				.into(ivProfileImage);

		tvName.setText(tweet.getUser().getName());
		tvScreenName.setText("@" + tweet.getUser().getScreenName());
		tvBody.setText(tweet.getBody());
		tvTime.setText(TwitterUtility.formatDate(cs.toString()));

		TwitterUtility.setConfiguration(this.getContext(), tweet.getUid());

		return view;
	}

}
