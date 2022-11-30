package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import state.ActionState;
import state.AiContext;
import weapon.Pistol;
import weapon.Weapon;

public class TestSimulator {
  Environment env = Environment.getEnvironment(10, 10);
  SimpleTimer timer = new SimpleTimer(1000);
  
  //Simulator sim = Simulator.getSimulator(env, timer, 2, 2);

  /**
   * Test to make sure the sim is populating the environment
   * It goes through every cell and adds together all the lifeforms and weapons it
   * comes across
   * @throws RecoveryRateException
   */
  @Test
  public void testSimulatorPopulation() throws RecoveryRateException {

    Simulator sim = new Simulator(env, timer, 2, 2);
    int lifeFormNum = 0;
    int weaponNum = 0;
    
    for(int i = 0; i < 10; i++) {
      for(int k = 0; k < 10; k++) {
        if(env.getLifeForm(i, k) != null) {
          lifeFormNum += 1;
        }
        Weapon weapArray[] = env.getWeapons(i, k);
        if(weapArray[0] != null){
          weaponNum += 1;
        }
        if(weapArray[1] != null) {
          weaponNum += 1;
        }
      }
    }
    
    assertEquals(4, lifeFormNum);
    assertEquals(4, weaponNum);
    env.clearBoard();
    
  }
  /**
   * Test for contextArray created in Simulator to hold all lifeform's contexts
   * @throws RecoveryRateException
   */
  @Test
  public void testAIContextArray() throws RecoveryRateException {
    
    Pistol p = new Pistol();
    Pistol p2 = new Pistol();
    env.addWeapon(p, 0, 1);
    env.addWeapon(p2, 0, 1);
    Simulator sim = new Simulator(env, timer, 2, 2);
    AiContext[] array = sim.getAIContextArray();
    for(int i = 0; i < 4; i++) {
      assertNotNull(array[i]);
    }
    env.clearBoard();
  }
  
  /**
   * Test to make sure contexts are changing on the board when the timer runs
   * Test adds a weapon to every cell so wherever lifeform is placed it will
   * change state when updateTime is called as it will grab a weapon
   * @throws RecoveryRateException
   */
  @Test
  public void testSimulatorTimer() throws RecoveryRateException {
    Simulator sim = new Simulator(env, timer, 1, 0);

    Pistol p = new Pistol();
    for(int i = 0; i < 10; i++) {
      for(int k = 0; k < 10; k++) {
        env.addWeapon(p, i, k);
      }
    }
//    Pistol p2 = new Pistol();
//    Pistol p3 = new Pistol();
//    Pistol p4 = new Pistol();
//    env.addWeapon(p, 0, 0);
//    env.addWeapon(p2, 0, 1);
//    env.addWeapon(p3, 1, 0);
//    env.addWeapon(p4, 1, 1);
    AiContext[] array = sim.getAIContextArray();
    ActionState prevResult = array[0].getCurrentState();
    assertEquals(prevResult, array[0].getCurrentState());
    //timer.start();
    array[0].updateTime(0);
    

    assertFalse(prevResult == array[0].getCurrentState());
    }
  

}
