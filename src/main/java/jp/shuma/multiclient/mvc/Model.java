package jp.shuma.multiclient.mvc;

/**
 * Modelクラス．
 * @since 0.2
 * @version 0.2
 * @param shuma
 */
public class Model {
	private View view;
	/**
	 * メインメソッド．
	 * @since 0.2
	 */
	public Model() {
		//do nothing...
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
		System.out.println(this.view.getPostData());
	}
	/**
	 * テスト用．
	 * @since 0.2
	 */
	public void start() {
		 Thread test = new Thread() {
		 		private View view;
		 		public Thread setView(View view) {
		 			this.view = view;
		 			return this;
		 		}
                public void run() {
                	while(true) {
	                    this.view.addNews(null);
	                    try{
							Thread.sleep(3000); 
						} catch(InterruptedException e){}
	                }
                }
            }.setView(view);
        test.start();
	}

}