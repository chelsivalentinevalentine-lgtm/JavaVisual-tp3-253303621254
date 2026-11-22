
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("   CHARACTER MANAGER - TP5");
        System.out.println("==================================");

        CharacterManager manager = new CharacterManager();

        // 1. Menambahkan karakter
        System.out.println("\n--- MENAMBAHKAN KARAKTER ---");

        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 5));
        manager.addCharacter(new Character("Merlin", CharacterType.MAGE, 3));
        manager.addCharacter(new Character("Athena", CharacterType.ARCHER, 4));

        // 2. Menguji penolakan nama duplikat
        System.out.println("\n--- MENGUJI KARAKTER DUPLIKAT ---");

        manager.addCharacter(new Character("Arjuna", CharacterType.WARRIOR, 8));

        // 3. Menampilkan jumlah karakter
        System.out.println("\nTotal karakter: " + manager.getTotalCharacters());

        // 4. Mencari karakter
        System.out.println("\n--- MENCARI KARAKTER ---");

        Character hasilCari = manager.findByName("Merlin");

        if (hasilCari != null) {
            System.out.println("Karakter ditemukan: " + hasilCari);
        } else {
            System.out.println("Karakter tidak ditemukan.");
        }

        // 5. Menghapus karakter
        System.out.println("\n--- MENGHAPUS KARAKTER ---");

        manager.removeCharacter("Athena");

        // 6. Menampilkan seluruh karakter
        System.out.println("\n--- DAFTAR KARAKTER ---");

        for (Character c : manager.getAllCharacters()) {
            System.out.println(c);
        }

        // 7. Menguji rata-rata level
        System.out.println("\n--- ANALISIS RATA-RATA LEVEL ---");

        System.out.println("Rata-rata level: " + manager.getAverageLevel());

        // 8. Menguji pencarian berdasarkan tipe
        System.out.println("\n--- MENCARI KARAKTER WARRIOR ---");

        ArrayList<Character> warriors = manager.findByType(CharacterType.WARRIOR);

        for (Character c : warriors) {
            System.out.println(c);
        }

        // 9. Fitur analisis tambahan: karakter terkuat
        System.out.println("\n--- KARAKTER DENGAN LEVEL TERTINGGI ---");

        Character strongest = manager.findStrongest();

        if (strongest != null) {
            System.out.println("Karakter terkuat: " + strongest);
        }

        // 10. Eksperimen object reference
        System.out.println("\n--- EKSPERIMEN OBJECT REFERENCE ---");

        Character referensiAsli = manager.findByName("Arjuna");
        Character referensiKedua = referensiAsli;

        referensiKedua.setLevel(99);

        System.out.println("Level melalui referensi kedua: "
                + referensiKedua.getLevel());

        System.out.println("Level melalui referensi asli: "
                + referensiAsli.getLevel());

        System.out.println("Apakah kedua referensi menunjuk objek yang sama? "
                + (referensiAsli == referensiKedua));

        // 11. Eksperimen defensive copy
        System.out.println("\n--- EKSPERIMEN DEFENSIVE COPY ---");

        ArrayList<Character> salinanList = manager.getAllCharacters();

        salinanList.add(new Character("Hantu", CharacterType.HEALER, 1));

        System.out.println("Jumlah karakter pada salinan: "
                + salinanList.size());

        System.out.println("Jumlah karakter pada manager asli: "
                + manager.getTotalCharacters());

        // 12. Menguji manager kosong
        System.out.println("\n--- MENGUJI MANAGER KOSONG ---");

        CharacterManager managerKosong = new CharacterManager();

        System.out.println("Rata-rata level saat kosong: "
                + managerKosong.getAverageLevel());

        System.out.println("\n==================================");
        System.out.println("       PENGUJIAN TP5 SELESAI");
        System.out.println("==================================");
    }
}
