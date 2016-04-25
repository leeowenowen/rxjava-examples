package union.uc.com.rxjava_example.sample;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import union.uc.com.rxjava_example.base.BaseActivity;

/**
 * Created by wangli on 4/7/16.
 */
public class AdFetchActivity extends BaseActivity {
  @Override
  protected String myDescription() {
    return "本示例展示广告获取流程及错误处理:获取配置信息(ok)-->获取广告(除错)-->ok";
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

  private class AdConfig {
    private final String mAdvertiser;

    public AdConfig(String advertiser) {
      mAdvertiser = advertiser;
    }

    public String getAdvertiser() {
      return mAdvertiser;
    }
  }

  private class Ad {
    private final String mTitle;

    public Ad(String title) {
      mTitle = title;
    }

    public String getTitle() {
      return mTitle;
    }
  }


  private void startRx() {
    String pub = "pubabc";
    Observable<Ad> o = //
      Observable.just(pub)//
                .observeOn(Schedulers.computation())//
                .map(new Func1<String, AdConfig>() {
                  @Override
                  public AdConfig call(String s) {
                    appendTip("fetch config ok! on " + Thread.currentThread().getName());
                    return new AdConfig("facebook");
                  }
                })//
                .observeOn(Schedulers.io())//
                .flatMap(new Func1<AdConfig, Observable<Ad>>() {
                  @Override
                  public Observable<Ad> call(AdConfig adConfig) {
                    return Observable.error(new Exception("Fetch ad failed!"));
                  }
                })//
                .observeOn(Schedulers.newThread());//
    o.subscribe(new Action1<Ad>() {
      @Override
      public void call(Ad ad) {
        appendTip("finished1, get Ad:" + ad.getTitle() + " on " + Thread.currentThread().getName());
      }
    }, new Action1<Throwable>() {
      @Override
      public void call(Throwable throwable) {
        appendTip("fetch error1:" + throwable.getMessage());
      }
    });

    o.subscribe(new Action1<Ad>() {
      @Override
      public void call(Ad ad) {
        appendTip("finished2, get Ad:" + ad.getTitle() + " on " + Thread.currentThread().getName());
      }
    }, new Action1<Throwable>() {
      @Override
      public void call(Throwable throwable) {
        appendTip("fetch error2:" + throwable.getMessage());
      }
    });


  }
}
