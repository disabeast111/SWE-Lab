package Recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import recovery.*;

public class TestRecoveryFractional {
  @Test
  public void noRecoveryWhenNotHurtF() {
    RecoveryFractional r2 = new RecoveryFractional(.3);
    int maxLifePts = 30;
    int result = r2.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }
  
  @Test
  public void testNoMoreMaxF() {
    RecoveryFractional r2 = new RecoveryFractional(.5);
    int currentPts = 20;
    int maxLifePts = 30;
    assertEquals(30, r2.calculateRecovery(currentPts, maxLifePts));
  }
  
  @Test
  public void testRecoverToFullF() {
    RecoveryFractional r2 = new RecoveryFractional(.4);
    int currentPts = 15;
    int maxLifePts = 30;
    currentPts = r2.calculateRecovery(currentPts, maxLifePts);
    assertEquals(30, r2.calculateRecovery(currentPts, maxLifePts));
  }
  
  @Test
  public void testNotFromDeadF() {
    RecoveryFractional r2 = new RecoveryFractional(.5);
    int currentPts = 0;
    int maxLifePts = 30;
    assertEquals(0, r2.calculateRecovery(currentPts, maxLifePts));
  }
}
