package jp.shuma.multiclient.mvc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;


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
		//now nothing....
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
		public Panel() {
			super();
			this.setLayout(null);
			this.setPreferredSize(new Dimension(View.CELL_WITHD, View.CELL_HEIGHT));
			this.setBackground(Color.WHITE);
			this.setBorder(new LineBorder(Color.gray, 1, true));
			//テストとして同じ物を何回も出力している．
			//引数としてDataを受け取って，やるつもり
			JLabel picture = new JLabel();
			JLabel name = new JLabel("shuma");
			JLabel date = new JLabel("2014/3/27 20:46:53");
			JLabel content = new JLabel("content");

			
			picture.setBounds(5, 5, 40, 40);
			name.setBounds(50, 5, 150, 10);
			date.setBounds(225, 5, 150, 10);
			content.setBounds(50, 20, 325, 25);

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
  			return new Panel();
  		}
	}
}