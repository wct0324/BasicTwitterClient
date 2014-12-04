Basic Twitter Client
====================

This is the app for a basic Twitter client which combines both Assignment #4 and Assignment #5. 

Time Spent (Assignment #4) :
The total time spent on Assignment #4 is approximately 3 days. Like the instructor predicted, this assignment took much more time than previous ones.

Time Spent (Assignment #5) :
The total time spent on Assignment #5 is approximately 1 day due to better familiarity with the Twitter API and Android development in general.

Thoughts (Assignment #4) :
I spent (wasted) about 2 days trying to display the tweets to work with "max_id" and SharedPreferences (for endless scrolling).
Due to the nature of the API itself, I often got a blank screen of tweets. Later on, I found out this problem was caused by not resetting "max_id".
It was resolved by clearing the SharedPreferences on the "onCreate" of the TimeLineActivity.

Thoughts (Assignment #5) :
Much easier assignment than Assignment #4 due to the familiarity. For the "User Profile" code to work both for my own profile and others, I had
to call a different API from the video walkthrough, which was pretty obvious. I also used the tag of the ImageView to store the user ID and pass it
using the Intent.

Bugs (Both Assignment #4 & #5):
The endless scroll will sometimes return a set of tweets which have already been viewed. I think the problem is caused
by the scroll listener. Another problem is that the tweet I have just composed does not show up on the Home TimeLine because it is cached and does
not refresh. The newly composed tweet will also have a "max_id" larger than the older tweets so the endless scroll cannot display it either
(I didn't use "since_id"). It will show up once I go to My Profile or restart the app.

Optional (Assignment #4)
Due to time constraint, I only managed to complete the counter for text left to tweet using a TextWatcher.

Optional (Assignment #5)
Due to time constraint, I basically just cleaned up the user interface and formatted some text.

Version 2.0 (Assignment #5)
![alt tag](https://github.com/wct0324/BasicTwitterClient/blob/master/Walkthrough_20141204.gif)

Version 1.0 (Assignment #4)
![alt tag](https://github.com/wct0324/BasicTwitterClient/blob/master/Walkthrough_20141128.gif)