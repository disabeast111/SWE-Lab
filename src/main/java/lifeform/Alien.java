package lifeform;

import exceptions.RecoveryRateException;
import recovery.RecoveryBehavior;
import recovery.RecoveryNone;

public class Alien extends LifeForm {
  private RecoveryBehavior recoveryBehavior;
  private int maxLifePoints;
  private int recoveryRate = 0;

  public Alien(String name, int life) throws RecoveryRateException {
    this(name, life, new RecoveryNone(), 0);
  }

  public Alien(String name, int life, RecoveryBehavior rb) throws RecoveryRateException {
    this(name, life, rb, 0);
  }

  /**
   * @param name
   * @param life
   * @param rb
   * @param rate
   * @throws RecoveryRateException
   */
  public Alien(String name, int life, RecoveryBehavior rb, int rate) throws RecoveryRateException {
    super(name, life);
    maxLifePoints = life;
    attackStrength = 10;
    recoveryBehavior = rb;
    if (rate < 0) {
      throw new RecoveryRateException("Rate value can not be negative");
    }
    recoveryRate = rate;
  }

  /**
   * recover (call heal)
   */
  public void recover() {
    if (currentLifePoints > 0 && currentLifePoints <= maxLifePoints) {
      currentLifePoints = recoveryBehavior.calculateRecovery(currentLifePoints, maxLifePoints);
    }
  }
}
