package cs3260.nyuad;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterBot {

  private Twitter twitter;
  private static final String BOT_SIGNATURE = " (posted using TwitterBot)";

  public TwitterBot(Twitter twitter) {
    this.twitter = twitter;
  }

  public TwitterBot() {
    // The factory instance is re-useable and thread safe.
    // you would need to access the twitter API using your twitter4j.properties file
    twitter = TwitterFactory.getSingleton();
  }

  public String getBotSignature() {
    return BOT_SIGNATURE;
  }

  /**
   * Post a tweet to the user's timeline, indicating that it was posted using the
   * bot
   * 
   * @param text the text of the tweet
   * @return the user's latest status
   */
  public void postTweet(String text) throws TwitterException {
    twitter.updateStatus(text + BOT_SIGNATURE);
  }

  /**
   * Automatically reply to tweets containing certain text
   * 
   * @param query_text the text to search for
   * @param reply      the text to reply with
   */
  public void replyToTweet(String query_text, String reply) throws TwitterException {
    // create a new search
    Query query = new Query(query_text);

    // get the results from that search
    QueryResult result = twitter.search(query);

    // get the first tweet from those results
    Status tweetResult = result.getTweets().get(0);

    // reply to that tweet
    twitter.updateStatus(".@" + tweetResult.getUser().getScreenName() + " " + reply + BOT_SIGNATURE);

  }

  // random API calls in the main function to demonstrate how TwitterBot
  // can be used
  public static void main(String... args) {
    try {
      TwitterBot twitterBot = new TwitterBot();
      twitterBot.postTweet("Hello world!");

      String query_text = "\"your welcome\"";
      String reply = "I believe you meant \"you're welcome\"?";
      twitterBot.replyToTweet(query_text, reply);
    } catch (TwitterException e) {
      e.printStackTrace();
    }

  }
}
