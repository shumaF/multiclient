package jp.shuma.multiclient.mvc;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

/**
 * Viewクラス．
 * ここでViewに関するすべてのオブジェクトの大きさを扱う．
 * @since 0.2
 * @version 0.2
 * @param shuma
 */
public class View extends JPanel {
	/**
	 * PostPanelのHeight．
	 * @since 0.2
	 */
	public static final int POST_HEIGHT = 100;
	/**
	 * PostPanelのWidht．
	 * @since 0.2
	 */
	public static final int POST_WITHD = 400;
	/**
	 * JListのHeight．
	 * @since 0.2
	 */
	public static final int LIST_HEIGHT = 500;
	/**
	 * JListのWidth．
	 * @since 0.2
	 */
	public static final int LIST_WITHD = 400;
	/**
	 * JListのHeight．
	 * @since 0.2
	 */
	public static final int CELL_HEIGHT = 50;
	/**
	 * JListのWidth．
	 * @since 0.2
	 */
	public static final int CELL_WITHD = 380;
    /**
     * JList．
	 * @since 0.2     
     */
	private JList list;
	/**
	 * JListの要素を持つクラス．
	 * @since 0.2
     */
 	private DefaultListModel<News.Data> listModel;
	/**
	 * Model．
	 * @since 0.2
	 */
	private Model model;
	/**
	 * PostPanel. ．
	 * @since 0.2
	 */
	private Post.Panel post;
	/**
	 * コンストラクタ．
	 * @since 0.2
	 */
	public View() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		post = new Post.Panel();
		post.setPreferredSize(new Dimension(View.POST_WITHD, View.POST_HEIGHT));
		this.add(post);


		this.listModel = new DefaultListModel();
		this.list = new JList(listModel);
		this.list.setLayoutOrientation(JList.VERTICAL);
		this.list.setCellRenderer(new News.Renderer());

		this.listModel.add(0, null);
		this.listModel.add(1, null);
		this.listModel.add(2, null);

		JScrollPane scroll = new JScrollPane();
		scroll.setPreferredSize(new Dimension(LIST_WITHD, LIST_HEIGHT));
		scroll.getViewport().setView(this.list);
		this.add(scroll);
	}
	/**
	 * modelをセットする．
	 * @param model モデル．
	 * @since 0.2
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	/**
	 * Controllerをセットする．
	 * @param controller コントローラー．
	 * @since 0.2
	 */
	public void setController(Controller controller) {
		post.setController(controller);
	}
	/**
	 * 記事を追加する．
	 * @param 記事データ．
	 * @since 0.2
	 */
	public void addNews(News.Data data) {
		this.listModel.add(0, data);
	}
	/**
	 * 投稿データを取得する．<br/>
	 * とりあえずStringで・・・
	 * @return 投稿データ．
	 * @since 0.2
	 */
	public String getPostData() {
		return this.post.getContentText();
	}
}