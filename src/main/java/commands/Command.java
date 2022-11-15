package commands;

import exceptions.WeaponException;

/**
 * @author Ethan J
 */
public interface Command {
  /**
   * Execute method for all commands
   * 
   * @throws WeaponException
   */
  public void execute() throws WeaponException;

}
