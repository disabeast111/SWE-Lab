package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Stabilizer extends Attachment {

  private double damage = 0;

  public Stabilizer(Weapon baseWeapon) throws AttachmentException {

    // auto reloads if ammo is at 0 after firing. Also increases damage by 25%
    base = baseWeapon;
    damage = baseWeapon.getBaseDamage();
    if (base.getNumAttachments() >= 2) {
      throw new AttachmentException("Can not have more than 2 attachments.");
    }
  }

  public int fire(int distance) throws WeaponException {
    double weaponDamage = base.fire(distance);
    damage = weaponDamage * 1.25;

    if (base.getCurrentAmmo() == 0) {
      base.reload();
    }
    return Double.valueOf(Math.floor(damage)).intValue();
  }

  public String toString() {
    return base.toString() + " +Stabilizer";
  }

}
