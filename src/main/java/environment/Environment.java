package environment;

import lifeform.LifeForm;

public class Environment {
  Cell[][] cells;
  
  /**
   * Environment
   * @param rows number of rows in environment
   * @param cols number of columns in environment
   */
  public Environment(int rows, int cols) {
    cells = new Cell[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        cells[r][c] = new Cell();
      }
    }
  }
  
  /**
   * addLifeForm
   * @param entity the lifeform
   * @param row what row
   * @param col what column
   * @return boolean
   */
  public boolean addLifeForm(LifeForm entity, int row, int col) { 
    if (0 <= row && row < cells.length) {
      if (0 <= col && col < cells[0].length) {
        if (getLifeForm(row,col) == null) {
          cells[row][col].addLifeForm(entity);
          return true;
        }
      }
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
