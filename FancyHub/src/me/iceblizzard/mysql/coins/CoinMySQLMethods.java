package me.iceblizzard.mysql.coins;
import me.iceblizzard.mysql.MySQLMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinMySQLMethods {

    private static CoinMySQLMethods instance = new CoinMySQLMethods();

    public static CoinMySQLMethods getInstance() {
        return instance;
    }


    public int getCoins(String uuid) {
        try {
            PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM PLAYERDATA WHERE UUID = ?");
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("COINS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void setCoins(UUID uuid, int amount) {
        if (getCoins(uuid.toString()) == -1) {
            try {
                PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                        "INSERT INTO PLAYERDATA (UUID, COINS) VALUES (?,?)");
                ps.setString(1, uuid.toString());
                ps.setInt(2, amount);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement statement = MySQLMain.getInstance().getConnection().prepareStatement(
                        "UPDATE PLAYERDATA SET COINS=? WHERE UUID=?");
                statement.setInt(1, amount);
                statement.setString(2, uuid.toString());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCoins(UUID uuid, int amount){
        int current = getCoins(uuid.toString());
        setCoins(uuid, current + amount);
    }

    public void removeCoins(UUID uuid, int amount){
        int current = getCoins(uuid.toString());
        setCoins(uuid, current - amount);
    }
}
   /* public void getCoins(UUID uuid, Player player){
        try{
        PreparedStatement ps = MySQLMain.getInstance().getConnection().prepareStatement(
                "SELECT * FROM " + ConfigManager.getConfigManager().getPath("tablename") + " WHERE UUID=?");
        ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            player.sendMessage(ChatColor.WHITE + "Player's Coins: " +
                    ChatColor.YELLOW + Integer.valueOf(rs.getInt("COINS")).toString());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCoinBalance(UUID uuid, Player player){
        getCoins(uuid, player);
        return 0;
    }
}
*/