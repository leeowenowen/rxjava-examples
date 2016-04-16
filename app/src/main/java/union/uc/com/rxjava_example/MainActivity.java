package union.uc.com.rxjava_example;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
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

public class MainActivity extends AppCompatActivity {

  private ListView mListView;
  private ActivityAdapter mListAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);

    mListView = new ListView(this);
    setContentView(mListView);

    mListAdapter = new ActivityAdapter();
    //    mListAdapter.add("AutoComplete", AutoCompleteActivity.class);
    //    mListAdapter.add("ConditionTest", ConditionActivity.class);
    //    mListAdapter.add("ReadWriteFile", RWFileActivity.class);
    //    mListAdapter.add("Fetch Ad", AdFetchActivity.class);
    //    mListAdapter.add("Group by", GroupByActivity.class);
    //    mListAdapter.add("Buffer", BufferActivity.class);
    //    mListAdapter.add("OtherAPI", OtherAPIActivity.class);
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

  private class ActivityAdapter extends BaseAdapter {
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

}
