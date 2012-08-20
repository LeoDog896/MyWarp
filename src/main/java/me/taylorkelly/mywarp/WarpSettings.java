package me.taylorkelly.mywarp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.taylorkelly.mywarp.data.WarpLimit;
import me.taylorkelly.mywarp.utils.PropertiesFile;
import me.taylorkelly.mywarp.utils.WarpLimitComparator;
import me.taylorkelly.mywarp.utils.WarpLogger;

public class WarpSettings {
    
    private static final String CONFIG_FILE = "config.yml";
    private static final String settingsFile = "MyWarp.settings";
    public static File dataDir;

    public static ArrayList<WarpLimit> warpLimits;
    public static WarpLimit defaultLimit;
    public static boolean adminPrivateWarps;
    public static boolean loadChunks;
    
    public static boolean usemySQL;
    public static String mySQLhost;
    public static int mySQLport;
    public static String mySQLuname;
    public static String mySQLpass;
    public static String mySQLdb;
    public static String mySQLtable;

    private static FileConfiguration config;
    private static File configFile;
    private static MyWarp plugin;

    
    public static void initialize(MyWarp plugin) {
        WarpSettings.plugin = plugin;
        dataDir = plugin.getDataFolder();
        configFile = new File(dataDir, CONFIG_FILE);
        config = getConfig(configFile);
        
        warpLimits = new ArrayList<WarpLimit>();
        
        ConfigurationSection confdatabase = config.getConfigurationSection("mysql");
        ConfigurationSection conflimits = config.getConfigurationSection("limits");
        
        File oldConfigFile  = new File(dataDir, settingsFile);
        if (oldConfigFile.exists()) {
            PropertiesFile file = new PropertiesFile(oldConfigFile);
            //port settings
            config.set("adminPrivateWarps", file.getBoolean("adminPrivateWarps", true, "Whether or not admins can see private warps in their list"));
            config.set("loadChunks", file.getBoolean("loadChunks", false, "Force sending of the chunk which people teleport to - default: false"));

            //port limits
            conflimits.set("default.maxTotal", file.getInt("maxPublic", 5, "Maximum number of public warps any player can make")
                    + file.getInt("maxPrivate", 10, "Maximum number of private warps any player can make"));
            conflimits.set("default.maxPublic", file.getInt("maxPublic", 5, "Maximum number of public warps any player can make"));
            conflimits.set("default.maxPrivate", file.getInt("maxPrivate", 10, "Maximum number of private warps any player can make"));

            //port database
            String mySQLconn = file.getString("mySQLconn", "jdbc:mysql://localhost:3306/minecraft", "MySQL Connection (only if using MySQL)");
            mySQLconn = mySQLconn.substring(mySQLconn.indexOf("//")+2);
            String[] mySQLconnParts = mySQLconn.split("[\\W]");
            confdatabase.set("enabled", file.getBoolean("usemySQL", false, "MySQL usage --  true = use MySQL database / false = use SQLite"));
            confdatabase.set("host", mySQLconnParts[0]);
            confdatabase.set("port", mySQLconnParts[1]);
            confdatabase.set("database", mySQLconnParts[2]);
            confdatabase.set("username", file.getString("mySQLuname", "root", "MySQL Username (only if using MySQL)"));
            confdatabase.set("password", file.getString("mySQLpass", "password", "MySQL Password (only if using MySQL)"));

            try {
                config.save(configFile);
                if (!oldConfigFile.renameTo(new File(dataDir, settingsFile + ".old"))) {
                    WarpLogger.warning("Could not rename old settings file, better remove it by yourself");
                }
                else {
                    WarpLogger.info("Successfully ported old settings file");
                }
            }
            catch (IOException e) {
                WarpLogger.severe("Failed to port old settings file", e);
            }
        }
        
        //settings
        adminPrivateWarps = config.getBoolean("adminPrivateWarps");
        loadChunks = config.getBoolean("loadChunks");
        
        
        // database
        usemySQL = confdatabase.getBoolean("enabled");
        mySQLhost = confdatabase.getString("host");
        mySQLport = confdatabase.getInt("port");
        mySQLuname = confdatabase.getString("username");
        mySQLpass = confdatabase.getString("password");
        mySQLdb = confdatabase.getString("database");
        mySQLtable = confdatabase.getString("table");
        
        // limits
        for (String key : conflimits.getKeys(false)) {
            if (key.equals("default")) {
                defaultLimit = new WarpLimit(key, conflimits.getInt(key + ".maxTotal"),
                        conflimits.getInt(key + ".maxPublic"), conflimits.getInt(key
                                + ".maxPrivate"));
            } else {
                warpLimits.add(new WarpLimit(key, conflimits.getInt(key + ".maxTotal"),
                        conflimits.getInt(key + ".maxPublic"), conflimits.getInt(key
                                + ".maxPrivate")));
            }
        }
        Collections.sort(warpLimits, new WarpLimitComparator());
    }
    
    private static FileConfiguration getConfig(File file)
    {
        FileConfiguration config = null;

        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
                WarpLogger.info("Default config created successfully!");
            }

            config = plugin.getConfig();
            config.setDefaults(YamlConfiguration.loadConfiguration(plugin.getResource(file.getName())));
            config.options().copyDefaults(true);
            config.save(file);
        }
        catch (Exception e) {
            WarpLogger.warning("Default config could not be created!");
        }

        return config;
    }
}
