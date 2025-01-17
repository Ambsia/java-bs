package com.bookingsystem.model.businessmodel;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bookingsystem.helpers.MessageBox;
import com.bookingsystem.model.Account;
import com.bookingsystem.model.Log;

/**
 * Author: [Alex]
 */
public class LoggerBusinessLayer extends BusinessLayer implements Iterable<Log> {

    private final List<Log> logList;
    private Account accountCurrentlyLoggedIn = null;

    public LoggerBusinessLayer() {
        logList = new ArrayList<>();
    }

    public void insertLog(Log log) {
        getDatabaseConnector().openConnection();
        if (getDatabaseConnector().isConnected()) {
            if (getDatabaseConnector().isConnectionClosed()) {
                getDatabaseConnector().createNewCallableStatement(
                        "{CALL spInsertLog(?,?,?,?,?,?,?,?,?)}");
                try (CallableStatement callableStatement = getDatabaseConnector()
                        .getCallableStatement()) {
                    callableStatement.setString(1, log.getEventLogged());
                    callableStatement.setString(2, log.getClassEvent());
                    callableStatement.setTimestamp(3,
                            getTimeStamp(log.getDateAndTimeOfEvent()));
                    callableStatement.setInt(4,
                            accountCurrentlyLoggedIn.getUserID());
                    callableStatement.setInt(5, log.getBookingIDInserted());
                    callableStatement.setInt(6, log.getBookingIDEdited());
                    callableStatement.setInt(7, log.getBookingIDDeleted());
                    callableStatement.setInt(8, log.getAccountIDCreated());
                    callableStatement.setInt(9, log.getAccountIDDeleted());
                    getDatabaseConnector().execute();
                } catch (SQLException e) {
                    MessageBox
                            .errorMessageBox("There was an issue while we were trying to insert a log.\n"
                                    + "Does this make any sense to you.."
                                    + e.toString() + "?");
                }
            }
            getDatabaseConnector().closeConnection();
        }
    }

    private java.sql.Timestamp getTimeStamp(java.util.Date date) {
        return new Timestamp(date.getTime());
    }

    public void exceptionCaused() {

    }

    public void removeLogsForAccount(int currentAccountID) {
        getDatabaseConnector().openConnection();
        if (getDatabaseConnector().isConnected()) {
            //System.out.println("" + getDatabaseConnector().isConnected());
            if (getDatabaseConnector().isConnectionClosed()) {
                getDatabaseConnector().createNewCallableStatement(
                        "{CALL spRemoveLogsForAccount(?)}");
                try (CallableStatement callableStatement = getDatabaseConnector()
                        .getCallableStatement()) {
                    callableStatement.setInt(1, currentAccountID);
                    callableStatement.execute();

                } catch (SQLException e) {
                    MessageBox
                            .errorMessageBox("There was an issue while removing logs.\n"
                                    + "Does this make any sense to you.."
                                    + e.toString() + "?");
                }
            }
            getDatabaseConnector().closeConnection();
        }
    }

    public void getLogsForAccount(int currentAccountID) {
        getDatabaseConnector().openConnection();
        if (getDatabaseConnector().isConnected()) {
            if (getDatabaseConnector().isConnectionClosed()) {
                logList.clear();
                ArrayList<Integer> logIDS = new ArrayList<>();
                ArrayList<Integer> integers = new ArrayList<>();
                if (currentAccountID != -1) {
                    getDatabaseConnector().createNewCallableStatement(
                            "{CALL spGetLogsForAccount(?)}");
                    try (CallableStatement csGetLogsForAccount = getDatabaseConnector()
                            .getCallableStatement()) {
                        csGetLogsForAccount.setInt(1, currentAccountID);
                        try (ResultSet rs = csGetLogsForAccount.executeQuery()) {
                            while (rs.next()) {
                                logList.add(new Log(rs.getString(2), rs
                                        .getString(3), rs.getTimestamp(4)));
                                logIDS.add(rs.getInt(1));
                            }
                        }
                        getDatabaseConnector().createNewCallableStatement(
                                "{CALL spGetIDPlayedWith(?,?)}");
                        try (CallableStatement csGetIDPlayedWith = getDatabaseConnector()
                                .getCallableStatement()) {
                            csGetIDPlayedWith.registerOutParameter(2,
                                    Types.INTEGER);

                            for (Integer i : logIDS) {
                                csGetIDPlayedWith.setInt(1, i);
                                csGetIDPlayedWith.execute();
                                integers.add(csGetIDPlayedWith.getInt(2));
                            }

                            for (int k = 0; k < logList.size(); k++) {
                                logList.get(k).setLogID(logIDS.get(k));
                                logList.get(k).setIdPlayedWith(integers.get(k));
                            }

                        }
                    } catch (SQLException e) {
                        MessageBox
                                .errorMessageBox("There was an issue while retrieving logs for an account.\n"
                                        + "Does this make any sense to you.."
                                        + e.toString() + "?");
                    }
                } else {
                    MessageBox
                            .errorMessageBox("You must select an account to view logs.");
                }
            }
            getDatabaseConnector().closeConnection();
        }
    }

    public void setAccountCurrentlyLoggedIn(Account accountCurrentlyLoggedIn) {
        this.accountCurrentlyLoggedIn = accountCurrentlyLoggedIn;
    }

    @Override
    public Iterator<Log> iterator() {
        return logList.iterator();
    }
}
