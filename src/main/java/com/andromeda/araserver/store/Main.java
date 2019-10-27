package com.andromeda.araserver.store;

import com.andromeda.araserver.util.OutputModel;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {
    public String GetStoreContent() {
        Gson gson = new Gson();
        Connection c;
        ArrayList<OutputModel> outputModelArrayList = new ArrayList<OutputModel>();
        try {
            c = getConnection();
            //add(c);
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return gson.toJson(outputModelArrayList);
    }

    private static Connection getConnection() throws SQLException {
        //get a SQL connection (Note these creds are for test DB)
        String dbUrl = "jdbc:postgresql://" + "ec2-54-235-92-244.compute-1.amazonaws.com" + ':' + "5432" + "/d40qc3ivndkhlh" + "?ssl=true" + "&sslfactory=org.postgresql.ssl.NonValidatingFactory";
        return DriverManager.getConnection(dbUrl,
                "nrpnuktgkcsxdh", "2f1dbd4488f547b9b358b059221ee69da1965f9d96b3db36035e896d2c5c6808");
    }

    void add(Connection c) throws SQLException {
        Statement stmt = c.createStatement();
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " INFO            TEXT     NOT NULL, " +
                " LINK          TEXT, " +
                " PRE         TEXT, " +
                " END          TEXT" +
                "  ACTION         TEXT)";
        stmt.executeUpdate(sql);
        stmt.close();

    }
}