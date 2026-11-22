
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    // Menambahkan karakter dan menolak nama duplikat
    public boolean addCharacter(Character character) {
        if (character == null) {
            System.out.println("Gagal: karakter tidak boleh null!");
            return false;
        }

        if (findByName(character.getName()) != null) {
            System.out.println("Gagal: karakter dengan nama '"
                    + character.getName() + "' sudah ada!");
            return false;
        }

        characterList.add(character);
        System.out.println("Karakter '" + character.getName()
                + "' berhasil ditambahkan.");
        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {
        for (Character c : characterList) {
            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    // Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {
        Character target = findByName(name);

        if (target == null) {
            System.out.println("Gagal hapus: karakter '"
                    + name + "' tidak ditemukan.");
            return false;
        }

        characterList.remove(target);
        System.out.println("Karakter '" + name
                + "' berhasil dihapus.");
        return true;
    }

    // Menghitung jumlah karakter
    public int getTotalCharacters() {
        return characterList.size();
    }

    // Mengembalikan salinan list agar data asli terlindungi
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    // Menghitung rata-rata level karakter
    public double getAverageLevel() {
        if (characterList.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character c : characterList) {
            totalLevel += c.getLevel();
        }

        return (double) totalLevel / characterList.size();
    }

    // Mencari semua karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        for (Character c : characterList) {
            if (c.getType() == type) {
                result.add(c);
            }
        }

        return result;
    }

    /**
     * Mencari karakter dengan level tertinggi.
     * Mengembalikan null jika daftar karakter kosong.
     */
    public Character findStrongest() {
        if (characterList.isEmpty()) {
            return null;
        }

        Character strongest = characterList.get(0);

        for (Character c : characterList) {
            if (c.getLevel() > strongest.getLevel()) {
                strongest = c;
            }
        }

        return strongest;
    }
}
