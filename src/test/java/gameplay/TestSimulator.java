package gameplay;

import static org.junit.Assert.*;

import org.junit.Test;

import environment.Environment;
import exceptions.RecoveryRateException;
import weapon.Weapon;

public class TestSimulator {

  @Test
  public void testSimulatorPopulation() throws RecoveryRateException {
    Environment env = Environment.getEnvironment(5, 5);
    SimpleTimer timer = new SimpleTimer(1000);
    Simulator sim = new Simulator(env, timer, 2, 2);
    int lifeFormNum = 0;
    int weaponNum = 0;
    
    for(int i = 0; i < 5; i++) {
      for(int k = 0; k < 5; k++) {
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
    
  }
  
  @Test
  public void testSimulatorTimer() {
    
  }

}
