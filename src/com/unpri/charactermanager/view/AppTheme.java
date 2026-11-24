
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public final class AppTheme {

    // WARNA UTAMA
    public static final Color BG_MAIN = Color.decode("#13131f");
    public static final Color TEXT_MAIN = Color.decode("#dfe6e9");
    public static final Color TEXT_LIGHT = Color.WHITE;
    public static final Color GOLD = Color.decode("#fdcb6e");

    // WARNA STATUS BAR
    public static final Color BG_STATUS = Color.decode("#0d0d1a");
    public static final Color TEXT_MUTED = Color.decode("#9d9dbf");

    // WARNA BERDASARKAN ROLE
    public static final Color COLOR_WARRIOR = Color.decode("#d63031");
    public static final Color COLOR_MAGE = Color.decode("#0984e3");
    public static final Color COLOR_ARCHER = Color.decode("#00b894");
    public static final Color COLOR_HEALER = Color.decode("#8e44ad");

    // FONT
    public static final Font FONT_TITLE =
            new Font("SansSerif", Font.BOLD, 22);

    public static final Font FONT_BODY =
            new Font("SansSerif", Font.PLAIN, 14);

    public static final Font FONT_MONO =
            new Font("Monospaced", Font.PLAIN, 13);

    // BORDER
    public static final Border CARD_BORDER =
            BorderFactory.createEmptyBorder(10, 12, 10, 12);

    public static final int HIGH_LEVEL_THRESHOLD = 5;

    private AppTheme() {
    }

    // Mengatur warna berdasarkan jenis karakter
    public static Color colorFor(CharacterType type) {
        switch (type) {
            case WARRIOR:
                return COLOR_WARRIOR;

            case MAGE:
                return COLOR_MAGE;

            case ARCHER:
                return COLOR_ARCHER;

            case HEALER:
                return COLOR_HEALER;

            default:
                return Color.GRAY;
        }
    }

    // Menjelaskan karakter berdasarkan role
    public static String describe(CharacterType type) {
        switch (type) {
            case WARRIOR:
                return "Warrior: petarung jarak dekat";

            case MAGE:
                return "Mage: pengguna kekuatan sihir";

            case ARCHER:
                return "Archer: penyerang jarak jauh";

            case HEALER:
                return "Healer: pendukung dan penyembuh";

            default:
                return "Role belum terdaftar";
        }
    }
}
