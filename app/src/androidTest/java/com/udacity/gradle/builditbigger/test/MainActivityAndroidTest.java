package com.udacity.gradle.builditbigger.test;

import android.support.test.runner.AndroidJUnitRunner;

import com.udacity.gradle.builditbigger.JokeEndPointsAsyncTask;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.fail;


/**
 * Created by Michael on 12/17/2016.
 */



public class MainActivityAndroidTest extends AndroidJUnitRunner {
    public void testJokeRetrieval()
    {
        final CountDownLatch signal = new CountDownLatch(1);

        JokeEndPointsAsyncTask jokeEndPointsAsyncTask = (JokeEndPointsAsyncTask) new JokeEndPointsAsyncTask(new JokeEndPointsAsyncTask.AsyncResponse() {
            @Override
            public void processResponse(String output) {
                assertNotNull(output);
                assertNotSame(output,"");
                signal.countDown();
            }
        }).execute();

        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Operation timed out!");
        }
    }
}
