package com.bookingsystem.view.dialogpanels.accountdialog;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Alex on 23/06/2015
 */
public class UIBookingSystemChangePasswordPanel  extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 419911671046158169L;
    private static final String[] LABELS = {"New Password:", "Confirm Password:"};
    private Component[] components;

    private final JPasswordField txtAccountPassword;
    private final JPasswordField txtAccountPasswordConfirmed;
    private final JLabel lblUsername;

    public UIBookingSystemChangePasswordPanel() {
        lblUsername = new JLabel("Username");
        txtAccountPassword = new JPasswordField(5);
        txtAccountPasswordConfirmed = new JPasswordField(5);
        setLayout(new GridBagLayout());
        addControlToPanel(lblUsername,0,0);
        addDefaultComponentsToPanel();
    }

    private void addControlToPanel(Component component, int gridX, int gridY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(component, gbc);
    }


    void addDefaultComponentsToPanel() {
        components = new Component[]{txtAccountPassword, txtAccountPasswordConfirmed};
        for (int i = 0; i < LABELS.length; i++) {
            addControlToPanel(new JLabel(LABELS[i]), 0, i+2);
            addControlToPanel(components[i], 1, i+2);
        }
    }

    public char[] getAccountPasswordText() {
        return txtAccountPassword.getPassword();
    }


    public boolean getPasswordsMoreOrEqualToFourCharacters() {
        return txtAccountPassword.getPassword().length >= 4;
    }
    public boolean getPasswordsTheSame() {
        String pass1String = "";
        String pass2Stirng = "";
        for (char c : txtAccountPassword.getPassword()) {
            pass1String += c;
        }

        for (char c : txtAccountPasswordConfirmed.getPassword()) {
            pass2Stirng += c;
        }
        System.out.println(pass1String == pass2Stirng);
        System.out.println(pass1String + " | " + pass2Stirng);
        return pass1String.equals(pass2Stirng);
    }

    public int showDialog() {
        return JOptionPane.showOptionDialog(null, this, "Change Password",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Change", "Cancel"}, "Change");
    }

    public void clearTextBoxes() {
        this.txtAccountPassword.setText("");
        this.txtAccountPasswordConfirmed.setText("");
    }

    public void setLblUsernameText(String username) {
        this.lblUsername.setText(username);
    }
}