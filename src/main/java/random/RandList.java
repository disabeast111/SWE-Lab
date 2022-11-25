package random;

import java.util.ArrayList;
import java.util.List;

public class RandList<A> implements Random<List<A>> {
  private Random<A> ra;
  private int n;

  public RandList(Random<A> r, int m) {
    ra = r;
    n = m;
  }

  public List<A> choose() {
    List<A> ans = new ArrayList<A>();
    for (int i = 0; i < n; i++)
      ans.add(ra.choose());
    return ans;
  }
}