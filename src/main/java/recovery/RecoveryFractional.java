package recovery;

public class RecoveryFractional implements RecoveryBehavior {
  double fraction;

  public RecoveryFractional(double n) {
    fraction = n;
  }

  /**
   * calculateRecovery (calculates heal)
   *@currentLife
   *@maxLife
   *@return new current life
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    int currentLifeInst = currentLife;
    if (currentLifeInst > 0) {
      currentLifeInst += Math.ceil(currentLifeInst * fraction);
    }

    if (currentLifeInst > maxLife) {
      currentLifeInst = maxLife;
    }

    return currentLifeInst;
  }
}
