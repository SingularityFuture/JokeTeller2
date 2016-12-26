package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jokeandroid.JokeActivity;

public class MainActivity extends AppCompatActivity {

    String result = "";//Holds result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
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

    public void launchJokeActivity(View view){

        final JokeEndPointsAsyncTask asyncTask = (JokeEndPointsAsyncTask) new JokeEndPointsAsyncTask(new JokeEndPointsAsyncTask.AsyncResponse() {

            @Override
            public void processResponse(String output) {
                //Log.d(YOLOPAD, RESPONSE_FROM_SERVER_IS + output);

                result = output;
                //    Log.d("Yolopad","Output is " + result);

            }


        }, this).execute();


        //  Log.d("Yolopad","Joke is " +result);
        if(! result.isEmpty()){
            //findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, result);
            startActivity(intent);
        }
        else
        {
            //    Log.d("Yolopad","Joke is " +result);
            //findViewById(R.id.progress_bar).setVisibility(View.GONE);
            String string = this.getString(R.string.fail_text);
            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
