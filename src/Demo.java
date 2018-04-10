/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Valerie
 */
import java.sql.*;
public class Demo {
    public static void main(String[] args) {
        try { 
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://Avendano-ACER:3306/cict","root1","password1");  
                
                //System.out.println("Database Connection Success!");
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computerdetails","root","");  
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("DELETE FROM `temporary` WHERE 1");
                while(rs.next()){
                   
                     System.out.println(rs.getString("pc_serial"));
                   
                }
                
                
            } catch (Exception ex) {
                System.out.println("No connection to database or phone didnt trigger qr code.");
                System.out.println(ex);
            }
    }
}
