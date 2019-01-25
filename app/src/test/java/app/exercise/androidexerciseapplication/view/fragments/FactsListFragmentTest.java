package app.exercise.androidexerciseapplication.view.fragments;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.mockito.Mockito.mock;

import app.exercise.androidexerciseapplication.beans.FactsBean;
import app.exercise.androidexerciseapplication.viewmodel.FactsListViewModel;

import static org.junit.Assert.*;

public class FactsListFragmentTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    FactsListViewModel factsListViewModel = new FactsListViewModel(null);
    Observer observer = mock(Observer.class);

    @Before
    public void init() throws Exception {
        MutableLiveData<FactsBean> factsBeanLiveData = new MutableLiveData<FactsBean>();
        factsListViewModel.setFactsBeanLiveData(factsBeanLiveData);
        factsListViewModel.getFactsList().observeForever(observer);

        MutableLiveData<String> message = new MutableLiveData<>();
        factsListViewModel.setMessage(message);
        factsListViewModel.getMessage().observeForever(observer);

    }

    @Test
    public void test() {

        Assert.assertNotNull(factsListViewModel.getFactsList());
    }

    @Test
    public void testMessage() {

        Assert.assertNotNull(factsListViewModel.getMessage());
    }
}