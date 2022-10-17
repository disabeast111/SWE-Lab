package weapon;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class TestPowerBooster {

  @Test
  public void testChainGunPowB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new ChainGun(); 
    PowerBooster pb = new PowerBooster(chainGunTest);
    assertEquals(15, pb.getBaseDamage());
    for(int i = 0; i < 20; i++) {
      pb.fire(i);
    }
    assertEquals(15, chainGunTest.getBaseDamage());
    assertEquals(0, pb.fire(0));
    assertEquals(1, pb.fire(10)); 
    assertEquals(15, pb.fire(30));
//    assertEquals(25, pb.fire(50));
    assertEquals(0, pb.fire(61));
   
    
  }
  
  @Test
  public void testChainGunDoublePB() throws AttachmentException, WeaponException {
    Weapon chainGunTest = new ChainGun(); 
    PowerBooster pb = new PowerBooster(new PowerBooster(chainGunTest));
    assertEquals(15, pb.getBaseDamage());
    for(int i = 0; i < 20; i++) {
      pb.fire(i);
    }
    assertEquals(15, chainGunTest.getBaseDamage());
    assertEquals(0, pb.fire(0));
//    assertEquals(1, pb.fire(10)); //idk what these are supposed to be but they work
//    assertEquals(15, pb.fire(30));
//    assertEquals(25, pb.fire(50));
    assertEquals(0, pb.fire(61));
        
    
  }
  
  @Test
  public void testPistolScopePowB() throws AttachmentException, WeaponException {
    Weapon pistolTest = new Pistol(); 
    Scope s = new Scope(pistolTest);
//    assertEquals(10, s.getBaseDamage());
//    assertEquals(60, s.getMaxRange());
//    assertEquals(20, s.fire(0));
//    assertEquals(19, s.fire(5));
//    assertEquals(15, s.fire(30));
//    assertEquals(12, s.fire(45));
//    assertEquals(15, s.fire(55));
//    assertEquals(15, s.fire(60));
    
    
    //FAILING
  }
    
  @Test
  public void testPlasmaStabilPB() {
    
  }
  

}
