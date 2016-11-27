package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.example.android.androidjokelibrary.MainJokeActivity;
import com.example.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by P01242 on 11/7/2016.
 */
public class GCEAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private AsyncTaskCompleted taskCompletedListener;

    public  GCEAsyncTask(AsyncTaskCompleted taskCompleted){
        taskCompletedListener = taskCompleted;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("https://androidbuild-9.appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        if (params != null && params.length > 0)
            context = params[0].first;
        //String name = params[0].second;

        try {
            Log.v("GCEASYNC TASK","Inside async task");
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        System.out.println("Post execute" + result);
        System.out.println("Post execute Context" + context);
        if (taskCompletedListener != null)
            taskCompletedListener.onTaskCompleted(result);

        /* if (context != null) {
             Intent intent = new Intent(context, MainJokeActivity.class);
             intent.putExtra("JOKE", result);
             context.getApplicationContext().startActivity(intent);
         }*/
       /* Intent intent = new Intent(context,MainJokeActivity.class);
        intent.putExtra("JOKE",result);
        context.startActivity(intent);*/
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}