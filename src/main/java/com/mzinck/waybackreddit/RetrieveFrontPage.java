package com.mzinck.waybackreddit;

import com.google.gson.Gson;
import com.mzinck.waybackreddit.pushshift.PushShift;
import net.dean.jraw.ApiException;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
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
        try {
            System.setErr(new PrintStream(new File(System.getProperty("user.home")+"/waybackredditerror.log")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setConfig();
        for(int i = 1; i <= 10; i++) {
            String[] beforeAndAfter = getTimeStamp(i);
            PushShift push = getTopPostsFromTimeStamp(beforeAndAfter[0], beforeAndAfter[1]);
            if(i == 1) {
                initiatePosting(push, beforeAndAfter[2], "eddit" + i + "yearago");
            } else {
                initiatePosting(push, beforeAndAfter[2], "eddit" + i + "yearsago");
            }
        }
    }

    public static void initiatePosting(PushShift push, String date, String subreddit) {
        UserAgent userAgent = new UserAgent("SMBuilder", "com.mzinck.waybackreddit", "v0.1", "l_-_-_-_-_-_-_-_-_l");
        Credentials credentials = Credentials.script(redditUsername, redditPassword, redditClientId,
                redditClientSecret); //clientid client secret
        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
        RedditClient reddit = OAuthHelper.automatic(adapter, credentials);
        for(int i = 0; i < push.getData().size(); i++) {
            post("https://old.reddit.com" + push.getData().get(i).getPermalink(), date, push.getData().get(i).getScore(), push.getData().get(i).getTitle(), subreddit, reddit);
        }
    }

    public static PushShift getTopPostsFromTimeStamp(String before, String after) {
        URL url = null;
        InputStreamReader reader = null;
        try {
            url = new URL("https://api.pushshift.io/reddit/submission/search/?after=" + after + "&before=" + before + "&sort_type=score&sort=desc");
            reader = new InputStreamReader(url.openStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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

    public static void post(String url, String date, int upvotes, String title, String subreddit, RedditClient redditClient) {
        if(title.length() > 250) {
            title = title.substring(0, title.length() - 25);
            title += "....";
        }
        try {
            SubmissionReference sub = redditClient.subreddit(subreddit).submit(SubmissionKind.LINK, "+" + upvotes + " [" + date + "] - \"" + title + "\"", url, false);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void setConfig() {
        File file = new File("C:\\Users\\Mitchell\\Desktop\\config.txt");
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
