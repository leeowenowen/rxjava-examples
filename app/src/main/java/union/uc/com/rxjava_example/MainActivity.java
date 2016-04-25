package union.uc.com.rxjava_example;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import union.uc.com.rxjava_example.api.AsyncActivity;
import union.uc.com.rxjava_example.api.BlockingObservableActivity;
import union.uc.com.rxjava_example.api.CombineActivity;
import union.uc.com.rxjava_example.api.ConnectableObservableActivity;
import union.uc.com.rxjava_example.api.CustomeOperatorActivity;
import union.uc.com.rxjava_example.api.ErrorHandleActivity;
import union.uc.com.rxjava_example.api.FilterActivity;
import union.uc.com.rxjava_example.api.MathAggregateActivity;
import union.uc.com.rxjava_example.api.ObservableCreateActivity;
import union.uc.com.rxjava_example.api.PluginActivity;
import union.uc.com.rxjava_example.api.ReactiveStreamActivity;
import union.uc.com.rxjava_example.api.SchedulerActivity;
import union.uc.com.rxjava_example.api.StringActivity;
import union.uc.com.rxjava_example.api.SubjectActivity;
import union.uc.com.rxjava_example.api.TransformationActivity;
import union.uc.com.rxjava_example.api.UtilityActivity;
import union.uc.com.rxjava_example.base.Tuple;
import union.uc.com.rxjava_example.sample.AdFetchActivity;
import union.uc.com.rxjava_example.sample.AutoCompleteActivity;
import union.uc.com.rxjava_example.sample.BufferActivity;
import union.uc.com.rxjava_example.sample.ConditionActivity;
import union.uc.com.rxjava_example.sample.GroupByActivity;
import union.uc.com.rxjava_example.sample.OtherAPIActivity;
import union.uc.com.rxjava_example.sample.RWFileActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView mListView;
    protected ActivityAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mListView = (ListView) findViewById(R.id.type_list);
        switchToApi();
    }

    private void switchToSample() {
        getWindow().setTitle(getResources().getString(R.string.sample));
        mListAdapter = new ActivityAdapter();
        mListAdapter.register("AutoComplete", AutoCompleteActivity.class);
        mListAdapter.register("ConditionTest", ConditionActivity.class);
        mListAdapter.register("ReadWriteFile", RWFileActivity.class);
        mListAdapter.register("Fetch Ad", AdFetchActivity.class);
        mListAdapter.register("Group by", GroupByActivity.class);
        mListAdapter.register("Buffer", BufferActivity.class);
        mListAdapter.register("OtherAPI", OtherAPIActivity.class);
        mListView.setAdapter(mListAdapter);
    }

    private void switchToApi() {
        getWindow().setTitle(getResources().getString(R.string.api));
        mListAdapter = new ActivityAdapter();
        mListAdapter.register("Async", AsyncActivity.class);
        mListAdapter.register("BlockingObservable", BlockingObservableActivity.class);
        mListAdapter.register("Combine", CombineActivity.class);
        mListAdapter.register("Condition", union.uc.com.rxjava_example.api.ConditionActivity.class);
        mListAdapter.register("ConnectableObserverable", ConnectableObservableActivity.class);
        mListAdapter.register("CustomeOperator", CustomeOperatorActivity.class);
        mListAdapter.register("ErrorHandler", ErrorHandleActivity.class);
        mListAdapter.register("Filter", FilterActivity.class);
        mListAdapter.register("Math & Aggregate", MathAggregateActivity.class);
        mListAdapter.register("ObservableCreate", ObservableCreateActivity.class);
        mListAdapter.register("Plugin", PluginActivity.class);
        mListAdapter.register("Scheduler", SchedulerActivity.class);
        mListAdapter.register("String", StringActivity.class);
        mListAdapter.register("Subject", SubjectActivity.class);
        mListAdapter.register("Transform", TransformationActivity.class);
        mListAdapter.register("Utility", UtilityActivity.class);
        mListAdapter.register("ReactiveStream", ReactiveStreamActivity.class);
        mListView.setAdapter(mListAdapter);
    }

    protected class ActivityAdapter extends BaseAdapter {
        private List<Tuple.Tuple2<String, Class<?>>> mActivityClses = new ArrayList<>();

        public void register(String name, Class<?> activityCls) {
            mActivityClses.add(new Tuple.Tuple2<String, Class<?>>(name, activityCls));
        }

        @Override
        public int getCount() {
            return mActivityClses.size();
        }

        @Override
        public Object getItem(int position) {
            return mActivityClses.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
            if (convertView != null) {
                tv = (TextView) convertView;
            } else {
                tv = new TextView(getApplicationContext());
            }
            tv.setGravity(Gravity.CENTER);
            final Tuple.Tuple2<String, Class<?>> item = mActivityClses.get(position);
            tv.setText(item.item1);
            tv.setTextColor(Color.BLACK);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, item.item2));
                }
            });
            AbsListView.LayoutParams lp =
                    new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, 100);
            tv.setLayoutParams(lp);
            return tv;

        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_api) {
            switchToApi();

        } else if (id == R.id.sample) {
            switchToSample();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
