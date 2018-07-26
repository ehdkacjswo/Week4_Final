package com.example.q.drawer_example;

import android.os.AsyncTask;
import android.telecom.Call;

import java.util.HashMap;

public class HttpRequest extends AsyncTask<Void, Void, String>{
    private Callback<String> callback;
    private HashMap<String, String> hashMap;

    public HttpRequest(Callback callback, HashMap<String, String> hashMap) {
        this.callback = callback;
        this.hashMap = hashMap;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String url = hashMap.get("url");
        hashMap.remove("url");

        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        String result = requestHttpURLConnection.request(url, hashMap);

        return result;
    }

    @Override
    protected void onPostExecute(String res) {
        super.onPostExecute(res);
        callback.onResponse(res);
    }
}
