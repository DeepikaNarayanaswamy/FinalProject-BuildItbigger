package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Pair;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by P01242 on 11/12/2016.
 */
public class NonEmptyString  extends AndroidTestCase {


    public void testEmpty() {
        try {
             new GCEAsyncTask(null).execute();
            String joke = new GCEAsyncTask(null).get(30, TimeUnit.SECONDS);
            assert joke !=null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
