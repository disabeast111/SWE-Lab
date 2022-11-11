package commands;

import lifeform.LifeForm;
import weapon.GenericWeapon;

public class AttackCommand implements Command {
  LifeForm attacker;
  GenericWeapon heldWeapon;
  
  public AttackCommand(LifeForm a, GenericWeapon w) {
    this.attacker = a;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    attacker.attack(attacker, 0);
    
  }

}
