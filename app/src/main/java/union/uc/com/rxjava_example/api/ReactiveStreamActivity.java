package union.uc.com.rxjava_example.api;

import union.uc.com.rxjava_example.base.APIBaseActivity;
import union.uc.com.rxjava_example.contants.Constants;

/**
 * Created by wangli on 4/12/16.
 */
public class ReactiveStreamActivity extends APIBaseActivity {

  @Override
  protected void onRegisterAction(ActionRegistery registery) {
    registery.add(Constants.ReactiveStream.materialize, new Runnable() {
      @Override
      public void run() {

      }
    });
  }
}
