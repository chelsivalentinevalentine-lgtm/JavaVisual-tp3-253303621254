
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private JPanel cardsPanel;
    private JPanel statisticsPanel;

    private JLabel countLabel;
    private JLabel statusLabel;

    public MainWindow(CharacterManager manager) {
        super("Character Manager v0.6");

        this.manager = manager;

        initComponents();
        refreshList();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 550));
        setSize(1100, 600);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(AppTheme.BG_MAIN);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );

        // NORTH: HEADER DAN STATISTIK
        JPanel topPanel = new JPanel(new BorderLayout(0, 10));
        topPanel.setBackground(AppTheme.BG_MAIN);

        topPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        statisticsPanel = createStatisticsPanel();
        topPanel.add(statisticsPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // WEST: FORM INPUT
        CharacterFormPanel formPanel =
                new CharacterFormPanel(manager, this::refreshList);

        formPanel.setPreferredSize(new Dimension(220, 350));

        mainPanel.add(formPanel, BorderLayout.WEST);

        // CENTER: DAFTAR KARAKTER
        mainPanel.add(createListArea(), BorderLayout.CENTER);

        // EAST: LEGENDA ROLE
        mainPanel.add(createLegendPanel(), BorderLayout.EAST);

        // SOUTH: STATUS BAR
        mainPanel.add(createStatusPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    // HEADER
    private JPanel createHeaderPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_MAIN);

        JLabel titleLabel =
                new JLabel("=== Daftar Karakter ===");

        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_MAIN);

        countLabel = new JLabel("Total: 0");
        countLabel.setFont(AppTheme.FONT_MONO);
        countLabel.setForeground(AppTheme.GOLD);

        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(countLabel, BorderLayout.EAST);

        return panel;
    }

    // STATISTIK ROLE
    private JPanel createStatisticsPanel() {

        JPanel panel = new JPanel(new GridLayout(1, 4, 8, 0));
        panel.setBackground(AppTheme.BG_MAIN);

        updateStatistics(panel);

        return panel;
    }

    private void updateStatistics(JPanel panel) {

        panel.removeAll();

        for (CharacterType type : CharacterType.values()) {

            int total = 0;

            for (Character c : manager.getAllCharacters()) {
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

        panel.revalidate();
        panel.repaint();
    }

    // AREA DAFTAR KARAKTER
    private JPanel createListArea() {

        cardsPanel = new JPanel(new GridLayout(0, 1, 0, 8));
        cardsPanel.setBackground(AppTheme.BG_MAIN);

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(AppTheme.BG_MAIN);
        holder.add(cardsPanel, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(AppTheme.BG_MAIN);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_MAIN);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // MEMPERBARUI DAFTAR KARAKTER
    public void refreshList() {

        cardsPanel.removeAll();

        for (Character c : manager.getAllCharacters()) {

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

            cardsPanel.add(characterLabel);
        }

        countLabel.setText(
                "Total: " + manager.getTotalCharacters()
        );

        updateStatistics(statisticsPanel);

        statusLabel.setText(
                "Total: " + manager.getTotalCharacters()
                + " karakter | Data diperbarui"
        );

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // LEGENDA ROLE
    private JPanel createLegendPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(AppTheme.BG_MAIN);

        panel.setBorder(
                BorderFactory.createEmptyBorder(0, 8, 0, 0)
        );

        JLabel title = new JLabel("ROLE LEGEND");
        title.setFont(AppTheme.FONT_BODY);
        title.setForeground(AppTheme.GOLD);

        panel.add(title);
        panel.add(Box.createVerticalStrut(12));

        for (CharacterType type : CharacterType.values()) {

            JLabel chip = new JLabel("  " + type + "  ");

            chip.setFont(AppTheme.FONT_BODY);
            chip.setForeground(AppTheme.TEXT_LIGHT);
            chip.setBackground(AppTheme.colorFor(type));
            chip.setOpaque(true);
            chip.setBorder(AppTheme.CARD_BORDER);
            chip.setToolTipText(AppTheme.describe(type));

            panel.add(chip);
            panel.add(Box.createVerticalStrut(8));
        }

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    // STATUS BAR
    private JPanel createStatusPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBackground(AppTheme.BG_STATUS);

        panel.setBorder(
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        );

        statusLabel = new JLabel(
                "Total: 0 karakter | Siap"
        );

        statusLabel.setFont(AppTheme.FONT_MONO);
        statusLabel.setForeground(AppTheme.TEXT_LIGHT);

        JLabel versionLabel = new JLabel("v0.6");
        versionLabel.setFont(AppTheme.FONT_MONO);
        versionLabel.setForeground(AppTheme.TEXT_MUTED);

        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(versionLabel, BorderLayout.EAST);

        return panel;
    }
}
