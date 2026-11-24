
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // Membuat CharacterManager
        CharacterManager manager = new CharacterManager();

        // Menambahkan 8 karakter
        manager.addCharacter(
                new Character("Arjuna", CharacterType.WARRIOR, 5)
        );

        manager.addCharacter(
                new Character("Merlin", CharacterType.MAGE, 3)
        );

        manager.addCharacter(
                new Character("Athena", CharacterType.ARCHER, 4)
        );

        manager.addCharacter(
                new Character("Bima", CharacterType.WARRIOR, 7)
        );

        manager.addCharacter(
                new Character("Luna", CharacterType.HEALER, 6)
        );

        manager.addCharacter(
                new Character("Kadita", CharacterType.MAGE, 8)
        );

        manager.addCharacter(
                new Character("Gatotkaca", CharacterType.WARRIOR, 9)
        );

        manager.addCharacter(
                new Character("Morgana", CharacterType.MAGE, 2)
        );

        // Menjalankan GUI melalui Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            MainWindow window = new MainWindow(
                    manager.getAllCharacters()
            );

            window.setVisible(true);
        });
    }
}
