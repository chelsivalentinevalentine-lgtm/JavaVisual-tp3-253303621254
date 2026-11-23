
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        // 1. Membuat CharacterManager
        CharacterManager manager = new CharacterManager();

        // 2. Menambahkan 5 karakter
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

        // 3. Mengambil rata-rata level dari Model
        double averageLevel = manager.getAverageLevel();

        // 4. Menjalankan GUI melalui Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            MainWindow window = new MainWindow(
                    manager.getAllCharacters(),
                    averageLevel
            );

            window.setVisible(true);
        });
    }
}
