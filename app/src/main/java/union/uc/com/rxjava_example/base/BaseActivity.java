package union.uc.com.rxjava_example.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by wangli on 4/5/16.
 */
public abstract class BaseActivity extends Activity {

  private LinearLayout mContainer;
  private TextView mDescription;
  private TextView mTip;
  private LinearLayout mWorkspace;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setupUIComponents();
  }

  private void setupUIComponents() {
    mContainer = new LinearLayout(this);
    mContainer.setOrientation(LinearLayout.VERTICAL);

    mDescription = new TextView(this);
    mDescription.setBackgroundColor(Color.BLUE);
    mDescription.setTextColor(Color.YELLOW);
    mDescription.setText(myDescription());
    mContainer.addView(mDescription,
                       new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.WRAP_CONTENT));

    mWorkspace = new LinearLayout(this);
    setupWorkspace(mWorkspace);
    mContainer.addView(mWorkspace,
                       new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                     LinearLayout.LayoutParams.WRAP_CONTENT));

    mTip = new TextView(this);
    mTip.setSingleLine(false);
    appendTip("Messages:(Scroll to show more ...)");
    ScrollView scrollView = new ScrollView(this);
    scrollView.setBackgroundColor(Color.GRAY);
    scrollView.addView(mTip);
    mContainer.addView(scrollView,
                       new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
    setContentView(mContainer);
  }

  protected void appendTip(final String tipLine) {
    //ensure log on ui thread
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mTip.setText(mTip.getText().toString() + "\r\n" + tipLine);
      }
    });
  }


  protected void clearTip() {
    //ensure log on ui thread
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mTip.setText("Messages:(Scroll to show more ...)");
      }
    });
  }

  protected abstract String myDescription();

  protected abstract void setupWorkspace(LinearLayout workspace);
}
