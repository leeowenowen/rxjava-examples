package union.uc.com.rxjava_example.sample;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.subjects.PublishSubject;
import union.uc.com.rxjava_example.base.BaseActivity;

/**
 * Created by wangli on 4/8/16.
 */
public class BufferActivity extends BaseActivity {
  @Override
  protected String myDescription() {
    return "本示例展示缓存的使用";
  }

  @Override
  protected void setupWorkspace(LinearLayout workspace) {
    workspace.setOrientation(LinearLayout.VERTICAL);
    Button btn = new Button(this);
    btn.setText("Start");
    workspace.addView(btn);
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startRx();
      }
    });
  }

  private void startRx() {
    //buffer no skip
    Observable.range(1, 10).buffer(3).subscribe(new Action1<List<Integer>>() {
      @Override
      public void call(List<Integer> integers) {
        appendTip("on buffered result:");
        String s = "";
        for (int l : integers) {
          s += l;
          s += " ";
        }
        appendTip(s);
      }
    });
    //buffer with skip
    Observable.range(1, 10).buffer(2, 3).subscribe(new Action1<List<Integer>>() {
      @Override
      public void call(List<Integer> integers) {
        appendTip("on buffered result:");
        String s = "";
        for (int l : integers) {
          s += l;
          s += " ";
        }
        appendTip(s);
      }
    });
    //self define buffer strategy
    final PublishSubject<Long> p = PublishSubject.create();
    p.buffer(new Func0<Observable<Long>>() {
      @Override
      public Observable<Long> call() {
        final PublishSubject<Long> p2 = PublishSubject.create();
        for (int i = 0; i < 3; ++i) {
          final Long index = (long)i;
          Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
              p2.onNext(index);
            }
          }, 1000 * i, TimeUnit.MILLISECONDS);
        }
        return p2;
      }
    })//
      .doOnNext(new Action1<List<Long>>() {
        @Override
        public void call(List<Long> longs) {
          appendTip("on next");
        }
      })//
      .doOnTerminate(new Action0() {
        @Override
        public void call() {
          appendTip("on terminate");
        }
      })//
      .subscribe(new Action1<List<Long>>() {
        @Override
        public void call(List<Long> ls) {
          appendTip("on buffered result:");
          String s = "";
          for (Long l : ls) {
            s += l;
            s += " ";
          }
          appendTip(s);
        }
      });

    for (int i = 0; i < 2; ++i) {
      final Long index = (long)i;
      Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
        @Override
        public void run() {
            p.onNext(index);
        }
      }, 100 * i, TimeUnit.MILLISECONDS);
    }
  }
}
