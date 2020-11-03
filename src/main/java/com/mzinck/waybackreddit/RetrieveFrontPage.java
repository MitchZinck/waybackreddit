package com.mzinck.waybackreddit;

import com.google.gson.Gson;
import com.mzinck.waybackreddit.pushshift.PushShift;
import net.dean.jraw.ApiException;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Comment;
import net.dean.jraw.models.DistinguishedStatus;
import net.dean.jraw.models.SubmissionKind;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.references.SubmissionReference;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * /r/waybackreddit
 * @author Mitchell Zinck Copyright (2019)
 * @version 1.0
 * @see <a href="github.com/mitchzinck">Github</a>
 */

public class RetrieveFrontPage {

    public static String redditUsername;
    public static String redditPassword;
    public static String redditClientId;
    public static String redditClientSecret;

    public static void main(String[] args) {
        if(args[0].equals("production")) {
            try {
                System.setErr(new PrintStream(new File(System.getProperty("user.home") + "/waybackredditerror.log")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        setConfig();

//        Thread waybackThread = new Thread(){
//            public void run(){
//                for(int i = 1; i <= 10; i++) {
//                    String[] beforeAndAfter = getTimeStamp(i);
//                    PushShift push = getTopPostsFromTimeStamp(beforeAndAfter[0], beforeAndAfter[1]);
//                    initiatePosting(push, beforeAndAfter[2], "waybackreddit", 0);
//                    initiatePosting(push, beforeAndAfter[2], "waybackreddit", 1);
//                    try {
//                        Thread.sleep(3600000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        waybackThread.start();

        Thread redditYearsAgo = new Thread() {
            public void run() {
                PushShift pushShifts[] = new PushShift[10];
                for(int i = 1; i <= 10; i++) {
                    String[] beforeAndAfter = getTimeStamp(i);
                    PushShift push = getTopPostsFromTimeStamp(beforeAndAfter[0], beforeAndAfter[1], 0);
                    pushShifts[i - 1] = push;
                }

                for(int y = 0; y < 20; y++) {
                    for (int x = 1; x <= 10; x++) {
                        String[] beforeAndAfter = getTimeStamp(x);
                        PushShift push = pushShifts[x - 1];

                        if(push == null) {
                            continue;
                        }
                        if (x == 1) {
                            initiatePosting(push, beforeAndAfter[2], "eddit" + x + "yearago", y);
                        } else {
                            initiatePosting(push, beforeAndAfter[2], "eddit" + x + "yearsago", y);
                        }
                    }
                    try {
                        Thread.sleep(1800000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        redditYearsAgo.start();

    }

    public static void initiatePosting(PushShift push, String date, String subreddit, int postIndex) {
        UserAgent userAgent = new UserAgent("SMBuilder", "com.mzinck.waybackreddit", "v0.1", "l_-_-_-_-_-_-_-_-_l");
        Credentials credentials = Credentials.script(redditUsername, redditPassword, redditClientId,
                redditClientSecret); //clientid client secret
        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
        RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
        post("https://reddit.com" + push.getData().get(postIndex).getPermalink(), push.getData().get(postIndex).getUrl(),
                date, push.getData().get(postIndex).getScore(), push.getData().get(postIndex).getTitle(), push.getData().get(postIndex).getSubreddit(), push.getData().get(postIndex).getOver18(), subreddit, reddit);
    }

    public static PushShift getTopPostsFromTimeStamp(String before, String after, int tryCount) {
        URL url = null;
        InputStreamReader reader = null;
        try {
            url = new URL("https://api.pushshift.io/reddit/submission/search/?after=" + after + "&before=" + before + "&sort_type=score&sort=desc");
            reader = new InputStreamReader(url.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            if(tryCount < 5) {
                try {
                    Thread.sleep(5000);
                    return getTopPostsFromTimeStamp(before, after, tryCount + 1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            } else {
                return null;
            }
        }

        return new Gson().fromJson(reader, PushShift.class);
    }

    public static String[] getTimeStamp(int numberOfYearsBack) {
        LocalDate today = LocalDate.now();
        LocalDate wayback = LocalDate.of(today.getYear() - numberOfYearsBack, today.getMonthValue(), today.getDayOfMonth());
        long waybackEpoch = wayback.atStartOfDay(ZoneId.of("GMT")).toEpochSecond();
        String before = Long.toString(waybackEpoch);
        String after = Long.toString(waybackEpoch - 86400); //remove a days worth of milliseconds

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedString = wayback.format(formatter);
        return new String[]{before, after, formattedString};
    }

    public static void post(String permalink, String contentURL, String date, int upvotes, String title, String originalSubreddit, boolean isNsfw, String postSubreddit, RedditClient redditClient) {
        if(title.length() > 280) {
            title = title.substring(0, title.length() - (title.length() - 280));
            title += "....";
        }
        try {
            SubmissionReference sub = redditClient.subreddit(postSubreddit).submit(SubmissionKind.LINK,  "(+" + upvotes + ") "
                    + title, contentURL, false);
            Comment com = redditClient.submission(sub.getId()).reply("Date: " + date + "  \n" +
                    "Title: " + title + "  \n" +
                    "Upvotes: " + upvotes + "  \n" +
                    "Original Post: " + permalink + "  \n" +
                    "Web Archive: https://web.archive.org/web/" + permalink.replaceAll("//old\\.", "//") + "  \n" +
                    "Subreddit: " + originalSubreddit + "  \n" +
                    "NSFW: " + isNsfw + "  \n" +
                    "If there are any issues with this post such as dead content please report the post or message the mods.");
            redditClient.comment(com.getId()).distinguish(DistinguishedStatus.MODERATOR, true);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void setConfig() {
        File file = new File(System.getProperty("user.home") + "/config.txt");
        try {
            Scanner scan = new Scanner(file);
            redditUsername = scan.nextLine();
            redditPassword = scan.nextLine();
            redditClientId = scan.nextLine();
            redditClientSecret = scan.nextLine();
            scan.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
