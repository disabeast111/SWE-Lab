package Recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import recovery.*;

public class TestRecoveryLinear {
  @Test
  public void noRecoveryWhenNotHurtL() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 30;
    int result = rl.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }
  
  @Test
  public void testNoMoreMaxL() {
    RecoveryLinear rl = new RecoveryLinear(15);
    int currentPts = 20;
    int maxLifePts = 30;
    assertEquals(30, rl.calculateRecovery(currentPts, maxLifePts));
  }
  
  @Test
  public void testRecoverToFullL() {
    RecoveryLinear rl = new RecoveryLinear(12);
    int currentPts = 10;
    int maxLifePts = 30;
    currentPts = rl.calculateRecovery(currentPts, maxLifePts);
    assertEquals(30, rl.calculateRecovery(currentPts, maxLifePts));
  }
  
  @Test
  public void testNotFromDeadL() {
    RecoveryLinear rl = new RecoveryLinear(10);
    int currentPts = 0;
    int maxLifePts = 30;
    assertEquals(0, rl.calculateRecovery(currentPts, maxLifePts));
  }
}
