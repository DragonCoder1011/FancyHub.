package me.iceblizzard.mysql.hats;

import me.iceblizzard.mysql.MySQLMain;
import me.iceblizzard.mysql.gadgets.GadgetsMySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HatsMySQL {

    private static HatsMySQL instance = new HatsMySQL();

    public static HatsMySQL getInstance() {
        return instance;
    }

    public int getHatID(String uuid, String id) {
        try {
            PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM HATDATA WHERE UUID = ?");
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

    public void setHatID(UUID uuid, String id, int amount) {
        if (getHatID(uuid.toString(),id) == -1) {
            try {
                PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                        "INSERT INTO HATDATA (UUID, " + id + ") VALUES (?,?)");
                ps.setString(1, uuid.toString());
                ps.setInt(2, amount);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement statement = MySQLMain.getInstance().getConnection().prepareStatement(
                        "UPDATE HATDATA SET " + id + "=? WHERE UUID=?");
                statement.setInt(1, amount);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
