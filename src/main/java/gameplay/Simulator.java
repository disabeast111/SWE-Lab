package gameplay;

import java.util.Random;

import environment.Environment;
import lifeform.Human;
import lifeform.LifeForm;
import random.RandInt;

public class Simulator implements TimerObserver {
  Environment enviro;
  SimpleTimer time;
  int humans;
  int aliens;
  String humanNames[] = new String[] { "Bob", "Jim", "Tim", "Emma", "Anna", "Katy", "Juan", "Zach", "Bella", "Rob",
      "Chris", "Mike", "Shelly", "Lena", "Bartholomew" };
  String alienNames[] = new String[] { "Zurg", "ET", "Yoda", "Grogu", "Xeno", "Groot", "Chewie", "Predator", "Spock",
      "Borg" };
  // need array of human names
  // need array of alien names
  // need array of all recovery rates?
  // need array of weapons?
  String recovRates[] = new String[] {};
  String weapons[] = new String[] {};

  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) {
    enviro = e;
    time = timer;
    humans = numHumans;
    aliens = numAliens;

    // Row and col to be used in addLifeForm
    int row = enviro.getNumRows();
    int col = enviro.getNumCols();

    // Initialized RandInt constructors
    RandInt ranHumName = new RandInt(0, humanNames.length);
    RandInt ranArmor = new RandInt(0, 9);
    RandInt ranHumLife = new RandInt(10, 50);
    RandInt ranRow = new RandInt(0, row);
    RandInt ranCol = new RandInt(0, col);

    // Loop to add each human requested
    for (int i = 0; i < humans; i++) {
      // All random stats for human
      int nameNum = ranHumName.choose();
      int life = ranHumLife.choose();
      int armor = ranArmor.choose();
      int humRow = ranRow.choose();
      int humCol = ranCol.choose();

      // While loop to find an empty cell in environment
      while (enviro.getLifeForm(humRow, humCol) != null) {
        humRow = ranRow.choose();
        humCol = ranCol.choose();
      }
      //Creates human to add
      LifeForm entity = new Human(humanNames[nameNum], life, armor);
      //Adds human to environment
      enviro.addLifeForm(entity, humRow, humCol);

    }

    // Loop to add each alien requested
    for (int i = 0; i < aliens; i++) {

    }

  }

  public void updateTime(int time) {

  }

}
