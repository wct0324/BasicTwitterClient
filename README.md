Basic Twitter Client
====================

This is the app for a basic Twitter client. 

Time Spent :
The total time spent on this app is approximately 3 hours. Like the instructor predicted, this assignment took much more time than previous ones.

Thoughts :
I spent (wasted) about 2 days trying to display the tweets to work with max_id and SharedPreferences (for endless scrolling).
Due to a the nature of the API itself, I often got a blank screen of tweets. Later on, I found out this problem was caused by the combination of
max_id and SharedPreferences. It was resolved by clearing the SharedPreferences on the "onCreate" of the TimeLineActivity.

Bugs :
Not really sure why, but the endless scroll will sometimes return a set of newer tweets which have already been viewed. I think the problem is caused
by when I set the max_id, which is in the adapter. Another "bug" is that unless I am in the debug mode, the new tweet that I have just composed 
just does not show up in the timeline. It only shows up immediately in debug mode because I presume it just took longer to start the timeline
activity. I had to exit the app and activate it again for it to show up.

Optional
Due to time constraint, I only managed to complete the counter for text left to tweet using a TextWatcher;

![alt tag](https://github.com/wct0324/BasicTwitterClient/blob/master/Walkthrough_20141128.gif)