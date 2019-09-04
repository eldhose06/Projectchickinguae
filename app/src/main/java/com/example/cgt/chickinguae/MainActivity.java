package com.example.cgt.chickinguae;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String[] mobileArray = {"", ""};
    ImageView img;

    private String apiPath = "https://mobileapp.chickinguae.com/index.php/api/banners/cake";

    private JSONArray restulJsonArray;
    private int success = 0;

    private ListView listView;

    ViewPager viewPager;
    CustomSwipeAdapter adapter1;
    private Timer timer;
    private int current_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Food Menu");
        new ServiceStubAsyncTask(this, this).execute();
        // img = findViewById(R.id.image1);

        toolbar.setBackgroundColor(Color.parseColor("#CD5C5C"));
        setSupportActionBar(toolbar);

        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.list_view, R.id.text1, mobileArray);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String methodURL = "banners/cake";


        viewPager = findViewById(R.id.image1);
        adapter1 = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter1);
        createslideShow();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createslideShow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (current_position == CustomSwipeAdapter.length )
                    current_position = 0;
                viewPager.setCurrentItem(current_position++, true);

            }

        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        } ,250,2500);

    }

    private class ServiceStubAsyncTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private Activity mActivity;
        String response = "";
        HashMap<String, String> postDataParams;

        public ServiceStubAsyncTask(Context context, Activity activity) {
            mContext = context;
            mActivity = activity;
        }


        @Override
        protected Void doInBackground(Void... arg0) {
            System.out.println("haii");
            postDataParams = new HashMap<String, String>();

            postDataParams.put("HTTP_ACCEPT", "application/json");


            HttpConnectionService service = new HttpConnectionService();
            response = service.sendRequest(apiPath, postDataParams);
            try {
                System.out.println("output    " + response);
                success = 1;
                JSONObject resultJsonObject = new JSONObject(response);
                System.out.println(resultJsonObject.toString());
                System.out.println(resultJsonObject.getString("status"));
                System.out.println("codegreen");
                restulJsonArray = resultJsonObject.getJSONArray("data");
                System.out.println(restulJsonArray);
            } catch (JSONException e) {
                success = 0;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            System.out.println("post");
            super.onPostExecute(result);
            System.out.println(result);


        }

    }//end of async task
}
