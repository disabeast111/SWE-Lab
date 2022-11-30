package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import state.AIContext;
import weapon.Weapon;

public class TestSimulator {
  Environment env = Environment.getEnvironment(2, 2);
  SimpleTimer timer = new SimpleTimer(1000);
  
  //Simulator sim = Simulator.getSimulator(env, timer, 2, 2);

  @Test
  public void testSimulatorPopulation() throws RecoveryRateException {
    Simulator sim = new Simulator(env, timer, 1, 2);
    int lifeFormNum = 0;
    int weaponNum = 0;
    
    for(int i = 0; i < 2; i++) {
      for(int k = 0; k < 2; k++) {
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
    
    assertEquals(3, lifeFormNum);
    assertEquals(3, weaponNum);
    env.clearBoard();
    
  }
  
  @Test
  public void testAIContextArray() throws RecoveryRateException {
    

    Simulator sim = new Simulator(env, timer, 2, 2);
    AIContext[] array = sim.getAIContextArray();
    for(int i = 0; i < 4; i++) {
      assertNotNull(array[i]);
    }
    env.clearBoard();
  }
  
  @Test
  public void testSimulatorTimer() {
    
  }

}
