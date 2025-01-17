package com.bookingsystem.view.panelparts.controlpanes;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;

import com.bookingsystem.view.dialogpanels.bookingdialog.UIBookingSystemAddPanel;
import com.bookingsystem.view.dialogpanels.bookingdialog.UIBookingSystemEditPanel;
import com.bookingsystem.view.dialogpanels.bookingdialog.UIBookingSystemFindPanel;
import com.bookingsystem.view.dialogpanels.bookingdialog.UIBookingSystemRemovePanel;

/**
 * Author: [Alex] on [$Date]
 */
public class UIBookingSystemBookingControlPanel extends
        UIBookingSystemControlPanel {

    /**
     *
     */
    private static final long serialVersionUID = 5909181346406388926L;
    private final UIBookingSystemAddPanel uiBookingSystemAddPanel;
    private final UIBookingSystemFindPanel uiBookingSystemFindPanel;
    private final UIBookingSystemEditPanel uiBookingSystemEditPanel;
    private final UIBookingSystemRemovePanel uiBookingSystemRemovePanel;

    public UIBookingSystemBookingControlPanel() {
        super();
        setLayout(new GridBagLayout());
        setButtonNames(new String[]{"Refresh", "Search", "Complete", "Add",
                "Edit", "Remove", "Today's", "Tomorrow's"});
        setButtonDimension(new Dimension(105, 25));
        createControlPanel();
        uiBookingSystemAddPanel = new UIBookingSystemAddPanel();
        uiBookingSystemFindPanel = new UIBookingSystemFindPanel();
        uiBookingSystemEditPanel = new UIBookingSystemEditPanel();
        uiBookingSystemRemovePanel = new UIBookingSystemRemovePanel();
    }

    public void restrictControls() {
        ArrayList<JButton> jButtonArrayList = getControlButtonList();
        for (JButton jButton : jButtonArrayList) {
            if (jButton.getText() == "Add" || jButton.getText() == "Edit"
                    || jButton.getText() == "Remove"
                    || jButton.getText() == "Complete") {
                jButton.setText("No Access");
            }
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

}
