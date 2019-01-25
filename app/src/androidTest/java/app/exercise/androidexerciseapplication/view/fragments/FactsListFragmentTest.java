package app.exercise.androidexerciseapplication.view.fragments;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.exercise.androidexerciseapplication.beans.FactsBean;
import app.exercise.androidexerciseapplication.viewmodel.FactsListViewModel;

import static org.junit.Assert.*;

public class FactsListFragmentTest {

    FactsListViewModel factsListViewModel = new FactsListViewModel(null);

    @Test
    public void test()
    {
        factsListViewModel.getFactsList().observeForever(new Observer<FactsBean>() {
            @Override
            public void onChanged(@Nullable FactsBean factsBean) {

                Assert.assertNotNull(factsBean);
            }
        });

        factsListViewModel.getMessage().observeForever(new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Assert.assertNotNull(s);
            }
        });

    }

}