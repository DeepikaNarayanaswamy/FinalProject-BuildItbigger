package com.udacity.gradle.free.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.udacity.gradle.builditbigger.AsyncTaskCompleted;
import com.udacity.gradle.builditbigger.GCEAsyncTask;
import com.udacity.gradle.builditbigger.R;

import com.example.android.androidjokelibrary.MainJokeActivity;
import com.jokes.JokesProvider;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        // comment for now .. i will put a new button
        /*JokesProvider jokes = new JokesProvider();

        Toast.makeText(this, jokes.getJoke(), Toast.LENGTH_SHORT).show();*/
        tellJokeTest(view);
    }

    public void launchJokeActivity(View view){
        Intent intent = new Intent(this,MainJokeActivity.class);
        JokesProvider jokes = new JokesProvider();

        intent.putExtra("JOKE",jokes.getJoke());
        startActivity(intent);
    }

    public void tellJokeTest(View view) {
        try {

            AsyncTaskCompleted taskCompleted = new AsyncTaskCompleted() {
                @Override
                public void onTaskCompleted(String result) {
                    Intent intent = new Intent(getApplicationContext(), MainJokeActivity.class);

                    intent.putExtra("JOKE", result);
                    startActivity(intent);
                }
            };
            new GCEAsyncTask(taskCompleted).execute(new Pair<Context, String>(this.getApplicationContext(), null));

        } catch (Exception ex) {
            ex.getMessage();
        }

    }


}
