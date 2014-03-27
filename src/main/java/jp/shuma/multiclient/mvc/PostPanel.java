package jp.shuma.multiclient.mvc;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
/**
 * PostPanelクラス．<br/>
 * setBoundsによる指定している．
 * @since 0.2
 * @version 0.2
 * @param shuma
 */
public class PostPanel extends JPanel {
	/**
	 * アイコン．
	 * @since 0.2
	 */
	private JLabel picture;
	/**
	 * 内容．
	 * @since 0.2
	 */
	private JTextArea content;
	/**
	 * 内容のテキストを取得する．
	 * @param 内容のテキスト．
	 * @since 0.2
	 */
	public String getContentText() {
		return this.content.getText();
	}
	/**
	 * コンボボックス．<br/>
	 * どのsnsで投稿するか．<br/>
	 * 初期として，allを格納する．
	 * @since 0.2
	 */
	private JComboBox combobox;
	/**
	 * 投稿ボタン．
	 * @since 0.2
	 */
	private JButton button;
	/**
	 * コンストラクタ．
	 * @since 0.2
	 */
	public PostPanel() {
		super();
		this.setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.white, Color.black));
		
		this.picture = new JLabel();
		content = new JTextArea();
		combobox = new JComboBox(new String[]{"all"});
		this.button = new JButton("投稿");

		this.content.setLineWrap(true);

		this.picture.setBounds(10, 10, 40, 40);
		this.content.setBounds(60, 5, 250, 90);
		this.combobox.setBounds(315, 45, 80, 25);
		this.button.setBounds(315, 75, 80, 20);

		this.add(this.picture);
		this.add(this.content);
		this.add(this.combobox);
		this.add(this.button);
	}
	/**
	 * Controllerをセットする．
	 * @param controller コントローラー．
	 * @since 0.2
	 */
	public void setController(Controller controller) {
		this.button.addActionListener(controller);
	}
}