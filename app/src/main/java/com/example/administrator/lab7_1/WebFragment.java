package com.example.administrator.lab7_1;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment {

    WebView webview;
    String currentURL;

    public WebFragment() {
        // Required empty public constructor
    }


    @Override
    public void onDetach() {

        super.onDetach();
    }

    @Override
    public void onDestroy() {
        currentURL = webview.getUrl();
        super.onDestroy();
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if(currentURL != null) {
            webview.loadUrl(currentURL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_web, container, false);
        webview = (WebView) v.findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        return v;
    }


    public void changeUrl (String url) {
        if (currentURL != null) {
            webview.loadUrl(currentURL);
        } else {
            if (url != null) {
                if (!url.startsWith("http://")) {
                    String temp = "http://" + url;
                    url = temp;
                }
                webview.loadUrl(url);
            }
        }
    }
}
