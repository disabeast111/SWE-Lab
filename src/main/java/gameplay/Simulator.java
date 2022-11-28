package gameplay;

import environment.Environment;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import random.RandInt;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;

public class Simulator implements TimerObserver {
  Environment enviro;
  SimpleTimer time;
  int humans;
  int aliens;
  int totalLifeForms;
  String humanNames[] = new String[] { "Bob", "Jim", "Tim", "Emma", "Anna", "Katy", "Juan", "Zach", 
      "Bella", "Rob", "Chris", "Mike", "Shelly", "Lena", "Bartholomew the IV" };
  String alienNames[] = new String[] { "Zurg", "ET", "Yoda", "Grogu", "Xeno", "Groot", "Chewie", 
      "Predator", "Spock", "Borg" };
  RecoveryBehavior recov[] = new RecoveryBehavior[] {new RecoveryNone(), new RecoveryLinear(5), new RecoveryFractional(.5)};
  
  // need array of human names
  // need array of alien names
  // need array of all recovery rates?
  // need array of weapons?
  String recovRates[] = new String[] {};
  String weapons[] = new String[] {};

  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) throws RecoveryRateException {
    enviro = e;
    time = timer;
    humans = numHumans;
    aliens = numAliens;
    totalLifeForms =numHumans + numAliens;

    // Row and col to be used in addLifeForm
    int row = enviro.getNumRows();
    int col = enviro.getNumCols();

    // Initialized RandInt constructors
    RandInt ranHumName = new RandInt(0, humanNames.length);
    RandInt ranAlienRecov = new RandInt(0, recov.length);
    RandInt ranArmor = new RandInt(0, 9);
    RandInt ranHumLife = new RandInt(10, 50);
    RandInt ranAlienLife = new RandInt(10,30);
    RandInt ranRow = new RandInt(0, row);
    RandInt ranCol = new RandInt(0, col);
    RandInt ranAlienName = new RandInt(0, alienNames.length);

    // Loop to add each human requested
    for (int i = 0; i < humans; i++) {
      // All random stats for human
      int nameNum = ranHumName.choose();
      //int life = ranHumLife.choose();
      //int armor = ranArmor.choose();
      int humRow = ranRow.choose();
      int humCol = ranCol.choose();

      // While loop to find an empty cell in environment
      while (enviro.getLifeForm(humRow, humCol) != null) {
        humRow = ranRow.choose();
        humCol = ranCol.choose();
      }
      //Creates human to add
      LifeForm entity = new Human(humanNames[nameNum], ranHumLife.choose(), ranArmor.choose());
      //Adds human to environment
      enviro.addLifeForm(entity, humRow, humCol);

    }

    // Loop to add each alien requested
    for (int i = 0; i < aliens; i++) {
      int nameNum = ranAlienName.choose();
      int alienRow = ranRow.choose();
      int alienCol = ranCol.choose();
      int recovNum = ranAlienRecov.choose();
      
      while(enviro.getLifeForm(alienRow, alienCol) != null) {
        alienRow = ranRow.choose();
        alienCol = ranCol.choose();
      }
      
      LifeForm entity = new Alien(alienNames[nameNum], ranAlienLife.choose(), recov[recovNum] );
      enviro.addLifeForm(entity, alienRow, alienCol);
    }
    
    // Loop to add each weapon requested
    for(int i = 0; i < totalLifeForms; i++) {
      
    }

  }

  public void updateTime(int time) {

  }

}
