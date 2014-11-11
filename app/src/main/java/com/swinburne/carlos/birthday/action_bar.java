package com.swinburne.carlos.birthday;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class action_bar extends Activity
        implements ActionBar.OnNavigationListener {
    /** The Bundle key to hold current dropdown position. */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.
                new ArrayAdapter<String>(
                        getActionBarThemedContextCompat(),
                        android.R.layout.simple_list_item_1, // android built-in layout
                        android.R.id.text1,  // android built-in id used by layout
                        new String[] {
                                "Sunrise sunset Times",
                                "locations AU",
                                "Table of Sun Rise/Set Times Times",
                                "Map google"
                        }),
                this);
    }
    /////////
    /**
     * Backward-compatible version of {@link android.app.ActionBar#getThemedContext()} that
     * simply returns the {@link android.app.Activity} if
     * <code>getThemedContext</code> is unavailable.
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private Context getActionBarThemedContextCompat()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        {
            return getActionBar().getThemedContext();
        } else
        {
            return this;
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM))
        {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }

    ///////////////////////////////////////////////////////////////////////////////////
    //            MenuGeneral
    ///////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onNavigationItemSelected(int position, long id)
    {
 
        // When the given dropdown item is selected, replace container with new fragment
        android.support.v4.app.Fragment fragment;
        DataListFragment list;
        FragmentManager fm = getSupportFragmentManager();
        if (position == 0) {
            fragment = new SunRise();
            fm.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }

        if(position == 1){
            list = new DataListFragment();
            fm.beginTransaction()
                    .replace(R.id.container, list)
                    .commit();
        }
        /*
        else if(position == 2){
            fragment = new Table();
            Bundle args = new Bundle();
            fragment.setArguments(args);
            fm.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
        else
        {
            fragment = new  MapsActivity();
            fm.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
        */
        return true;

    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.assignment8, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.support.v4.app.Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_action_bar, container, false);
            return rootView;
        }
    }


}
