package app.exercise.androidexerciseapplication.view.activities;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import app.exercise.androidexerciseapplication.R;
import app.exercise.androidexerciseapplication.view.fragments.FactsListFragment;

/**
 * Launcher activity of the application
 */
public class MainActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Add default fragment
        addFragment(new FactsListFragment());

    }

    /**
     * This method is used to add fragment
     *
     * @param fragment to be added
     */
    private void addFragment(Fragment fragment) {

        if (mFragmentManager == null)
            mFragmentManager = getSupportFragmentManager();

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getSimpleName().toString()).commit();

    }

    /**
     * This method is used to set action bar title.
     *
     * @param title to be set on actionbar
     */
    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(title);
    }

}
