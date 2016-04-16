package union.uc.com.rxjava_example.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import us.feras.mdv.MarkdownView;

/**
 * Created by wangli on 4/12/16.
 */
public abstract class APIBaseActivity extends AppCompatActivity {
  private TextView mLog;
  private MarkdownView mMarkdownView;

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
    //top
    LinearLayout top = new LinearLayout(this);
    TextView description = new TextView(this);
    description.setBackgroundColor(Color.BLUE);
    description.setTextColor(Color.YELLOW);
    description.setText(myDescription());

    final Button btnClearLog = new Button(this);
    btnClearLog.setText("Clear Log");
    btnClearLog.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        clearLog();
      }
    });

    top.addView(description,
                new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
    top.addView(btnClearLog,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                              LinearLayout.LayoutParams.WRAP_CONTENT));

    // bottom
    LinearLayout bottom = new LinearLayout(this);

    mLog = new TextView(this);
    mLog.setSingleLine(false);
    ScrollView scrollView = new ScrollView(this);
    scrollView.setBackgroundColor(Color.GRAY);
    scrollView.addView(mLog);
    bottom.addView(scrollView,
                   new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
    ListView actionList = new ListView(this);
    onRegisterAction(mActionAdapter);
    actionList.setAdapter(mActionAdapter);
    bottom.addView(actionList,
                   new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));

    LinearLayout container = new LinearLayout(this);
    container.setOrientation(LinearLayout.VERTICAL);

    container.addView(top,
                      new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.WRAP_CONTENT));
    mMarkdownView = new MarkdownView(this);
    container.addView(mMarkdownView,
                      new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 2));

    container.addView(bottom,
                      new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 3));
    setContentView(container);
  }

  protected void logNotImplemented() {
    log("RxJava not implement!");
  }

  protected void logUseObservable() {
    log("Use Observable!");
  }

  protected <T> void log(T value) {
    log("" + value);
  }

  protected void log(Throwable throwable) {
    log("error:" + throwable.getMessage());
  }

  protected void log(final String tipLine) {
    //ensure log on ui thread
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mLog.setText(mLog.getText().toString() + "\r\n" + tipLine);
      }
    });
  }

  protected void clearLog() {
    //ensure log on ui thread
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mLog.setText("");
      }
    });
  }


  protected void sleep(int millsecond) {
    try {
      Thread.sleep(millsecond);
    } catch (Exception e) {
      log(e.getMessage());
    }
  }

  private ActionAdapter mActionAdapter = new ActionAdapter();

  private class ActionItem {
    public String name;
    public String code;
    public Runnable action;
  }

  private class ActionAdapter extends BaseAdapter implements ActionRegistery {
    private List<ActionItem> mActions = new ArrayList<>();

    @Override
    public void add(String name, Runnable action) {
      add(name, "", action);
    }

    //from ActionRegistery
    @Override
    public void add(String name, String code, Runnable action) {
      ActionItem item = new ActionItem();
      item.name = name;
      item.code = code;
      item.action = action;
      mActions.add(item);
    }

    //from BaseAdapter
    @Override
    public int getCount() {
      return mActions.size();
    }

    @Override
    public Object getItem(int position) {
      return mActions.get(position);
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
        tv = new TextView(parent.getContext());
      }
      final ActionItem item = (ActionItem) getItem(position);
      tv.setPadding(20, 20, 20, 10);
      tv.setText(item.name);
      tv.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          clearLog();

          TextView tv = (TextView) v;
          log(tv.getText().toString());
          log("----------");
          // mMarkdownView.loadMarkdown("```\n\n" + item.code + "\n\n```");
          mMarkdownView.loadMarkdown("\n\n" + process(code));
          item.action.run();
        }
      });
      return tv;
    }
  }

  private final String code = "" +
                              "        Observable.just(\"a\", \"b\")\n" +
                              "                  .observeOn(Schedulers.from(UIThreadExecutor.SINGLETON))\n" +
                              "                  .subscribe(new Action1<String>() {\n" +
                              "                    @Override\n" +
                              "                    public void call(String s) {\n" +
                              "                      log(s + \" on \" + Thread.currentThread().getName());\n" +
                              "                    }\n" +
                              "                  });\n";

  private String process(String s) {
    return s;
  }

  protected String myDescription() {
    return "";
  }

  protected interface ActionRegistery {
    void add(String name, Runnable action);

    void add(String name, String code, Runnable action);
  }

  protected abstract void onRegisterAction(ActionRegistery registery);
}
