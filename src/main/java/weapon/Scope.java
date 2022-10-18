package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Spencer H
 */
public class Scope extends Attachment {

  private double damage = 0;
  private double maxRange = 0;
  private double targetDistance = 0;

  /**
   * A Scope increases the range of a weapon by 10, and also
   * increases the damage by an amount equal to the current damage
   * times the percent difference between the distance and the max range.
   * If the target is between the weapon's base range and the new range,
   * the damage will be the weapon's base damage plus 5.
   * 
   * @param baseWeapon The weapon or attachment whose damage is 
   * being affected 
   * @throws AttachmentException when trying to add a third attachment.
   */
  public Scope(Weapon baseWeapon) throws AttachmentException {
    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
    maxRange = getMaxRange();
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Can not have more than 2 attachments.");
    }
  }
 
  /**
   * Fires the weapon, dealing the increased amount of damage.
   * 
   * @param distance the distance from the target we are firing at.
   * @return The amount of damage dealt with the attachment, rounded
   * down to the nearest integer.
   */
  public int fire(int distance) throws WeaponException {
    if (base.getMaxRange() < distance && distance <= maxRange) {
      return base.fire(base.getMaxRange()) + 5;

    } else if (distance <= base.getMaxRange()) {
      targetDistance = distance;
      damage = base.fire(distance);

      damage *= 1 + ((maxRange - targetDistance) / maxRange);

      return Double.valueOf(Math.floor(damage)).intValue();
    } else {
      base.fire(distance);
      return 0;
    }
  }

  /**
   * Increases the max range of the weapon by 10.
   */
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }

  /**
   * Adds the name of the Attachment to the string holding the
   * name of the weapon
   * 
   * @return the full string with Weapon and all Attachments listed.
   */
  public String toString() {
    return base.toString() + " +Scope";
  }

}
