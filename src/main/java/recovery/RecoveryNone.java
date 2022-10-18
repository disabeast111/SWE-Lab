package recovery;

/**
 * @author David W
 */
public class RecoveryNone implements RecoveryBehavior {
  public int calculateRecovery(int currentLife, int maxLife) {
    return currentLife;
  }
}
