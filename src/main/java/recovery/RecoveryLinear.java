// author: David W

package recovery;

public class RecoveryLinear implements RecoveryBehavior {
  int step;

  public RecoveryLinear(int n) {
    step = n;
  }

  /**
   * calculateRecovery (calculates heal)
   * 
   * @currentLife
   * @maxLife
   * @return new current life
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife > 0) {
      currentLife += step;
    }

    if (currentLife > maxLife) {
      currentLife = maxLife;
    }

    return currentLife;
  }
}
