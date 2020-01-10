package me.iceblizzard.mysql.gadgets;

import me.iceblizzard.mysql.MySQLMain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class GadgetsMySQL {

    private static GadgetsMySQL instance = new GadgetsMySQL();

    public static GadgetsMySQL getInstance() {
        return instance;
    }

    public int getGadgetID(String uuid, String id) {
        try {
            PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM GADGETDATA WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void setGadgetID(UUID uuid, String id, int amount) {
        if (getGadgetID(uuid.toString(),id) == -1) {
            try {
                PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                        "INSERT INTO GADGETDATA (UUID, " + id + ") VALUES (?,?)");
                ps.setString(1, uuid.toString());
                ps.setInt(2, amount);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement statement = MySQLMain.getInstance().getConnection().prepareStatement(
                        "UPDATE GADGETDATA SET " + id + "=? WHERE UUID=?");
                statement.setInt(1, amount);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
