package nl.ekb.webviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class EkbWebView extends Activity {
	
	private class EkbWebViewClient extends WebViewClient {
	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
	}
	
	WebView mWebView;
		
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
	        mWebView.goBack();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
    /** Called when the activity is first created. */
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setWebViewClient(new EkbWebViewClient());

		Intent i = getIntent();
		if (i.getAction().equals("android.intent.action.MAIN")) {
			mWebView.loadUrl("http://www.ekb.nl");
		} else if (i.getAction().equals("android.intent.action.VIEW")) {
			mWebView.loadUrl(i.getDataString());
		} else {
			TextView tv = new TextView(this);
			tv.setText(i.getAction() + " " + i.getDataString());
			setContentView(tv);
		}
	}
}