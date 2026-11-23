
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Cursor;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import com.unpri.charactermanager.model.Character;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {

        super("Character Manager v0.4");

        this.characterList = characterList;

        initComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(420, 300));
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 12));
        mainPanel.setBackground(AppTheme.BG_MAIN);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );

        JLabel titleLabel = new JLabel(
                "=== Daftar Karakter (" + characterList.size() + ") ==="
        );

        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_MAIN);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel characterPanel = new JPanel(
                new GridLayout(0, 1, 0, 8)
        );

        characterPanel.setBackground(AppTheme.BG_MAIN);

        for (Character c : characterList) {

            String info = c.getName()
                    + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

            JLabel characterLabel = new JLabel(info);

            // Warna berdasarkan role
            characterLabel.setBackground(
                    AppTheme.colorFor(c.getType())
            );

            characterLabel.setOpaque(true);

            // Font dan warna teks
            characterLabel.setFont(AppTheme.FONT_BODY);
            characterLabel.setForeground(AppTheme.TEXT_LIGHT);

            // Border
            characterLabel.setBorder(AppTheme.CARD_BORDER);

            // Properti tambahan 1: tooltip berisi informasi lengkap
            characterLabel.setToolTipText(
                    AppTheme.describe(c.getType())
                    + " | HP: " + c.getHitPoints()
                    + " | ATK: " + c.getAttackPower()
            );

            // Properti tambahan 2: perataan teks
            characterLabel.setHorizontalAlignment(
                    SwingConstants.LEFT
            );

            // Cursor ketika diarahkan ke kartu
            characterLabel.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
            );

            // Mini challenge: level tinggi diberi warna emas dan bold
            if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {

                characterLabel.setForeground(AppTheme.GOLD);

                characterLabel.setFont(
                        AppTheme.FONT_BODY.deriveFont(Font.BOLD)
                );
            }

            characterPanel.add(characterLabel);
        }

        mainPanel.add(characterPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}
