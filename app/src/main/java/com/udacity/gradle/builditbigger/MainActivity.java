package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jokeandroid.JokeActivity;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
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

/*    public void launchJokeActivity(View view) {
//        JokeTell jokeSource = new JokeTell();
//        String joke = jokeSource.getJoke();

        new EndpointsAsyncTask(this).execute(new Pair<Context, String>(this, "Manfred"));

    }*/

    public void launchJokeActivity(View view){

        final JokeEndPointsAsyncTask asyncTask = (JokeEndPointsAsyncTask) new JokeEndPointsAsyncTask(new JokeEndPointsAsyncTask.AsyncResponse() {

            @Override
            public void processResponse(String output) {
                //Log.d(YOLOPAD, RESPONSE_FROM_SERVER_IS + output);

                result = output;
                //    Log.d("Yolopad","Output is " + result);

            }


        }).execute();


        //  Log.d("Yolopad","Joke is " +result);
        if(! result.isEmpty()){
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, result);
            startActivity(intent);
        }
        else
        {
            //    Log.d("Yolopad","Joke is " +result);
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
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

 /*   public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
        //private static MyApi myApiService = null;
        private MyApi myApiService = null;
        private Context context;
        public EndpointsAsyncTask(Context context){
            this.context=context;
        }

        @Override
        protected String doInBackground(Pair<Context, String>... params) {
            if (myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0].first;
            String name = params[0].second;

            try {
                return myApiService.sayHi(name).execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            super.onPostExecute(result);
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, result);
            startActivity(intent);
        }
    }
*/
}
