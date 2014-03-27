package jp.shuma.multiclient.mvc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * メインクラス．
 * とりあえずHello・・・
 * @since 0.2
 * @version 0.2
 * @param shuma
 */
public class Controller implements ActionListener  {
	/**
	 * Model．
	 * @since 0.2
	 */
	private Model model;
	/**
	 * modelをセットする．
	 * @param model モデル．
	 * @since 0.2
	 */
	public void setModel(Model model) {
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.model.post();
    }
}