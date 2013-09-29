package eu.verdelhan.samples.twitter4j;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Twitter4JExample {

    public static void main(String[] args) {

        // Posting a tweet
        Twitter twitter = TwitterFactory.getSingleton();
        try {
            Status status = twitter.updateStatus("A tweet from twitter4j. #test");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException te) {
            te.printStackTrace();
        }

    }

}