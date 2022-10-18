// author: David W

package lifeform;

public class Human extends LifeForm {
  private int armorPoints;

  /**
   * Human constructor
   * 
   * @param name
   * @param life
   * @param armor
   */
  public Human(String name, int life, int armor) {
    super(name, life);
    if (armor <= 0) {
      armor = 0;
    }
    armorPoints = armor;
    attackStrength = 5;
  }

  /**
   * getArmorPoints
   * 
   * @return armorPoints
   */
  public int getArmorPoints() {
    return armorPoints;
  }

  @Override
  public void takeHit(int damage) {
    damage -= armorPoints;
    if (damage < 0) {
      damage = 0;
    }
    currentLifePoints -= damage;
    if (currentLifePoints < 0) {
      currentLifePoints = 0;
    }
  }

  /**
   * setArmorPoints
   * 
   * @param armor
   */
  public void setArmorPoints(int armor) {
    if (armor <= 0) {
      armor = 0;
    }
    armorPoints = armor;
  }
}