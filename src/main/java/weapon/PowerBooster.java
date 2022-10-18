package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

/**
 * @author Spencer H
 */
public class PowerBooster extends Attachment {

  private double damage = 0;
  private double currAmmo = 0;
  private double maxAmmo = 0;

  /**
   * A PowerBooster increases damage by an amount equal to the current damage
   * times the current ammo divided by the max ammo.
   * 
   * @param baseWeapon The weapon or attachment whose damage is 
   * being affected
   * @throws AttachmentException when trying to add a third attachment.
   */
  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
    maxAmmo = baseWeapon.getMaxAmmo();
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Can not have more than 2 attachments.");
    }
  }

  /**
   * Fires the weapon, dealing the increased amount of damage.
   * 
   * @param distance the distance from the target we are firing at.
   * Because of how a PowerBooster works, distance is irrelevant.
   * @return The amount of damage dealt with the attachment, rounded
   * down to the nearest integer.
   */
  public int fire(int distance) throws WeaponException {
    currAmmo = base.getCurrentAmmo();
    damage = base.fire(distance);

    damage *= 1 + (currAmmo / maxAmmo);

    return Double.valueOf(Math.floor(damage)).intValue();
  }

  /**
   * Adds the name of the Attachment to the string holding the
   * name of the weapon
   * 
   * @return the full string with Weapon and all Attachments listed.
   */
  public String toString() {
    return base.toString() + " +PowerBooster";
  }
}
