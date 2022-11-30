package random;

import java.util.List;

import lifeform.Human;

public class RandHuman {
  List<String> nameChoices = List.of("Bob", "Jim", "Tim", "Emma", "Anna", "Katy", "Juan", "Zach",
      "Bella", "Rob", "Chris", "Mike", "Shelly", "Lena", "Bartholomew the IV");

  /**
   * generates a human with random
   * assets, health (10-50), armor (0-9)
   * @return Human
   */
  public Human choose() {
    return new Human(new FromList<>(nameChoices).choose(), new RandInt(10, 51).choose(),
        new RandInt(0, 10).choose());
  }
}
