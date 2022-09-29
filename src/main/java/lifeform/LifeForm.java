package lifeform;

public abstract class LifeForm extends java.lang.Object {
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;

  /**
   * @param name
   * @param points
   */
  public LifeForm(String name, int points) {
    this(name, points, 1);
  }

  /**
   * LifeForm constructor
   * @param name
   * @param points
   * @param attack
   */
  public LifeForm(String name, int points, int attack) {
    myName = name;
    if (points > 0) {
      currentLifePoints = points;
    }
    if (attack >= 0) {
      attackStrength = attack;
    }

  }

  public String getName() {
    return myName;
  }

  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * @param damage
   */
  public void takeHit(int damage) {
    currentLifePoints -= damage;
    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }

  public int getAttackStrength() {
    return attackStrength;
  }

  /**
   * attack
   * @param opponent (type LifeForm)
   */
  public void attack(LifeForm opponent) {
    if (getCurrentLifePoints() > 0) {
      opponent.takeHit(getAttackStrength());
    }

  }
}
