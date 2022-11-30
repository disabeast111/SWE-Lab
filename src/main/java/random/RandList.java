package random;

import java.util.ArrayList;
import java.util.List;

import exceptions.RecoveryRateException;

public class RandList<A> implements Random<List<A>> {
  private Random<A> ra;
  private int num;

  public RandList(Random<A> r, int m) {
    ra = r;
    num = m;
  }

  public List<A> choose() {
    List<A> ans = new ArrayList<A>();
    for (int i = 0; i < num; i++) {
      try {
        ans.add(ra.choose());
      } catch (RecoveryRateException e) {
        e.printStackTrace();
      }
    }
    return ans;
  }
}