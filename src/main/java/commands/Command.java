package commands;

import exceptions.WeaponException;

public interface Command {
public void execute() throws WeaponException;

}
