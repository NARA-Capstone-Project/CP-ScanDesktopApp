
import com.google.zxing.WriterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.filechooser.FileSystemView;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import java.net.*;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.write.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Valerie
 */

public class MainMenu {

    /**
     * @param args the command line arguments
     */
    
    private static final String EXCEL_FILE_LOCATION = "C:\\Users\\Valerie\\Documents\\NetBeansProjects\\GetComputerInfoWithUsb\\ComputerData.xls";
    public static String kb="Ok", model, motherboard = "Ok", motherserial, ram = "", mouse="Ok", hdd = "", hostname="", os="";
    public static ArrayList check = new ArrayList();
    
    private static String getWindowsMotherboard_SerialNumber() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs =
            "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
              + "Set colItems = objWMIService.ExecQuery _ \n"
              + "   (\"Select * from Win32_BaseBoard\") \n"
              + "For Each objItem in colItems \n"
              + "    Wscript.Echo objItem.SerialNumber \n"
              + "    exit for  ' do the first cpu only! \n"
              + "Next \n";

            fw.write(vbs);
            fw.close();

            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
               result += line;
            }
            input.close();
        }
        catch(Exception E){
             System.err.println("Windows MotherBoard Exp : "+E.getMessage());
        }
        return result.trim();
    } 
    public static void getRam() throws SigarException, UnknownHostException, IOException{
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = File.listRoots();
        /* For each filesystem root, print some info */
        Sigar sigar = new Sigar();
        org.hyperic.sigar.CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
        for(org.hyperic.sigar.CpuInfo info : cpuInfoList){
        String modl = info.getModel();
        String ghz = modl.substring(31);
        //System.out.println(modl);
        ram = ghz;
        break;
    }
      
//        Runtime.getRuntime().exec("C:\\Users\\Valerie\\source\\repos\\ConsoleApplication1\\Debug\\ConsoleApplication1.exe", null, new File("C:\\Users\\Valerie\\source\\repos\\ConsoleApplication1\\Debug"));
//        String fileName1 = "C:\\Users\\Valerie\\source\\repos\\ConsoleApplication1\\Debug\\usbportkeyboard.txt";
//        String fileName2 = "C:\\Users\\Valerie\\source\\repos\\ConsoleApplication1\\Debug\\usbportmouse.txt";
//        // This will reference one line at a time
//        String line = null;
//
//        try {
//            
//            // FileReader reads text files in the default encoding.
//            FileReader fileReader1 = new FileReader(fileName1);
//            FileReader fileReader2 = new FileReader(fileName2);
//
//            // Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
//            BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
//
//            while((line = bufferedReader1.readLine()) != null) {
//                kb = line;
//            }
//            while((line = bufferedReader2.readLine()) != null) {
//                mouse = line;
//            }
//             
//
//            
//            bufferedReader1.close();
//            bufferedReader2.close();
//        }
//        catch(FileNotFoundException ex) {
//            System.out.println("Unable to open file '" + fileName1 + "'");                
//        }
//        catch(IOException ex) {
//            System.out.println("Error reading file '" + fileName1 + "'");                  
//        }
        for(org.hyperic.sigar.CpuInfo info : cpuInfoList){
        String modl = info.getModel();
            
        model = modl.substring(0, 16);
        model = model.substring(9,11);
        break;
    }   
        File file = new File("c:");
    	long totalSpace = file.getTotalSpace(); 
        
        long hddmem = totalSpace;
        long mb = hddmem /1024 /1024 /1024; //convert mb to gb
        hdd = Long.toString(mb) + "gb";
    
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        hostname = addr.getHostName();  
    }
    private static String SendDataToServer(String processor, String os , String motherserial, String Ram, String Kb, String mouse, String hdd, String hostname){
        Connection con;
        Statement st;
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");  
        System.out.println("Database Connection Success!");
        
        st = con.createStatement();
        
        st.executeUpdate("insert into `computers` (`os`,`processor`,`motherboard`,`ram`,`keyboard`,`mouse`,`hdd`) values ('"+os+"', '"+processor+"', '"+motherserial+"', '"+ram+"', '"+Kb+"', '"+mouse+"', '"+hdd+"')");
        
        }catch(Exception ex){
            System.out.println(ex);
        }
        return "";
    }
    private void webServer() throws SigarException, IOException, InterruptedException{
        String motherBoard_SerialNumber = getWindowsMotherboard_SerialNumber();
        motherserial = motherBoard_SerialNumber;
        
        getRam();
   
        System.out.println(model);
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        //System.out.println(ram+model+);
//        String linez, line;
        
//        for(int x=0; x<1; x--){
//            
//            Thread.sleep(1000); //Time is in milliseconds
//            
//            try { 
//              String urlink = "http://localhost/desktop_api/cict_checkqr.php";
//              URL url = new URL(urlink);
//              HttpURLConnection con = (HttpURLConnection) url.openConnection();
//              BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//              String fileName = "C:\\xampp\\htdocs\\desktop_api\\qr.txt";
//              try {
//              FileReader fileReader = new FileReader(fileName);
//              BufferedReader bufferedReader = new BufferedReader(fileReader);
//              while((line = bufferedReader.readLine()) != null) {
//                  check.add(line);
//                }   
//                  System.out.println(check);
//              bufferedReader.close();  
//              if(check.contains(motherserial)){
//                  
//              String lnk = "http://localhost/desktop_api/cict_sendcompdata.php?comp_id="+hostname+"&model="+model+"&motherserial="+motherserial+"&ram="+ram+"&kboard="+kb+"&mouse="+mouse+"&hdd="+hdd+"";      
//              URL lru = new URL(lnk);
//              HttpURLConnection noc = (HttpURLConnection) lru.openConnection();
//              noc.setRequestMethod("GET");
//              BufferedReader inn = new BufferedReader(new InputStreamReader(noc.getInputStream()));
//              inn.close();
//              new DisplayCompdata().setVisible(true);
//              Thread.currentThread().stop();
//              }else{
//                System.out.println("QR not scanned!");
//              }
//                  
//            }catch(Exception ex) {
//                      System.out.println(ex);
//                }
//              
//            }catch(Exception ex){
//                System.out.println(ex);
//                }
//        }

    for(int x=0; x<1; x--){
            Thread.sleep(1000); //Time is in milliseconds
            try { 
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");  
                
                //System.out.println("Database Connection Success!");
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computerdetails","root","");  
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from temporary");
                while(rs.next()){
                   if(rs.getString("pc_serial").equals(motherserial)){
                   //  SendDataToServer(model, ,motherserial, ram, kb, mouse, hdd, hostname);
                    
                    DisplayCompData disp = new DisplayCompData();
                    disp.show();
                    ShowQrCode qr = new ShowQrCode();
                    qr.dispose();
                    
                    Thread.currentThread().stop();
                    }else if(rs.getString("pc_serial").isEmpty()){
                    System.out.println("QR not yet scanned.");
                    }
                    
                   
                }
                //System.out.println("noob");
                
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
    }
    }
    public static void main(String[] args) throws IOException, SigarException, WriterException, InterruptedException {
       String motherBoard_SerialNumber = getWindowsMotherboard_SerialNumber();
        motherserial = motherBoard_SerialNumber;
        
        getRam();
   
        System.out.println(model);
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        qrCodeGenerator.main(args);
        String opersys = System.clearProperty("os.name");
        for(int x=0; x<1; x--){
            Thread.sleep(1000); //Time is in milliseconds
            try { 
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");  
                
                //System.out.println("Database Connection Success!");
                //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/computerdetails","root","");  
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from temporary");
                System.out.println(motherserial);
                while(rs.next()){
                   if(rs.getString("pc_serial").equals(motherserial)){
                    SendDataToServer(model, opersys ,motherserial, ram, kb, mouse, hdd, hostname);
                    
                    DisplayCompData disp = new DisplayCompData();
                    disp.show();
                    ShowQrCode qr = new ShowQrCode();
                    qr.dispose();
                    
                    Thread.currentThread().stop();
                    }else if(rs.getString("pc_serial").isEmpty()){
                    System.out.println("QR not yet scanned.");
                    }
                    
                   
                }
                //System.out.println("noob");
                
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
    }
    }
}
