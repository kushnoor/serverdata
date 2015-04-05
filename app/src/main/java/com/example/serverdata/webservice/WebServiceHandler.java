package com.example.serverdata.webservice;

import android.content.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Ayesha on 4/5/2015.
 */
public class WebServiceHandler {
    public String GetJSONData(String url)
    {
        String response = null;

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpEntity httpEntity = null;
        HttpResponse httpResponse = null;

        try
        {
            httpResponse = httpClient.execute(httpGet);
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return response;
    }
}
