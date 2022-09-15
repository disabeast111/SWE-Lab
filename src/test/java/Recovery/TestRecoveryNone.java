package Recovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import recovery.*;

public class TestRecoveryNone {
  @Test
  public void testRNatMax() {
    RecoveryNone rl = new RecoveryNone();
    int maxLifePts = 30;
    int result = rl.calculateRecovery(maxLifePts, maxLifePts);
    assertEquals(maxLifePts, result);
  }
  
  @Test
  public void testRNBelowMax() {
    RecoveryNone rl = new RecoveryNone();
    int currentPts = 20;
    int maxLifePts = 30;
    int result = rl.calculateRecovery(currentPts, maxLifePts);
    assertEquals(currentPts, result);
  }
}
