package jp.shuma.multiclient.sns;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URI;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.util.Properties;
import java.util.List;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.User;
import twitter4j.Paging;
import twitter4j.auth.RequestToken;
import twitter4j.auth.AccessToken;
import twitter4j.ResponseList;

import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

import jp.shuma.multiclient.mvc.News;
import jp.shuma.multiclient.mvc.Model;

public class TwitterClient {
	private Twitter twitter;
	private List<News.Data> queue = new ArrayList<News.Data>();
	private long lastId = -1;
	public TwitterClient(Model model) {
		try {
			twitter = TwitterFactory.getSingleton();
		    RequestToken requestToken = twitter.getOAuthRequestToken();
		    AccessToken accessToken = this.loadAccessToken();
		    if(accessToken == null) {
				this.browser(requestToken.getAuthorizationURL());
				String pin = JOptionPane.showInputDialog("PIN");
		   		if(pin.length() > 0){
		        	accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		        } else {
		        	accessToken = twitter.getOAuthAccessToken();
		        }
		 	   storeAccessToken((int)twitter.verifyCredentials().getId(), accessToken);
			} else {
				twitter.setOAuthAccessToken(accessToken);
			}
			TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory();
   			TwitterStream twitterStream = twitterStreamFactory.getInstance();
    		twitterStream.addListener(new UserStreamAdapter() {
    			private Model model;
    			public UserStreamAdapter setModel(Model model) {
    				this.model = model;
    				return this;
    			}
    			public void onStatus(Status status) {
					super.onStatus(status);
					this.model.addNews(TwitterClient.toNewsDate(status));
				}
    		}.setModel(model));
    		twitterStream.setOAuthAccessToken(accessToken);
    		twitterStream.user();

     	} catch (TwitterException e) {

     	}
	}
	private void browser(String url) {
		Desktop desktop = Desktop.getDesktop();
		try {
			URI uri = new URI(url);
			desktop.browse(uri);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void storeAccessToken(int useId, AccessToken accessToken){
		try {
			String jarPath = System.getProperty("java.class.path");
			String dirPath = jarPath.substring(0, jarPath.lastIndexOf(File.separator)+1);

			File propertyFile = new File(dirPath+"multiclient.properties");
			Properties myConf = new Properties();

			myConf.setProperty("useId", Integer.toString(useId));
			myConf.setProperty("accessToken", accessToken.getToken());
			myConf.setProperty("accessTokenSecret", accessToken.getTokenSecret());

			FileOutputStream myConfFileOut = new FileOutputStream(propertyFile);
			myConf.store(myConfFileOut, null); 
		} catch(IOException e) {
			e.printStackTrace();
		} 
  	}
  	public void post(String text) {
  		try {
	  		this.twitter.updateStatus(text);
	  	} catch (TwitterException e) {
	  		e.printStackTrace();
	  	}
  	}
  	private static AccessToken loadAccessToken() {
  		try {
			String jarPath = System.getProperty("java.class.path");
			String dirPath = jarPath.substring(0, jarPath.lastIndexOf(File.separator)+1);

			File propertyFile = new File(dirPath+"multiclient.properties");
			Properties myConf = new Properties();
			myConf.load(new FileInputStream(propertyFile));
			long useId = Long.parseLong(myConf.getProperty("useId"));
			String accessToken = myConf.getProperty("accessToken");
			String accessTokenSecret = myConf.getProperty("accessTokenSecret");
			return new AccessToken(accessToken, accessTokenSecret, useId);
		} catch(Exception e) {
			return null;
		} 
  	}
  	private static Map<String, ImageIcon> iconMap = new HashMap<String, ImageIcon>();

  	public static ImageIcon toIcon(String urlString){
    	Image image;
    	if(iconMap.get(urlString) != null) return iconMap.get(urlString);
    	try {
   			image = ImageIO.read(new URL(urlString));
    		image = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
      		ImageIcon icon = new ImageIcon(image);
      		iconMap.put(urlString, icon);
      		return icon;
    	} catch (MalformedURLException e) {
      		e.printStackTrace();
    	} catch (IOException e) {
      		e.printStackTrace();
    	}
    	return null;
	}
  	public static News.Data toNewsDate(Status i) {
  		return new News.Data(toIcon(i.getUser().getProfileImageURL()), i.getUser().getName(), i.getText(), i.getCreatedAt());
  	}
}
