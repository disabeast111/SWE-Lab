package random;

import java.util.List;

import exceptions.RecoveryRateException;
import lifeform.Alien;

public class RandAlien implements Random<Alien> {
  List<String> nameChoices = List.of("Zurg", "ET", "Yoda", "Grogu", "Xeno", "Groot", "Chewie",
      "Predator", "Spock", "Borg");

  public Alien choose() throws RecoveryRateException {
    return new Alien(new FromList<>(nameChoices).choose(), new RandInt(30, 51).choose(),
        new RandRecovery().choose());
  }
}
