package union.uc.com.rxjava_example.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.BaseActivity;
import union.uc.com.rxjava_example.base.UIThreadExecutor;

/**
 * Created by wangli on 4/5/16.
 */
public class RWFileActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected String myDescription() {
    return "本示例展示了如何读写并合并两个文件";
  }

  @Override
  protected void setupWorkspace(LinearLayout workspace) {
    TextView tv1 = new TextView(this);
    tv1.setText("File1: abcde");
    TextView tv2 = new TextView(this);
    tv2.setText("File1: ABCDE");
    Button btn = new Button(this);
    btn.setText("Start");
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startRx();
      }
    });

    workspace.setOrientation(LinearLayout.VERTICAL);
    workspace.addView(tv1);
    workspace.addView(tv2);
    workspace.addView(btn);
  }

  private void startRx() {
    final String path1 = getFilesDir() + File.separator + "f1";
    final String path2 = getFilesDir() + File.separator + "f2";

    File f2 = new File(path2);

    Observable<String> o1 = //
      Observable.just(path1)
                .map(mWriteFile)
                .map(mReadFile)
                .compose(new Observable.Transformer<String, String>() {
                  @Override
                  public Observable<String> call(Observable<String> stringObservable) {
                    stringObservable.subscribe(new Action1<String>() {
                      @Override
                      public void call(String s) {
                        appendTip("f1 write read finished");
                      }
                    });
                    return stringObservable;
                  }
                });
    Observable<String> o2 = //
      Observable.just(path2)
                .map(mWriteFile)
                .map(mReadFile)
                .compose(new Observable.Transformer<String, String>() {
                  @Override
                  public Observable<String> call(Observable<String> stringObservable) {
                    stringObservable.subscribe(new Action1<String>() {
                      @Override
                      public void call(String s) {
                        appendTip("f2 write read finished");
                      }
                    });
                    return stringObservable;
                  }
                });
    Observable.combineLatest(o1, o2, new Func2<String, String, String>() {
      @Override
      public String call(String s1, String s2) {
        return s1 + "\r\n" + s2;
      }
    }).observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))//
      .subscribe(new Action1<String>() {
        @Override
        public void call(String s) {
          appendTip("combine finished, result:\r\n" + s);
        }
      });
  }

  private Func1<String, String> mWriteFile = new Func1<String, String>() {
    @Override
    public String call(String s) {
      try {
        FileOutputStream fos = new FileOutputStream(s);
        fos.write(s.getBytes());
      } catch (Exception e) {

      }
      return s;
    }
  };
  private Func1<String, String> mReadFile = new Func1<String, String>() {
    @Override
    public String call(String s) {
      FileInputStream fis = null;
      try {
        File f = new File(s);
        if (f.exists()) {
          byte[] buf = new byte[(int) f.length()];
          fis = new FileInputStream(f);
          fis.read(buf);
          fis.close();
          return new String(buf);
        }
      } catch (Exception e) {
        if (fis != null) {
          try {
            fis.close();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      }
      return null;
    }
  };
}
