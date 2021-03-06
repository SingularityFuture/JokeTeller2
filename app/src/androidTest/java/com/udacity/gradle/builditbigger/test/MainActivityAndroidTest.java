package com.udacity.gradle.builditbigger.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;

import com.udacity.gradle.builditbigger.JokeEndPointsAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;


 /**
 * Created by Michael on 12/17/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityAndroidTest extends AndroidJUnitRunner {
    @Test
    public void testJokeRetrieval()
    {
//        final CountDownLatch signal = new CountDownLatch(1);
        Context context = InstrumentationRegistry.getTargetContext();

        JokeEndPointsAsyncTask jokeEndPointsAsyncTask = (JokeEndPointsAsyncTask) new JokeEndPointsAsyncTask(new JokeEndPointsAsyncTask.AsyncResponse() {
            @Override
            public void processResponse(String output) {
                assertNotNull(output);
                assertNotSame(output,"");
                //signal.countDown();
            }
        },context).execute();

/*        try {
            signal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail("Operation timed out!");
        }*/
    }
}
