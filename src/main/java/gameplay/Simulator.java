package gameplay;

import java.awt.HeadlessException;

import commands.Invoker;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.RecoveryRateException;
import lifeform.Alien;
import lifeform.Human;
import random.RandAlien;
import random.RandHuman;
import random.RandInt;
import random.RandWeapon;
import state.AiContext;
import weapon.Weapon;

/**
 * @author Ethan J
 */
/**
 * @author dawol
 *
 */
public class Simulator implements TimerObserver {
  Environment enviro;
  SimpleTimer time;
  int humans;
  int aliens;
  int totalLifeForms;
  AiContext[] aiArray;

  /**
   * Simulator constructor that actually populates the environment passed to it
   * 
   * @param e         is the environment passed to it
   * @param timer     is the timer used to add both lifeforms, weapons, and
   *                  aiContexts as time observers
   * @param numHumans is the amount of humans requested
   * @param numAliens is the amount of aliens requested
   * @throws RecoveryRateException if recov reate is below 0
   */
  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens)
      throws RecoveryRateException {
    enviro = e;
    time = timer;
    humans = numHumans;
    aliens = numAliens;

    int row = enviro.getNumRows();
    int col = enviro.getNumCols();

    // Loop that checks if number of lifeForms will fit in evironment
    while (row * col < humans + aliens) {
      if (humans > 1) {
        humans -= 1;
      }
      if (aliens > 1) {
        aliens -= 1;
      }
    }
    // total used for weapons as for each lifeForm there is one weapon
    totalLifeForms = humans + aliens;
    // Setup for an array to hold AIContexts
    aiArray = new AiContext[totalLifeForms];
    int aiPosition = 0;

    RandInt ranRow = new RandInt(0, row);
    RandInt ranCol = new RandInt(0, col);

    // Loop to add each human requested
    for (int i = 0; i < humans; i++) {

      int humRow = ranRow.choose();
      int humCol = ranCol.choose();

      RandHuman alen = new RandHuman();
      Human entity = alen.choose();

      // While loop to find an empty cell in environment
      while (enviro.getLifeForm(humRow, humCol) != null) {
        humRow = ranRow.choose();
        humCol = ranCol.choose();
      }

      // Adding to array of AIContexts
      AiContext aic = new AiContext(entity, enviro);
      time.addTimeObserver(aic);
      aiArray[aiPosition] = aic;
      aiPosition += 1;

      // Adds human to environment
      time.addTimeObserver(entity);
      enviro.addLifeForm(entity, humRow, humCol);

    }

    // Loop to add each alien requested
    for (int i = 0; i < aliens; i++) {
      int alienRow = ranRow.choose();
      int alienCol = ranCol.choose();

      RandAlien alen = new RandAlien();
      Alien entity2 = alen.choose();

      while (enviro.getLifeForm(alienRow, alienCol) != null) {
        alienRow = ranRow.choose();
        alienCol = ranCol.choose();
      }

      AiContext aic = new AiContext(entity2, enviro);
      time.addTimeObserver(aic);
      aiArray[aiPosition] = aic;
      aiPosition += 1;

      time.addTimeObserver(entity2);
      enviro.addLifeForm(entity2, alienRow, alienCol);

    }

    // Loop to add each weapon requested
    for (int i = 0; i < totalLifeForms; i++) {
      int weaponRow = ranRow.choose();
      int weaponCol = ranCol.choose();
      RandWeapon wepa = new RandWeapon();
      Weapon weaponToAdd = wepa.choose();

      Weapon[] weap = enviro.getWeapons(weaponRow, weaponCol);
      // checks both positions if they are already filled
      while (weap[0] != null && weap[1] != null) {
        weaponRow = ranRow.choose();
        weaponCol = ranCol.choose();
        weap = enviro.getWeapons(weaponRow, weaponCol);
      }
      time.addTimeObserver(weaponToAdd);
      enviro.addWeapon(weaponToAdd, weaponRow, weaponCol);

    }

  }

  /**
   * updateTime() is empty as it is not used by simulator Simulator only needs to
   * implement TimerObserver for timer
   */
  public void updateTime(int time) {

  }


  /**
   * Main method for simulator that creates both Invoker and GameBoard and uses
   * the simulator constructor to populate it It also creates the initial timer
   * and starts it
   * 
   * @param args
   * @throws RecoveryRateException
   *     if recovery rate is below 0
   */
  public static void main(String[] args) throws RecoveryRateException {
    try {

      Environment env = Environment.getEnvironment(10, 10);
      Invoker gui = Invoker.invoker();
      int x = 500;
      int y = 400;
      gui.setBounds(1000, 200, x, y);
      SimpleTimer timer = new SimpleTimer(5000);

      Simulator sim = new Simulator(env, timer, 10, 10);

      GameBoard gb = GameBoard.getInstance();
      for (int row = 0; row < 10; row++) {
        for (int col = 0; col < 10; col++) {
          gb.updateCell(row, col);
        }
      }
      timer.start();
    } catch (HeadlessException e) {
      e.printStackTrace();
    }
  }

  // Getter for AiContext array
  public AiContext[] getAiContextArray() {

    return aiArray;
  }

}
