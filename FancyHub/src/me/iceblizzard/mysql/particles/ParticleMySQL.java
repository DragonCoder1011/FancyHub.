package me.iceblizzard.mysql.particles;
import me.iceblizzard.mysql.MySQLMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ParticleMySQL {

    private static ParticleMySQL instance = new ParticleMySQL();

    public static ParticleMySQL getInstance() {
        return instance;
    }

    public int getParticleID(String uuid, String id) {
        try {
            PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM PARTICLEDATA WHERE UUID = ?");
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

    public void setParticleID(UUID uuid, String id, int amount) {
        if (getParticleID(uuid.toString(),id) == -1) {
            try {
                PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                        "INSERT INTO PARTICLEDATA (UUID, " + id + ") VALUES (?,?)");
                ps.setString(1, uuid.toString());
                ps.setInt(2, amount);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement statement = MySQLMain.getInstance().getConnection().prepareStatement(
                        "UPDATE PARTICLEDATA SET " + id + "=? WHERE UUID=?");
                statement.setInt(1, amount);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


