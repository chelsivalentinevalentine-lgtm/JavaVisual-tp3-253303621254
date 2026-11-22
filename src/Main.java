
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Character Manager v0.1 ===");

        Character arjuna = new Character("Arjuna", CharacterType.WARRIOR, 5);
        Character merlin = new Character("Merlin", CharacterType.MAGE, 3);
        Character athena = new Character("Athena", CharacterType.ARCHER, 4);

        System.out.println(arjuna);
        System.out.println(merlin);
        System.out.println(athena);

        System.out.println("\n>> Arjuna level up!");
        arjuna.setLevel(6);
        System.out.println("After level up: " + arjuna);

        System.out.println("\n>> Nama karakter: " + arjuna.getName());
        System.out.println(">> Jenis karakter: " + arjuna.getType());
        System.out.println(">> HP saat ini: " + arjuna.getHitPoints());

        arjuna.setMana(100);
        System.out.println("\n>> Mana Arjuna: " + arjuna.getMana());

        try {
            arjuna.setMana(-10);
        } catch (IllegalArgumentException e) {
            System.out.println(">> Validasi mana: " + e.getMessage());
        }

        try {
            arjuna.setLevel(0);
        } catch (IllegalArgumentException e) {
            System.out.println(">> Validasi level: " + e.getMessage());
        }

        System.out.println("\n>> Apakah Arjuna masih hidup? " + arjuna.isAlive());

        // Membuat karakter dengan HP 0
        Character zombie = new Character(
                0, "Zombie", CharacterType.WARRIOR, 1, 0, 15
        );

        System.out.println(">> Apakah Zombie masih hidup? " + zombie.isAlive());
    }
}
