package environment;

import lifeform.LifeForm;

import weapon.Weapon;

import exceptions.EnvironmentException;

/**
 * Labs 4 and 5
 * @author David W
 * Lab 5
 * @author Kyle S
 */
public class Environment {
  Cell[][] cells;
  private static Environment theEnv;

  /**
   * Environment
   * 
   * @param rows number of rows in environment
   * @param cols number of columns in environment
   */
  private Environment(int rows, int cols) {
    cells = new Cell[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        cells[r][c] = new Cell();
      }
    }
  }

  /**
   * getEnvironment
   * 
   * @param rows number of rows
   * @param cols number of columns
   * @return the Singleton instance
   */
  public static Environment getEnvironment(int rows, int cols) {
    if (theEnv == null) {
      theEnv = new Environment(rows, cols);
    }
    return theEnv;
  }

  /**
   * addLifeForm
   * 
   * @param entity the lifeform
   * @param row    what row
   * @param col    what column
   * @return boolean
   */
  public boolean addLifeForm(LifeForm entity, int row, int col) {
    if (0 <= row && row < cells.length) {
      if (0 <= col && col < cells[0].length) {
        if (getLifeForm(row, col) == null) {
          cells[row][col].addLifeForm(entity);
          entity.setLocation(row, col);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * getWeapons
   * 
   * @param r target row
   * @param c target column
   * @return array of the weapons
   */
  public Weapon[] getWeapons(int r, int c) {
    Weapon[] w = { cells[r][c].getWeapon1(), cells[r][c].getWeapon2() };
    return w;
  }

  /**
   * getNumRows
   * 
   * @return the number of rows
   */
  public int getNumRows() {
    return cells.length;
  }

  /**
   * getNumCols
   * 
   * @return the number of columns
   */
  public int getNumCols() {
    return cells[0].length;
  }

  /**
   * clearBoard
   */
  public void clearBoard() {
    for (int r = 0; r < getNumRows(); r++) {
      for (int c = 0; c < getNumCols(); c++) {
        removeLifeForm(r, c);
        cells[r][c].removeWeapon(cells[r][c].getWeapon1());
        cells[r][c].removeWeapon(cells[r][c].getWeapon2());
      }
    }
  }

  /**
   * removeWeapon
   * 
   * @param w target weapon
   * @param r target row
   * @param c target column
   * @return
   */
  public Weapon removeWeapon(Weapon w, int r, int c) {
    try {
      return cells[r][c].removeWeapon(w);

    } catch (Exception e) {
      return null;
    }
  }

  /**
   * getDistance
   * 
   * @param r1 row for first location
   * @param c1 column for first location
   * @param r2 row for second location
   * @param c2 column for second location
   * @return decimal distance in feet
   * @throws EnvironmentException
   */
  public double getDistance(int r1, int c1, int r2, int c2) throws EnvironmentException {
    if (r1 < getNumRows() && r2 < getNumRows() && c1 < getNumCols() && c2 < getNumCols()) {
      if (r1 == -1 || r2 == -1) {
        throw new EnvironmentException("LifeForm(s) not in Environment");
      }
      double a = Math.abs(r1 - r2) * 5;
      double b = Math.abs(c1 - c2) * 5;
      return Math.sqrt(a * a + b * b);
    } else {
      throw new EnvironmentException("Invalid coordinates");
    }
  }

  /**
   * getDistance
   * 
   * @param lf1 first target lifeform
   * @param lf2 second target lifeform
   * @return distance between lifeforms in feet
   * @throws EnvironmentException
   */
  public double getDistance(LifeForm lf1, LifeForm lf2) throws EnvironmentException {
    int r1 = lf1.getRow();
    int c1 = lf1.getCol();
    int r2 = lf2.getRow();
    int c2 = lf2.getCol();

    if (r1 == -1 || r2 == -1) {
      throw new EnvironmentException("LifeForm(s) not in Environment");
    } else {
      return getDistance(r1, c1, r2, c2);
    }
  }

  /**
   * addWeapon
   * 
   * @param w weapon to add
   * @param r target row
   * @param c target column
   * @return status of the operation
   */
  public boolean addWeapon(Weapon w, int r, int c) {
    try {
      if (w != cells[r][c].getWeapon1() && w != cells[r][c].getWeapon2()) {
        return cells[r][c].addWeapon(w);
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * removeLifeForm
   * 
   * @param row target row
   * @param col target column
   */
  public void removeLifeForm(int row, int col) {
    cells[row][col].removeLifeForm();
  }

  /**
   * getLifeForm
   * 
   * @param row row of the lifeform
   * @param col column of the lifeform
   * @return the target lifeform
   */
  public LifeForm getLifeForm(int row, int col) {
    return cells[row][col].getLifeForm();
  }
}
