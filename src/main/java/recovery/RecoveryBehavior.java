package recovery;

/**
 * @author David W
 */
public interface RecoveryBehavior {
  public int calculateRecovery(int currentLife, int maxLife);
}
