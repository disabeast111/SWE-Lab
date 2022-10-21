package exceptions;

/**
 * @author David W
 */
public class WeaponException extends Exception {
  private static final long serialVersionUID = 3L;

  public WeaponException(String message) {
    super(message);
  }
}