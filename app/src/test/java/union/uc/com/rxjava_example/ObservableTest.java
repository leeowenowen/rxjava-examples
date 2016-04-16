package union.uc.com.rxjava_example;

import org.junit.Test;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by wangli on 4/1/16.
 */
public class ObservableTest {
  public static void hello(String... names) {
    Observable.from(names).subscribe(new Action1<String>() {
      @Override
      public void call(String s) {
        System.out.println("Hello " + s + "!");
      }

    });
  }

  @Test
  public void testHello(){
    hello("Ben", "George");
  }
}
