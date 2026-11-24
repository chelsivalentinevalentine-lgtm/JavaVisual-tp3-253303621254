
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;
    private final Runnable onAdded;

    private JTextField nameField;
    private JComboBox<CharacterType> roleBox;
    private JSpinner levelSpinner;
    private JLabel messageLabel;

    private Border originalNameBorder;

    public CharacterFormPanel(
            CharacterManager manager,
            Runnable onAdded
    ) {
        this.manager = manager;
        this.onAdded = onAdded;

        setLayout(new BorderLayout(8, 12));
        setBackground(AppTheme.BG_MAIN);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel title = new JLabel("TAMBAH KARAKTER");
        title.setFont(AppTheme.FONT_BODY);
        title.setForeground(AppTheme.GOLD);

        add(title, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        fieldsPanel.setBackground(AppTheme.BG_MAIN);

        JLabel nameLabel = createLabel("Nama Karakter");
        nameField = new JTextField();
        nameField.setFont(AppTheme.FONT_BODY);
        originalNameBorder = nameField.getBorder();

        JLabel roleLabel = createLabel("Role");
        roleBox = new JComboBox<>(CharacterType.values());

        JLabel levelLabel = createLabel("Level (1-99)");
        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        messageLabel = new JLabel(" ");
        messageLabel.setFont(AppTheme.FONT_BODY);
        messageLabel.setForeground(AppTheme.TEXT_LIGHT);

        JButton saveButton = new JButton("Simpan Karakter");
        saveButton.addActionListener(e -> onSave());

        nameField.addActionListener(e -> onSave());

        fieldsPanel.add(nameLabel);
        fieldsPanel.add(nameField);
        fieldsPanel.add(roleLabel);
        fieldsPanel.add(roleBox);
        fieldsPanel.add(levelLabel);
        fieldsPanel.add(levelSpinner);
        fieldsPanel.add(saveButton);
        fieldsPanel.add(messageLabel);

        add(fieldsPanel, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT_MAIN);
        return label;
    }

    // Memeriksa nama tanpa mengubah komponen Swing
    private String validateName(String name) {

        if (name.isEmpty()) {
            return "Nama wajib diisi";
        }

        if (name.length() < 3 || name.length() > 20) {
            return "Nama harus 3-20 karakter";
        }

        if (!name.matches("[A-Za-z ]+")) {
            return "Nama hanya boleh huruf dan spasi";
        }

        if (name.contains("  ")) {
            return "Nama tidak boleh memiliki spasi ganda";
        }

        if (manager.isNameTaken(name)) {
            return "Nama sudah dipakai";
        }

        return null;
    }

    private void onSave() {

        clearMessage();

        String name = nameField.getText().trim();

        String error = validateName(name);

        if (error != null) {
            showError(error);
            return;
        }

        try {
            levelSpinner.commitEdit();
        } catch (ParseException ex) {
            showError("Level harus berupa angka yang valid");
            return;
        }

        CharacterType role =
                (CharacterType) roleBox.getSelectedItem();

        Integer level =
                (Integer) levelSpinner.getValue();

        try {
            Character character =
                    new Character(name, role, level);

            if (!manager.addCharacter(character)) {
                showError("Karakter gagal ditambahkan");
                return;
            }

            nameField.setText("");
            roleBox.setSelectedIndex(0);
            levelSpinner.setValue(1);

            showSuccess("Karakter berhasil ditambahkan!");

            onAdded.run();

        } catch (IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void showError(String message) {
        nameField.setBorder(
                BorderFactory.createLineBorder(AppTheme.ERROR, 2)
        );

        messageLabel.setForeground(AppTheme.ERROR);
        messageLabel.setText(message);

        nameField.requestFocusInWindow();
    }

    private void showSuccess(String message) {
        nameField.setBorder(originalNameBorder);

        messageLabel.setForeground(AppTheme.SUCCESS);
        messageLabel.setText(message);
    }

    private void clearMessage() {
        nameField.setBorder(originalNameBorder);
        messageLabel.setForeground(AppTheme.TEXT_LIGHT);
        messageLabel.setText(" ");
    }
}
