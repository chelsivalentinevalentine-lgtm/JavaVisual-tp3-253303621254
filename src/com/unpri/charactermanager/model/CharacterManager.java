
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private ArrayList<Character> characterList;

    public CharacterManager() {
        this.characterList = new ArrayList<>();
    }

    // Mengecek apakah nama sudah digunakan
    public boolean isNameTaken(String name) {
        if (name == null) {
            return false;
        }

        for (Character c : characterList) {
            if (c.getName().equalsIgnoreCase(name.trim())) {
                return true;
            }
        }

        return false;
    }

    // Menambahkan karakter
    public boolean addCharacter(Character character) {
        if (character == null) {
            System.out.println("Gagal: karakter tidak boleh null!");
            return false;
        }

        if (isNameTaken(character.getName())) {
            System.out.println("Gagal: karakter dengan nama '"
                    + character.getName() + "' sudah ada!");
            return false;
        }

        // Aturan Model: maksimal 20 karakter
        if (characterList.size() >= 20) {
            System.out.println(
                    "Gagal: jumlah karakter maksimal 20!"
            );
            return false;
        }

        characterList.add(character);

        System.out.println("Karakter '" + character.getName()
                + "' berhasil ditambahkan.");

        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {
        if (name == null) {
            return null;
        }

        for (Character c : characterList) {
            if (c.getName().equalsIgnoreCase(name.trim())) {
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

    // Mengembalikan salinan daftar karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characterList);
    }

    // Menghitung rata-rata level
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

    // Mencari karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        for (Character c : characterList) {
            if (c.getType() == type) {
                result.add(c);
            }
        }

        return result;
    }

    // Mencari karakter dengan level tertinggi
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
