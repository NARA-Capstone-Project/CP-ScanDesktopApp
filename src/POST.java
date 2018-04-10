
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Valerie
 */
public class POST {
    private static HttpURLConnection con;
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        String uname = "waaaa";
        String pass = "raaa";
        String id = "SSSS";
        String datec = "2018-06-01";
        String datee = "2071-03-01";
        String stat = "Active";
        
        String urlink = "http://localhost/desktop_api/cict_addaccount.php?uname="+uname+"&pass="+pass+"&id="+id+"&datec="+datec+"&datee="+datee+"&stat="+stat;
        URL url = new URL(urlink);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
       
//       
        in.close();


    }
   
    
}


	
