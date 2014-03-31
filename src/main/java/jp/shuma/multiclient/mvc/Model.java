package jp.shuma.multiclient.mvc;

import java.util.List;
import java.util.ArrayList;

import jp.shuma.multiclient.sns.TwitterClient;
/**
 * Modelクラス．
 * @since 0.2
 * @version 0.2
 * @param shuma
 */
public class Model {
	/**
	 * View．
	 * @since 0.2
	 */
	private View view;
	/**
	 *
	 *
	 */
	private List<TwitterClient> clients;
	/**
	 * メインメソッド．
	 * @since 0.2
	 */
	public Model() {
		this.clients = new ArrayList<TwitterClient>();
	}
	/**
	 * viewをセットする．
	 * @param view ビュー．
	 * @since 0.2
	 */
	public void setView(View view) {
		this.view = view;
	}
	/**
	 * ポストボタンが押されたとき，反応する．
	 * @since 0.2
	 */
	public void post() {
		for(TwitterClient i : clients) {
			i.post(this.view.getPostData());
		}
	}
	public void setClient(TwitterClient client) {
		this.clients.add(client);
		// this.update();
	}
	public void addNews(News.Data data) {
		this.view.addNews(data);
	}

}