package environment;

import lifeform.LifeForm;
import weapon.Weapon;
import java.lang.ArrayIndexOutOfBoundsException;

/**
 * @author David W
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
          return true;
        }
      }
    }
    return false;
  }

  public Weapon[] getWeapon(int r, int c) {
    Weapon[] w = { cells[r][c].getWeapon1(), cells[r][c].getWeapon2() };
    return w;
  }

  public int getRows() {
    return cells.length;
  }

  public int getCols() {
    return cells[0].length;
  }

  public void clearBoard() {
    for (int r = 0; r < getRows(); r++) {
      for (int c = 0; c < getCols(); c++) {
        removeLifeForm(r, c);
        cells[r][c].removeWeapon(cells[r][c].getWeapon1());
        cells[r][c].removeWeapon(cells[r][c].getWeapon2());
      }
    }
  }

  public Weapon removeWeapon(Weapon w, int r, int c) {
    try {
      return cells[r][c].removeWeapon(w);
    } catch (Exception e) {
      return null;
    }
  }
  
  public double getDistance(int r1, int r2, int c1, int c2){
    double a = Math.abs(r1-r2);
    double b = Math.abs(c1-c2);
    return Math.sqrt(a*a + b*b);
  }
  
  public double getDistance(LifeForm lf1, LifeForm lf2) {
    int r1 = 0;
    int c1 = 0;
    int r2 = 0;
    int c2 = 0;
    while (getLifeForm(r1, c1) != lf1) {
      r1++;
      while (getLifeForm(r1, c1) != lf1) {
        c1++;
      }
    }
    while (getLifeForm(r2, c2) != lf1) {
      r2++;
      while (getLifeForm(r2, c2) != lf1) {
        c2++;
      }
    }
    return getDistance(r1, r2, c1, c2);
  }

  public boolean addWeapon(Weapon w, int r, int c) {
    try {
      if (cells[r][c].getWeapon1() == w || cells[r][c].getWeapon2() == w) {
        return cells[r][c].addWeapon(w);
      }
      throw new ArrayIndexOutOfBoundsException();
    } catch(Exception e) {
      return false;
    }
    return false;
  }

  public void removeLifeForm(int row, int col) {
    cells[row][col].removeLifeForm();
  }

  public LifeForm getLifeForm(int row, int col) {
    return cells[row][col].getLifeForm();
  }
}
