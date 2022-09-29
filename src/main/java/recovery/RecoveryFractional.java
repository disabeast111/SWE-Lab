package recovery;

public class RecoveryFractional implements RecoveryBehavior {
  double fraction;

  public RecoveryFractional(double n) {
    fraction = n;
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
      currentLife += Math.ceil(currentLife * fraction);
    }

    if (currentLife > maxLife) {
      currentLife = maxLife;
    }

    return currentLife;
  }
}
