package com.donnicholaus.instapals.util;

import android.content.Context;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class SslRuinedDownloader extends BaseImageDownloader {

    public SslRuinedDownloader(Context context) {
        super(context);
    }

    @Override
    protected HttpURLConnection createConnection(String url, Object extra) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) super.createConnection(url, extra);

        //here you go
        conn.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
//        conn.setSSLSocketFactory(...);
        return conn;
    }

}