package recovery;

public class RecoveryLinear implements RecoveryBehavior {
  int step;

  public RecoveryLinear(int n) {
    step = n;
  }

  /**
   *calculateRecovery (calculates heal)
   *@currentLife
   *@maxLife
   *@return new current life
   */
  public int calculateRecovery(int currentLife, int maxLife) {
    int currentLifeInst = currentLife;
    if (currentLifeInst > 0) {
      currentLifeInst += step;
    }

    if (currentLifeInst > maxLife) {
      currentLifeInst = maxLife;
    }

    return currentLifeInst;
  }
}
