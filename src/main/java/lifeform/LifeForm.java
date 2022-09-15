package lifeform;

public abstract class LifeForm {
  private String myName;
  protected int currentLifePoints;
  protected int attackStrength;

  /**
   * @param name
   * @param points
   */
  public LifeForm(String name, int points) {
    myName = name;
    if (points > 0) {
      currentLifePoints = points;
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
}
