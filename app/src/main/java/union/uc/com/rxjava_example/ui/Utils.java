package union.uc.com.rxjava_example.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by wangli on 4/17/16.
 */
public class Utils {
  public static int dipToPixels(Context context, int dipValue) {
    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
    return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
  }
}
