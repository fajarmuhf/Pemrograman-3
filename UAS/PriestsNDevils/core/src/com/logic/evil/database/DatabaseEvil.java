package com.logic.evil.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;
import com.logic.evil.component.HistoryLogin;
import com.logic.evil.component.HistoryRegister;
import com.logic.evil.component.Player;
import com.logic.evil.component.Skor;
import com.logic.evil.component.SkorDetail;

public class DatabaseEvil {

    Database dbHandler;
    
    //Untuk Tabel Player
    public static final String TABLE_PLAYER = "player";
    public static final String PLAYER_ID = "_id";
    public static final String PLAYER_USERNAME = "username";
    public static final String PLAYER_PASSWORD = "password";
    public static final String PLAYER_TANGGAL = "tanggal";

    //Untuk Tabel Skor
    public static final String TABLE_SKOR = "skor";
    public static final String SKOR_ID = "_id";
    public static final String SKOR_PLAYERID = "idplayer";
    public static final String SKOR_SKOR = "skor";
    
    //Untuk Tabel HistoryLogin
    public static final String TABLE_HISTORYLOGIN = "historylogin";
    public static final String HISTORYLOGIN_ID = "_id";
    public static final String HISTORYLOGIN_PLAYERID = "idplayer";
    public static final String HISTORYLOGIN_TANGGAL = "tanggal";
    
    //Untuk Tabel HistoryRegister
    public static final String TABLE_HISTORYREGISTER = "historyregister";
    public static final String HISTORYREGISTER_ID = "_id";
    public static final String HISTORYREGISTER_PLAYERID = "idplayer";
    public static final String HISTORYREGISTER_TANGGAL = "tanggal";
    
    private static final String DATABASE_NAME = "PND.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_TABLE_PLAYER = "create table if not exists "
            + TABLE_PLAYER + "(" + PLAYER_ID
            + " integer primary key autoincrement, " + PLAYER_USERNAME
            + " text not null, " + PLAYER_PASSWORD
            + " text not null, " + PLAYER_TANGGAL
            + " text not null);";
    
    private static final String DATABASE_TABLE_SKOR = "create table if not exists "
            + TABLE_SKOR + "(" + SKOR_ID
            + " integer primary key autoincrement, " + SKOR_PLAYERID
            + " integer not null, " + SKOR_SKOR
            + " integer not null);";
    
    private static final String DATABASE_TABLE_HISTORYLOGIN = "create table if not exists "
            + TABLE_HISTORYLOGIN + "(" + HISTORYLOGIN_ID
            + " integer primary key autoincrement, " + HISTORYLOGIN_PLAYERID
            + " integer not null, " + HISTORYLOGIN_TANGGAL
            + " text not null);";
    
    private static final String DATABASE_TABLE_HISTORYREGISTER = "create table if not exists "
            + TABLE_HISTORYREGISTER + "(" + HISTORYREGISTER_ID
            + " integer primary key autoincrement, " + HISTORYREGISTER_PLAYERID
            + " integer not null, " + HISTORYREGISTER_TANGGAL
            + " text not null);";

    public DatabaseEvil() {
        Gdx.app.log("DatabaseTest", "creation started");
        dbHandler = DatabaseFactory.getNewDatabase(DATABASE_NAME,
                DATABASE_VERSION, null, null);

        dbHandler.setupDatabase();
        try {
            dbHandler.openOrCreateDatabase();
            dbHandler.execSQL(DATABASE_TABLE_PLAYER);
            dbHandler.execSQL(DATABASE_TABLE_SKOR);
            dbHandler.execSQL(DATABASE_TABLE_HISTORYLOGIN);
            dbHandler.execSQL(DATABASE_TABLE_HISTORYREGISTER);
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }

        Gdx.app.log("DatabaseTest", "created successfully");
        
    }
    
    public void tambahPlayer(Player player){
    	String username = player.getUsername();
    	String password = player.getPassword();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String tanggal = df.format(player.getTanggal());
    	
    	try {
            dbHandler
                    .execSQL("INSERT INTO player ('username','password','tanggal') VALUES ('"+username+"','"+password+"','"+tanggal+"')");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }
    
    public void tambahSkor(Skor skor){
    	int idplayer = skor.getIdplayer();
    	int skorPemain = skor.getSkor();
    	
    	try {
            dbHandler
                    .execSQL("INSERT INTO skor ('idplayer','skor') VALUES ("+idplayer+","+skorPemain+")");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }
    
    public void tambahHistoryLogin(HistoryLogin login){
    	int idplayer = login.getIdPlayer();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String tanggal = df.format(login.getTanggal());
    	
    	try {
            dbHandler
                    .execSQL("INSERT INTO historylogin ('idplayer','tanggal') VALUES ("+idplayer+",'"+tanggal+"')");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }
    
    public void tambahHistoryRegister(HistoryRegister register){
    	int idplayer = register.getIdPlayer();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String tanggal = df.format(register.getTanggal());
    	
    	try {
            dbHandler
                    .execSQL("INSERT INTO historyregister ('idplayer','tanggal') VALUES ("+idplayer+",'"+tanggal+"')");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
    }
    
    public int getIdByUsername(Player player){
    	DatabaseCursor cursor = null;
    	String username = player.getUsername();

        try {
            cursor = dbHandler.rawQuery("SELECT * FROM player where username = '"+username+"'");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        while (cursor.next()) {
        	return cursor.getInt(0);
        }
        return 0;
    }
    
    public boolean cekUsername(Player player){
    	DatabaseCursor cursor = null;
    	String username = player.getUsername();

        try {
            cursor = dbHandler.rawQuery("SELECT COUNT(*) FROM player where username = '"+username+"'");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        while (cursor.next()) {
        	if(cursor.getInt(0) > 0){
        		return false;
        	}
        }
        return true;
    }
    
    public boolean cekLogin(Player player){
    	DatabaseCursor cursor = null;
    	String username = player.getUsername();
    	String password = player.getPassword();

        try {
            cursor = dbHandler.rawQuery("SELECT COUNT(*) FROM player where username = '"+username+"' AND password = '"+password+"'");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        while (cursor.next()) {
        	if(cursor.getInt(0) > 0){
        		return true;
        	}
        }
        return false;
    }
    
    public void tampilPlayer(){
    	DatabaseCursor cursor = null;

        try {
            cursor = dbHandler.rawQuery("SELECT * FROM player");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        while (cursor.next()) {
            Gdx.app.log("Id", String.valueOf(cursor.getInt(0)));
            Gdx.app.log("Username", String.valueOf(cursor.getString(1)));
            Gdx.app.log("Password", String.valueOf(cursor.getString(2)));
            Gdx.app.log("Tanggal", String.valueOf(cursor.getString(3)));
        }
    }
    
    public List<SkorDetail> tampilSkor(){
    	DatabaseCursor cursor = null;
    	List<SkorDetail> list = new ArrayList<SkorDetail>();

        try {
            cursor = dbHandler.rawQuery("select * from skor as a,player as b where a.idplayer = b._id ORDER BY a.skor LIMIT 5");
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        while (cursor.next()) {
        	SkorDetail skordetail = new SkorDetail();
        	skordetail.setUsername(cursor.getString(4));
        	skordetail.setSkor(cursor.getInt(2));
        	list.add(skordetail);
        }
        
        return list;
    }
    
    public void closeDB(){
        try {
            dbHandler.closeDatabase();
        } catch (SQLiteGdxException e) {
            e.printStackTrace();
        }
        dbHandler = null;
        Gdx.app.log("DatabaseTest", "dispose");
    }
}