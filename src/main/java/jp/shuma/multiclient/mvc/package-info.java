/**
 * multiclientのmvcパッケージ<br/><br/>
 * MVCモデルでは，依存性を利用するMVCを利用している．<br/>
 * ここでは，誰かが投稿したデータ(ツイートなど)を
 *『記事(News』という単語で表現する．
 * 	<br/><br/>
 *  ---- version ----					<br/>
 *  0.2	 add mvc! 2014/3/28 			<br/>
 *		  - Model.java      	作成 	<br/>
 *        - View.java			作成		<br/>
 *		  - Controller.java     作成		<br/>
 *		  - PostPanel.java      作成 	<br/>
 *		  - News.java     		作成 	<br/>
 *  0.21 refactoring! 2014/3/28			<br/>
 *		  - PostPanel.java -> Post.java <br/>
 *		  - Post.java			変更		<br/>
 *		  ... Dataは次のversionで			<br/>
 * @author shuma
 * @since 0.2
 * @version 0.21
 */
package jp.shuma.multiclient.mvc;