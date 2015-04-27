package com.bookingsystem.view.panelparts;

import com.bookingsystem.view.dialogpanels.bookingdialog.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Author: [Alex] on [$Date]
 */
public class UIBookingSystemControlPanel extends JPanel {

    private ArrayList<JButton> controlButtonList;
    private UIBookingSystemAddPanel uiBookingSystemAddPanel;
    private UIBookingSystemFindPanel uiBookingSystemFindPanel;
    private UIBookingSystemEditPanel uiBookingSystemEditPanel;
    private UIBookingSystemRemovePanel uiBookingSystemRemovePanel;
    private UIBookingSystemShowBookingsFound uiBookingSystemShowBookingsFound;

    public UIBookingSystemControlPanel() {
        setLayout(new GridBagLayout());
        String[] buttonNames = {"Load","Search","Filter","Add", "Edit", "Remove", "Repeat", "", ""};
        controlButtonList = new ArrayList<>();
        uiBookingSystemAddPanel = new UIBookingSystemAddPanel();
        uiBookingSystemFindPanel = new UIBookingSystemFindPanel();
        uiBookingSystemEditPanel = new UIBookingSystemEditPanel();
        uiBookingSystemRemovePanel = new UIBookingSystemRemovePanel();
        uiBookingSystemShowBookingsFound = new UIBookingSystemShowBookingsFound();
        Dimension buttonDimension = new Dimension(83,25);
        for ( int buttonNo = 0, colsPassed = 0, rowsPassed = 0; buttonNo<buttonNames.length;buttonNo++) {
        	JPanel jPanel = new JPanel();
        	JButton jButton = new JButton(buttonNames[buttonNo]);
        	jButton.setPreferredSize(buttonDimension);

                if (colsPassed == 3) { rowsPassed++; colsPassed = 0; }

        	addControlToPanel(jPanel, colsPassed++, rowsPassed);
        	controlButtonList.add(jButton);
            jPanel.add(controlButtonList.get(buttonNo));
        }
    }

    public void addControlToPanel(Component component, int gridX, int gridY) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(component, gbc);
    }
    public void addListeners(ActionListener al) {
        for (JButton button : controlButtonList) {
            button.addActionListener(al);
        }
    }
    public UIBookingSystemEditPanel getUIBookingSystemEditPanel() {
        return uiBookingSystemEditPanel;
    }

    public UIBookingSystemAddPanel getUIBookingSystemAddPanel() {
        return uiBookingSystemAddPanel;
    }

    public UIBookingSystemFindPanel getUIBookingSystemFindPanel() {
        return uiBookingSystemFindPanel;
    }

    public UIBookingSystemRemovePanel getUIBookingSystemRemovePanel() {
        return uiBookingSystemRemovePanel;
    }

    public UIBookingSystemShowBookingsFound getUIBookingSystemShowBookingsFound() { return uiBookingSystemShowBookingsFound; }
}
