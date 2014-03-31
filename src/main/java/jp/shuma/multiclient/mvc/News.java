package jp.shuma.multiclient.mvc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.util.Date;

import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
/**
 * Viewの記事を制御するクラス，<br/>
 * 内部クラスで構成されている．<br/>
 * パッケージ宣言するほど大きな分類でないため，内部クラスで表現した．
 * @since 0.2
 * @version 0.2
 * @param shuma
 */
public class News {
	/**
	 * 記事Panelに表示するデータを保持するクラス．
	 * @since 0.2
	 * @version 0.2
	 * @param shuma
	 */
	public static class Data {
		ImageIcon icon;
		String userName;
		String content;
		Date date;
		public Data(ImageIcon icon, String userName, String content, Date date) {
			this.icon = icon;
			this.userName = userName;
			this.content = content;
			this.date = date;
		}
	}
	/**
	 * 記事を表示するクラス．<br/>
	 * JListのセル．
	 * @since 0.2
	 * @version 0.2
	 * @param shuma
	 */
	public static class Panel extends JPanel {
		/**
		 * コンストラクタ．
		 * @since 0.2
		 */
		public Panel(Data data) {
			super();
			this.setLayout(null);
			
			this.setBackground(Color.WHITE);
			this.setBorder(new LineBorder(Color.gray, 1, true));
			
			JLabel picture = new JLabel(data.icon);
			JLabel name = new JLabel(data.userName);
			JLabel date = new JLabel(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(data.date));
			JTextArea content = new JTextArea(data.content.replaceAll(System.getProperty("line.separator"), ""));
			content.setLineWrap(true);

			name.setFont(new Font("Arial", Font.PLAIN, 10));
			date.setFont(new Font("Arial", Font.PLAIN, 10));
			content.setFont(new Font("Arial", Font.PLAIN, 10));

			this.setPreferredSize(new Dimension(View.CELL_WITHD, View.CELL_HEIGHT));
			picture.setBounds(5, 15, 40, 40);
			name.setBounds(50, 5, 150, 10);
			date.setBounds(225, 5, 150, 10);
			content.setBounds(50, 20, 325, 45);

			this.add(picture);
			this.add(name);
			this.add(date);
			this.add(content);
		}
	}
	/**
	 * JListにPanelを表示するためのクラス．
	 * @since 0.2
	 * @version 0.2
	 * @param shuma
	 */
	public static class Renderer implements ListCellRenderer {
		@Override
  		public Component getListCellRendererComponent(JList list, Object data, int index, boolean isSelected, boolean cellHasFocus) {
  			return new Panel((Data)data);
  		}
	}
}