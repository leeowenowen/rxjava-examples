package union.uc.com.rxjava_example.sample;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.BaseActivity;
import union.uc.com.rxjava_example.base.UIThreadExecutor;

/**
 * Created by wangli on 4/8/16.
 */
public class GroupByActivity extends BaseActivity {
  private LinearLayout mGroupContainer;

  @Override
  protected String myDescription() {
    return "本示例展示如何进行分组处理,将1-6按照奇偶分成两组展示.";
  }

  @Override
  protected void setupWorkspace(LinearLayout workspace) {
    workspace.setOrientation(LinearLayout.VERTICAL);
    mGroupContainer = new LinearLayout(this);
    Button btn = new Button(this);
    btn.setText("Start");
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startRx();
      }
    });
    workspace.addView(btn);
    workspace.addView(mGroupContainer);
  }

  private void startRx() {
    Observable.range(1, 6).groupBy(new Func1<Integer, Integer>() {
      @Override
      public Integer call(Integer integer) {
        return integer % 2;
      }
    })//
      .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
      .subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
        @Override
        public void call(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
          final int key = integerIntegerGroupedObservable.getKey();
          final RadioGroup group = new RadioGroup(GroupByActivity.this);
          group.setTag(key);
          mGroupContainer.addView(group);
          appendTip("group " + key + " ok");
          integerIntegerGroupedObservable//
            .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
            .subscribe(new Action1<Integer>() {
              @Override
              public void call(Integer integer) {
                RadioButton btn = new RadioButton(getApplicationContext());
                btn.setText("" + integer);
                group.setBackgroundColor((key == 0) ? Color.RED : Color.BLUE);
                group.addView(btn);
                appendTip("make radio item, [id:" + integer + "][key:" + key + "]");
              }
            });
        }
      });
  }
}
