package union.uc.com.rxjava_example.sample;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;
import union.uc.com.rxjava_example.base.BaseActivity;
import union.uc.com.rxjava_example.base.UIThreadExecutor;

/**
 * Created by wangli on 4/5/16.
 */
public class ConditionActivity extends BaseActivity {
  @Override
  protected String myDescription() {
    return "本示例展示条件判断:\r\n" +
           "监听任意按钮点击事件,当点击到button3的时候停止监听.";
  }

  @Override
  protected void setupWorkspace(LinearLayout workspace) {
    final PublishSubject<Button> subject = PublishSubject.create();
    for (int i = 0; i < 5; ++i) {
      final Button btn = new Button(this);
      btn.setText("btn" + i);
      btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          subject.onNext(btn);
        }
      });
      workspace.addView(btn, new LinearLayout.LayoutParams(0, 100, 1));
    }
    subject//
      .subscribeOn(Schedulers.computation()).observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))
      .takeUntil(new Func1<Button, Boolean>() {
        @Override
        public Boolean call(Button button) {
          String text = button.getText().toString();
          boolean stop = text.contains("3");
          if (stop) {
            appendTip("Button3 is clicked, stop provide data ...");
          }
          return stop;
        }
      })
      .doOnCompleted(new Action0() {
        @Override
        public void call() {
          appendTip("onComplete!");
        }
      })
      .subscribe(new Action1<Button>() {
        @Override
        public void call(Button button) {
          appendTip(button.getText().toString() + " is clicked!");
        }
      });
  }
}
