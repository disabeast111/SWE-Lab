package lifeform;


public class LifeForm {
  private String myName;
  int currentLifePoints;
  
  public LifeForm(String name, int points) {
    myName = name;
    currentLifePoints = points;
  }
  
  public String getName() {
    return myName;
  }
  
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }
}
