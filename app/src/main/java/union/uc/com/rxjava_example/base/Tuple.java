package union.uc.com.rxjava_example.base;

public class Tuple {
  private Tuple() {
  }

  public static class Tuple2<T1, T2> {
    public T1 item1;
    public T2 item2;

    public Tuple2(T1 item1, T2 item2) {
      this.item1 = item1;
      this.item2 = item2;
    }
  }

  public static class Tuple3<T1, T2, T3> {
    public T1 item1;
    public T2 item2;
    public T3 item3;

    public Tuple3(T1 item1, T2 item2, T3 item3) {
      this.item1 = item1;
      this.item2 = item2;
      this.item3 = item3;
    }
  }
}
