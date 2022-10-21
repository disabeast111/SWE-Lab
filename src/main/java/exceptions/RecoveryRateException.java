package exceptions;

/**
 * @author David W
 */
public class RecoveryRateException extends Exception {
  private static final long serialVersionUID = 2L;

  public RecoveryRateException(String message) {
    super(message);
  }
}