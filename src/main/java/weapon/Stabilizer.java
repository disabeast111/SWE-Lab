package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;

/**
 * @author Spencer H
 */
public class Stabilizer extends Attachment implements TimerObserver {

  private double damage = 0;

  /**
   * A Stabilizer increases damage by 25% and add an auto reloading feature
   * so that the weapon is reloaded as soon as it runs out of ammo.
   * 
   * @param baseWeapon The weapon or attachment whose damage is 
   * being affected
   * @throws AttachmentException when trying to add a third attachment.
   */
  public Stabilizer(Weapon baseWeapon) throws AttachmentException {
    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
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
    double weaponDamage = base.fire(distance);
    damage = weaponDamage * 1.25;

    if (base.getCurrentAmmo() == 0) {
      base.reload();
    }
    return Double.valueOf(Math.floor(damage)).intValue();
  }

  /**
   * Adds the name of the Attachment to the string holding the
   * name of the weapon
   * 
   * @return the full string with Weapon and all Attachments listed.
   */
  public String toString() {
    return base.toString() + " +Stabilizer";
  }

}
