package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Pair;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * Created by P01242 on 11/12/2016.
 */
public class NonEmptyString  extends AndroidTestCase {

    @Test
    public void testEmpty() {
        try {
            String joke = new GCEAsyncTask().execute(new Pair<Context, String>(getContext(), null)).get();
            assert joke !=null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
