package com.kostya.bookreader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

/**
 * Created by konstantin on 1/31/2017.
 */

public class SimpleContentFragment extends WebViewFragment {

    public static SimpleContentFragment newInstance(String file) {
        SimpleContentFragment frag = new SimpleContentFragment();

        Bundle args = new Bundle();
        args.putString(Constants.KEY_FILE, file);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        WebView webView = getWebView();
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        webView.loadUrl(getPage());

        return v;
    }

    private String getPage() {
        return getArguments().getString(Constants.KEY_FILE);
    }
}
