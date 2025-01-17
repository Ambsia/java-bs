package com.bookingsystem.view.dialogpanels.bookingdialog;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.bookingsystem.helpers.MessageBox;
import com.bookingsystem.view.dialogpanels.UIBookingSystemDialogPanel;

public class UIBookingSystemFindPanel extends UIBookingSystemDialogPanel {

    /**
     *
     */
    private static final long serialVersionUID = -7377627651873968268L;

    public UIBookingSystemFindPanel() {
        super();
        
        addDefaultComponentsToPanel();
        try {
            ((JTextField) getComponentsAsList()[0]).setEditable(true);
        } catch (Exception e) {
            MessageBox.errorMessageBox(e.toString() + "Definitely Broken");
        }
    }

    @Override
    public int showDialog() {
        return JOptionPane.showOptionDialog(null, this, "Find Booking",
                JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null,
                new String[]{"Find", "Cancel"}, "Add");
    }

}