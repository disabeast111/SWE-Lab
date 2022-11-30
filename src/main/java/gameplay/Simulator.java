package gameplay;



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

public class Simulator implements TimerObserver {
  Environment enviro;
  SimpleTimer time;
  int humans;
  int aliens;
  int totalLifeForms;
  AiContext aiArray[];
  
  //private static Simulator theSim;

  public Simulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) throws RecoveryRateException {
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
    //Setup for an array to hold AIContexts
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
      // int nameNum = ranAlienName.choose();
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
      // new Weapon[2];
      // weap = enviro.getWeapons(weaponRow, weaponCol);
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
  public static Simulator getSimulator(Environment e, SimpleTimer timer, int numHumans, int numAliens) throws RecoveryRateException {
    if (theSim == null) {
      theSim = new Simulator(e, timer, numHumans, numAliens);
    }
    return theSim;
  }
  */

  public void updateTime(int time) {
//array of contexts and add each time then update each here
    /**
    for(int i = 0; i < aiArray.length; i++) {
      int tempRow = aiArray[i].getLifeForm().getRow();
      int tempCol = aiArray[i].getLifeForm().getCol();
      GameBoard.getInstance().updateCell(tempRow, tempCol);
      GameBoard.getInstance().updateStats(tempRow, tempCol);
      
    }
    */
    
  }

  public static void main(String[] args) throws AttachmentException, RecoveryRateException {
    Environment env = Environment.getEnvironment(10, 10);
    Invoker gui = Invoker.invoker();
    int x = 500;
    int y = 400;
    gui.setBounds(1000, 200, x, y);
    SimpleTimer timer = new SimpleTimer(1000);
    
    Simulator sim = new Simulator(env, timer, 5, 0);
    
    GameBoard gb = GameBoard.getInstance();
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 10; col++) {
        gb.updateCell(row, col);
      }
    }
    
    timer.start();

  }
  //Getter for AiContext array
  public AiContext[] getAIContextArray() {
 
    return aiArray;
  }

}
