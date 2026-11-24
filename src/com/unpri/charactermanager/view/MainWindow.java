
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.5");

        this.characterList = characterList;

        initComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(420, 300));
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 12));
        mainPanel.setBackground(AppTheme.BG_MAIN);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );

        // NORTH: HEADER DAN STATISTIK
        JPanel topPanel = new JPanel(new BorderLayout(0, 10));
        topPanel.setBackground(AppTheme.BG_MAIN);
        topPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        topPanel.add(createStatisticsPanel(), BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // CENTER: DAFTAR KARAKTER DENGAN SCROLLBAR
        mainPanel.add(createListArea(), BorderLayout.CENTER);

        add(mainPanel);
    }

    // HEADER: JUDUL DAN JUMLAH KARAKTER
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_MAIN);

        JLabel titleLabel = new JLabel("=== Daftar Karakter ===");
        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_MAIN);

        JLabel countLabel = new JLabel(
                "Total: " + characterList.size()
        );
        countLabel.setFont(AppTheme.FONT_MONO);
        countLabel.setForeground(AppTheme.GOLD);

        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(countLabel, BorderLayout.EAST);

        return panel;
    }

    // STATISTIK BERDASARKAN ROLE
    private JPanel createStatisticsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 8, 0));
        panel.setBackground(AppTheme.BG_MAIN);

        for (CharacterType type : CharacterType.values()) {
            int total = 0;

            for (Character c : characterList) {
                if (c.getType() == type) {
                    total++;
                }
            }

            JLabel label = new JLabel(
                    type + ": " + total,
                    SwingConstants.CENTER
            );

            label.setFont(AppTheme.FONT_MONO);
            label.setForeground(AppTheme.TEXT_LIGHT);
            label.setBackground(AppTheme.colorFor(type));
            label.setOpaque(true);
            label.setBorder(AppTheme.CARD_BORDER);

            panel.add(label);
        }

        return panel;
    }

    // CENTER: KARTU KARAKTER DAN SCROLLBAR
    private JPanel createListArea() {
        JPanel cards = new JPanel(new GridLayout(0, 1, 0, 8));
        cards.setBackground(AppTheme.BG_MAIN);

        for (Character c : characterList) {
            String info = c.getName()
                    + " | " + c.getType()
                    + " | Lv." + c.getLevel()
                    + " | HP:" + c.getHitPoints()
                    + " | ATK:" + c.getAttackPower();

            JLabel characterLabel = new JLabel(info);

            characterLabel.setBackground(
                    AppTheme.colorFor(c.getType())
            );
            characterLabel.setOpaque(true);
            characterLabel.setFont(AppTheme.FONT_BODY);
            characterLabel.setForeground(AppTheme.TEXT_LIGHT);
            characterLabel.setBorder(AppTheme.CARD_BORDER);

            characterLabel.setToolTipText(
                    AppTheme.describe(c.getType())
                    + " | HP: " + c.getHitPoints()
                    + " | ATK: " + c.getAttackPower()
            );

            characterLabel.setHorizontalAlignment(
                    SwingConstants.LEFT
            );

            characterLabel.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
            );

            if (c.getLevel() >= AppTheme.HIGH_LEVEL_THRESHOLD) {
                characterLabel.setForeground(AppTheme.GOLD);
                characterLabel.setFont(
                        AppTheme.FONT_BODY.deriveFont(Font.BOLD)
                );
            }

            cards.add(characterLabel);
        }

        // Wrapper agar kartu tidak meregang memenuhi tinggi scroll
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.add(cards, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(460, 280));
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_MAIN);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
}
