package union.uc.com.rxjava_example.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wangli on 4/17/16.
 */
public class SeperatorView extends View {
  public static final int MODE_HORIZENTAL = 1;
  public static final int MODE_VERTICAL = 2;

  public SeperatorView(Context context, int mode) {
    super(context);
    int len = (int) Utils.dipToPixels(context, 2);
    if (MODE_HORIZENTAL == mode) {
      setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, len));
    } else {
      setLayoutParams(new ViewGroup.LayoutParams(len, ViewGroup.LayoutParams.MATCH_PARENT));
    }
    setBackgroundColor(Color.DKGRAY);
  }
}
