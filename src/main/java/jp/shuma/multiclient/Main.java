package jp.shuma.multiclient;

import javax.swing.JFrame;

import jp.shuma.multiclient.mvc.Controller;
import jp.shuma.multiclient.mvc.Model;
import jp.shuma.multiclient.mvc.View;
import jp.shuma.multiclient.sns.TwitterClient;
/**
 * メインクラス．<br/>
 * mvcのMainFrameを開く．
 * @since 0.1
 * @version 0.2
 * @author shuma
 */
public class Main {
	/**
	 * コンストラクタ．
	 * @param args コマンドライン引数．
	 * @since 0.2
	 */
	public Main(String[] args) {
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller();

		view.setModel(model);
		view.setController(controller);
		model.setView(view);
		controller.setModel(model);

		JFrame window = new JFrame("MultiClient");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(view);
		window.pack();
		window.setVisible(true);
		
		TwitterClient client = new TwitterClient(model);
		model.setClient(client);
		// model.start();
	}
	/**
	 * メインメソッド．
	 * @param args コマンドライン引数．
	 * @since 0.1
	 * @version 0.2
	 */
	public static void main(String[] args) {
		new Main(args);
	}
}