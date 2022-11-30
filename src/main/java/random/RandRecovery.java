package random;

import java.util.List;

import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;

public class RandRecovery {
  List<RecoveryBehavior> choices = List.of(new RecoveryNone(),
      new RecoveryLinear(new RandInt(5, 10).choose()),
      new RecoveryFractional(new RandInt(2, 5).choose() * 0.1));

  public RecoveryBehavior choose() {
    return new FromList<RecoveryBehavior>(choices).choose();
  }
}
