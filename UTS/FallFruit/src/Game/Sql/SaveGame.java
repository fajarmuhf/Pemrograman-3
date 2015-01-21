package Game.Sql;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import Game.utility.*;

public class SaveGame {
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/FallFruit";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "";
	   
	   public void getHighscoreByLevel(int level,int indexSkor,ArrayList<Player> player ){
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM `skor` as a,player as b WHERE a.id_player = b.id AND a.id_level = "+level+" ORDER BY skor DESC";
		      System.out.print(""+sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      int i=0;
		      while(rs.next()){
		         //Retrieve by column name
		         if(i < indexSkor){
			        String Username  = rs.getString("username");
			        int skor = rs.getInt("skor");
			        
			        Player pemain = new Player();
			        pemain.setUsername(Username);
			        pemain.setSkor(skor);
			        
			        player.add(pemain);
		         }
		         i++;
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
	   }
	   
	   public boolean cekPlayer(String username,String password,PlayerLevel player){
		   Connection conn = null;
		   Statement stmt = null;
		   boolean kembali=false;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM player WHERE Username='"+username+"' AND Password ='"+password+"'";
		      System.out.print(""+sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         String Username  = rs.getString("username");
		         String Password  = rs.getString("password");
		         String Email  = rs.getString("email");
		         
		         player.setId(id);
		         player.setUsername(Username);
		         player.setPassword(Password);
		         player.setEmail(Email);
		         
		         kembali=true;
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return kembali;
	   }
	   public boolean mendapatkanLevel(String username,String password,PlayerLevel player){
		   Connection conn = null;
		   Statement stmt = null;
		   boolean kembali=false;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM `detail level` as a,player as b WHERE a.id_player = b.id AND b.Username='"+username+"' AND b.Password ='"+password+"'";
		      System.out.print(""+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      player.setUnlock(new ArrayList<Integer>());
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int unlock = rs.getInt("unlock"); 
		         
		         player.addUnlock(unlock);
		         
		         kembali=true;
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return kembali;
	   }
	   
	   public void daftarkanPemain(String username,String password,String email){
		   Connection conn = null;
		   PreparedStatement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.prepareStatement("INSERT INTO player(`id`, `username`, `password`, `email`) SELECT COUNT(id)+1,?,?,? FROM player WHERE 1");

		      stmt.setString(1, username);
		      stmt.setString(2, password);
		      stmt.setString(3, email);
		      
		      stmt.executeUpdate();
		      
		      stmt = conn.prepareStatement("INSERT INTO `detail level`(`id`, `id_level`, `id_player`, `unlock`) SELECT COUNT(id)+1 , ? , ? , ? FROM `detail level` WHERE 1");

		      stmt.setInt(1, 1);
		      stmt.setInt(2, getIdByUsername(username,password));
		      stmt.setInt(3, 1);
		      
		      stmt.executeUpdate();
		      
		      stmt = conn.prepareStatement("INSERT INTO `detail level`(`id`, `id_level`, `id_player`, `unlock`) SELECT COUNT(id)+1 , ? , ? , ? FROM `detail level` WHERE 1");

		      stmt.setInt(1, 2);
		      stmt.setInt(2, getIdByUsername(username,password));
		      stmt.setInt(3, 0);
		      
		      stmt.executeUpdate();
		      
		      stmt = conn.prepareStatement("INSERT INTO `detail level`(`id`, `id_level`, `id_player`, `unlock`) SELECT COUNT(id)+1 , ? , ? , ? FROM `detail level` WHERE 1");

		      stmt.setInt(1, 3);
		      stmt.setInt(2, getIdByUsername(username,password));
		      stmt.setInt(3, 0);
		      
		      stmt.executeUpdate();
		      
		      
		      System.out.println("Inserted records into the table...");
		      
		      //STEP 6: Clean-up environment
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	   }
	   
	   public int getIdByUsername(String username,String password){
		   Connection conn = null;
		   Statement stmt = null;
		   int Idplayer=0;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM player WHERE Username='"+username+"' AND Password = '"+password+"'";
		      System.out.print(""+sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         Idplayer = id;
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return Idplayer;
	   }
	   
	   public boolean daftarPlayer(String username,String password,String email){
		   Connection conn = null;
		   Statement stmt = null;
		   boolean cekUsername=true;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM player WHERE Username='"+username+"'";
		      System.out.print(""+sql);
		      ResultSet rs = stmt.executeQuery(sql);

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         cekUsername=false;
		      }
		      if(cekUsername){
		    	  daftarkanPemain(username,password,email);
		      }
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   return cekUsername;
	   }
	   
	   public void simpanSkor(int idUser,int level,int Skor) {
		   Connection conn = null;
		   PreparedStatement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");
		
		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		
		      //STEP 4: Execute a query
		      System.out.println("Inserting records into the table...");
		      stmt = conn.prepareStatement("INSERT INTO `skor`(`id`, `id_player`, `id_level`, `skor`, `tanggal`) SELECT count(*)+1, ? , ? , ? , ? FROM skor WHERE 1");

		      stmt.setInt(1, idUser);
		      stmt.setInt(2, level);
		      stmt.setInt(3, Skor);
		      stmt.setString(4, new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		      stmt.executeUpdate();
		      
		      System.out.println("Inserted records into the table...");
		      
		      //STEP 6: Clean-up environment
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	   }//end main
	public void naikLevel(int id, int level) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int LevelNaik = level+1;
		if(LevelNaik <= 3){
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.jdbc.Driver");
			
			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			      //STEP 4: Execute a query
			      System.out.println("Updating records into the table...");
			      
			      String sql = "UPDATE `detail level` SET `unlock`=? WHERE `id_level`= ? AND `id_player`= ?";
			      stmt = conn.prepareStatement(sql);
			      System.out.print(sql);
			      
			      stmt.setInt(1, 1);
			      stmt.setInt(2, LevelNaik);
			      stmt.setInt(3, id);
			      
			      stmt.executeUpdate();
			      
			      System.out.println("Inserted records into the table...");
			      
			      //STEP 6: Clean-up environment
			      stmt.close();
			      conn.close();
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("Goodbye!");
		}
	}
}
