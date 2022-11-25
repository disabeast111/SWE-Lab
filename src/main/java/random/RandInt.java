package random;

public class RandInt implements Random<Integer> {
  private int lo;
  private int hi;

  public RandInt(int l, int h) {
    lo = l;
    hi = h;
  }

  public Integer choose() {
    return new java.util.Random().nextInt(hi - lo) + lo;
  }
}