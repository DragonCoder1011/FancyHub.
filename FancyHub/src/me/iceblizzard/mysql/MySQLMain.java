package me.iceblizzard.mysql;

import me.iceblizzard.file.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLMain {

    private static MySQLMain instance = new MySQLMain();
    private Connection connection;
    public String host, database, username, password;
    public int port;

    public static MySQLMain getInstance() {
        return instance;
    }

    public void connect() {
        host = ConfigManager.getConfigManager().getPath("host");

        port = ConfigManager.getConfigManager().getInt("port");

        database = ConfigManager.getConfigManager().getPath("database");

        username = ConfigManager.getConfigManager().getPath("username");

        password = ConfigManager.getConfigManager().getPath("password");
        try {
            synchronized (this) {

                if (getConnection() != null && !getConnection().isClosed()) {

                    return;

                }
                Class.forName("com.mysql.jdbc.Driver");

                setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":"

                        + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password));


                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
                createPlayerDataTable();
                createParticleTable();
                createGadgetsTable();
                createHatsTable();
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
    }

    public Connection getConnection() {

        return connection;
    }

    public void setConnection(Connection connection) {

        this.connection = connection;

    }

    public void createPlayerDataTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + "PLAYERDATA"
                    + "(UUID VARCHAR(255) NOT NULL, NAME VARCHAR(255) NOT NULL, IP VARCHAR(255), COINS INT(16), PRIMARY KEY (UUID))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createParticleTable() {
        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + "PARTICLEDATA" +
                    "(UUID VARCHAR(255), NONE INT(10), HEART INT(10), MAGIC INT(10), CLOUD INT(10), ENDER INT(10), SPARK INT(10), FLAME INT(10), SNOW INT(10), " +
                    "WATER INT(10), SMOKE INT(10), SLIME INT(10), ENCHANTED INT(10), MUSIC INT(10), EMERALD INT(10), RAINBOW INT(10), " +
                    "PRIMARY KEY(UUID))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createGadgetsTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + "GADGETDATA" +
                    "(UUID VARCHAR(255), NONE INT(10), FUNGUN  INT(10), EXPLOSIVEBOW  INT(10), NECROMANCERWAND  INT(10), FREEZERAY  INT(10), FLYINGPIG  INT(10)," +
                    " RAILGUN  INT(10), FIREBALLSTICK  INT(10), CANNONBLASTER  INT(10), PIGZOOKA  INT(10), MELONLAUNCHER  INT(10)," +
                    " ALMIGHTYENCHANTER  INT(10), CONFETTI  INT(10), LIGHTNINGSTICK  INT(10), THROWABLETNT  INT(10), PRIMARY KEY(UUID))");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createHatsTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + "HATDATA" +
                    "(UUID VARCHAR(255), NONE INT(10), NOTCH INT(10), HEROBRINE INT(10), JEB INT(10), DINNERBONE INT(10), MD_5 INT(10), ENDERDRAGON INT(10), WITHER INT(10)," +
                    " THOR INT(10), IRONMAN INT(10), BATMAN INT(10), PRIMARY KEY(UUID))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}