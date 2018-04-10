import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.json.JSONObject;

 
public class Accounts extends javax.swing.JDialog {
    
    public static String s, a, id,
            usern, passw, signature, datecre, dateexp, acctstat;
    public static String returnmessage;
    public Accounts(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        datecreate.setText(java.time.LocalDate.now().toString());
        expire.setText(java.time.LocalDate.now().plusMonths(5).toString());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
            Statement st = con.createStatement();   
            ResultSet rs = st.executeQuery("select * from request_account");
            DefaultTableModel tbls = (DefaultTableModel) approve.getModel();
            tbls.setNumRows(0);
            while(rs.next()){
                tbls.addRow(new Object[]{
                rs.getString("id"),rs.getString("username"),rs.getString("password"),rs.getString("date_request") 
            });
            }
        }catch(Exception ex){
            
        }
        approvetxt.getDocument().addDocumentListener(new DocumentListener() {
         
            @Override
            public void insertUpdate(DocumentEvent de) {
                
                String srchbx = emlbl.getText();
                DefaultTableModel tbls = (DefaultTableModel) approve.getModel();
                tbls.setNumRows(0);
              try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
                    Statement st = con.createStatement();                   
                    
                        ResultSet rs = st.executeQuery("select * from `request_account` where `id` LIKE '"+approvetxt.getText()+"%'");
                        while(rs.next()){
                         tbls.addRow(new Object[]{
                         rs.getString("id"),rs.getString("username"),rs.getString("password"),rs.getString("date_request") 
                        });
                
                    }
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                 //To change body of generated methods, choose Tools | Templates.
                 String srchbx = emlbl.getText();
                DefaultTableModel tbls = (DefaultTableModel) approve.getModel();
                tbls.setNumRows(0);
              try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
                    Statement st = con.createStatement();
                    
                        ResultSet rs = st.executeQuery("select * from `request_account` where `id` LIKE '"+approvetxt.getText()+"%'");
                        while(rs.next()){
                         tbls.addRow(new Object[]{
                         rs.getString("id"),rs.getString("username"),rs.getString("password"),rs.getString("date_request") 
                        });
                    }
                    
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
        });
        
       //update
        
       uptbl.setRowSelectionAllowed(true);
        uptbl.setColumnSelectionAllowed(false);
        uptbl.setEnabled(true);
        DefaultTableModel tbls = (DefaultTableModel) uptbl.getModel();
       tbls.setNumRows(0); 
        uptbl.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
//            System.out.println("Database Connection Success!");
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery("select * from accounts");
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columncount = rsmd.getColumnCount();
//            ArrayList data = new ArrayList();
//            while(rs.next()){
//                tbls.addRow(new Object[]{
//                    rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)
//                });
//
//            }
//
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
        uptxt.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                 //To change body of generated methods, choose Tools | Templates.
                String srchbx = srch.getSelectedItem().toString();
                DefaultTableModel tbls = (DefaultTableModel) uptbl.getModel();
                tbls.setNumRows(0);
              try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
                    Statement st = con.createStatement();
                    if(srchbx.equals("Employee Id")){
                       
                        ResultSet rs = st.executeQuery("select * from accounts where `user_id` LIKE '"+uptxt.getText()+"%'");
                        while(rs.next()){
                         tbls.addRow(new Object[]{
                            rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) 
                            });
                    }
                    }else if(srchbx.equals("Name")){
                        ResultSet rs = st.executeQuery("select * from users where `name` LIKE '"+uptxt.getText()+"%'");
                        ArrayList usrid = new ArrayList();
                        while(rs.next()){
                            usrid.add(rs.getString("user_id"));
                        }
                        for (int i = 0; i < usrid.size(); i++) {
                            rs = st.executeQuery("select * from accounts where `user_id` LIKE '"+usrid.get(i)+"%'");
                        while(rs.next()){
                         tbls.addRow(new Object[]{
                            rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) 
                            });
                        }
                    }
                    }
                    
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                 //To change body of generated methods, choose Tools | Templates.
                String srchbx = srch.getSelectedItem().toString();
                DefaultTableModel tbls = (DefaultTableModel) uptbl.getModel();
                tbls.setNumRows(0);
              try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
                    Statement st = con.createStatement();
                    if(srchbx.equals("User Id")){
                       
                        ResultSet rs = st.executeQuery("select * from accounts where `user_id` LIKE '"+uptxt.getText()+"%'");
                        while(rs.next()){
                         tbls.addRow(new Object[]{
                            rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) 
                            });
                    }
                    }else if(srchbx.equals("Name")){
                        ResultSet rs = st.executeQuery("select * from users where `name` LIKE '"+uptxt.getText()+"%'");
                        ArrayList usrid = new ArrayList();
                        while(rs.next()){
                            usrid.add(rs.getString("user_id"));
                        }
                        for (int i = 0; i < usrid.size(); i++) {
                            rs = st.executeQuery("select * from accounts where `user_id` LIKE '"+uptxt.getText()+"%'");
                        while(rs.next()){
                         tbls.addRow(new Object[]{
                            rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7) 
                            });
                        }
                    }
                    }
                    
                    
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                
            }
        });
        
       
        del.setRowSelectionAllowed(true);
        del.setColumnSelectionAllowed(false);
        del.setEnabled(true);
        DefaultTableModel tbls1 = (DefaultTableModel) del.getModel();
        tbls1.setNumRows(0);
        del.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
            System.out.println("Database Connection Success!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from accounts");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columncount = rsmd.getColumnCount();
            ArrayList data = new ArrayList();
            
            while(rs.next()){
                tbls.addRow(new Object[]{
                    rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                });
                
                

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
        
        
        ArrayList userrole = new ArrayList();
        ArrayList userid = new ArrayList();
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        rolebx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Object selected = rolebx.getSelectedItem();
                DefaultTableModel tbls = (DefaultTableModel) viewtbl.getModel();
                tbls.setNumRows(0);
                switch (selected.toString()) {
                    case "Admin":
                        userid.clear();
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery("select * from users where `role` = '"+selected.toString()+"'");
                            //ResultSet rs1 = st.executeQuery("select * from accounts where `user_id` = ");
                            String rl;
                            while(rs.next()){
                                userid.add(rs.getString("user_id"));
                                
                            }
                            for (int i = 0; i < userid.size(); i++) {
                                rs = st.executeQuery("select * from accounts where `user_id` = '"+userid.get(i)+"'");
                                while(rs.next()){
                                    tbls.addRow(new Object[]{ 
                                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)
                                    });
                                }
                            }
                            
                            
                        } catch (Exception ex) {
                            //Logger.getLogger(ViewAccounts.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    case "Technician":
                        userid.clear(); 
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
                            Statement st = con.createStatement();
                            ResultSet rs = st.executeQuery("select * from users where `role` = '"+selected.toString()+"'");
                            //ResultSet rs1 = st.executeQuery("select * from accounts where `user_id` = ");
                            String rl;
                            while(rs.next()){
                                userid.add(rs.getString("user_id"));
                                
                            }
                            for (int i = 0; i < userid.size(); i++) {
                                rs = st.executeQuery("select * from accounts where `user_id` = '"+userid.get(i)+"'");
                                while(rs.next()){
                                    tbls.addRow(new Object[]{
                                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)
                                    });
                                }
                            }
                            
                            
                        } catch (Exception ex) {
                            //Logger.getLogger(ViewAccounts.class.getName()).log(Level.SEVERE, null, ex);
                        }   break;
                    default:
                        break;
                }
            }

            
        });
        //currentlogin.setText(LogIn.name);
        //setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        DefaultTableModel tbls3 = (DefaultTableModel) viewtbl.getModel();
        tbls3.setNumRows(0);
        viewtbl.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));
        
        try {      
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
            System.out.println("Database Connection Success!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from accounts");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columncount = rsmd.getColumnCount();
            ArrayList data = new ArrayList();
            while(rs.next()){
                tbls.addRow(new Object[]{
                rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7) 
            });
    
            }
                    
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        approvetxt = new javax.swing.JTextField();
        emlbl = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        approve = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        srch = new javax.swing.JComboBox<>();
        uptxt = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        uptbl = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        deltxt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        srch2 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        del = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        rolebx = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        viewtbl = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        employeeidtxt = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        createpass = new javax.swing.JPasswordField();
        jLabel21 = new javax.swing.JLabel();
        createuser = new javax.swing.JTextField();
        retypepass = new javax.swing.JPasswordField();
        jLabel22 = new javax.swing.JLabel();
        roletxt = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        createname = new javax.swing.JTextField();
        createphone = new javax.swing.JTextField();
        datecreate = new javax.swing.JTextField();
        expire = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        stat = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Account Management");
        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(153, 204, 255));

        jPanel1.setBackground(new java.awt.Color(255, 193, 7));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 60)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Accounts Approval");

        approvetxt.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        approvetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approvetxtActionPerformed(evt);
            }
        });

        emlbl.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        emlbl.setText("Employee Id:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(emlbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(approvetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(jLabel9)
                    .addContainerGap(702, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approvetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emlbl))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel9)
                    .addContainerGap(142, Short.MAX_VALUE)))
        );

        approve.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        approve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Username", "Password", "Date Requested"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        approve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                approveMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(approve);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(633, 633, 633)
                    .addComponent(jLabel13)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel12)
                    .addContainerGap(186, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(jLabel12))
                    .addContainerGap(610, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("APPROVE", jPanel8);

        jPanel2.setBackground(new java.awt.Color(153, 204, 255));

        jPanel27.setBackground(new java.awt.Color(255, 221, 44));

        jPanel28.setBackground(new java.awt.Color(255, 169, 1));

        jPanel29.setBackground(new java.awt.Color(255, 221, 44));

        jButton8.setBackground(new java.awt.Color(255, 128, 0));
        jButton8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton8.setText("Log-out");
        jButton8.setMaximumSize(new java.awt.Dimension(165, 45));
        jButton8.setMinimumSize(new java.awt.Dimension(165, 45));
        jButton8.setPreferredSize(new java.awt.Dimension(165, 45));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 128, 0));
        jButton11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton11.setText("Back");
        jButton11.setMaximumSize(new java.awt.Dimension(165, 45));
        jButton11.setMinimumSize(new java.awt.Dimension(165, 45));
        jButton11.setPreferredSize(new java.awt.Dimension(165, 45));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 193, 7));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 60)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Update Accounts");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Search by:");

        srch.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        srch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee Id", "Name" }));
        srch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srchActionPerformed(evt);
            }
        });

        uptxt.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        uptxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uptxtActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton12.setText("Show all accounts");
        jButton12.setMaximumSize(new java.awt.Dimension(165, 45));
        jButton12.setMinimumSize(new java.awt.Dimension(165, 45));
        jButton12.setPreferredSize(new java.awt.Dimension(165, 45));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(srch, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uptxt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(251, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(srch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uptxt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        uptbl.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        uptbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Username", "Password", "Date Created", "Date Expire", "Account Status"
            }
        ));
        uptbl.setEnabled(false);
        uptbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uptblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(uptbl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("UPDATE", jPanel2);

        jPanel6.setBackground(new java.awt.Color(153, 204, 255));

        jPanel3.setBackground(new java.awt.Color(255, 193, 7));
        jPanel3.setPreferredSize(new java.awt.Dimension(762, 152));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 60)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Delete Accounts");

        deltxt.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        deltxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deltxtActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Search by:");

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton13.setText("Show all accounts");
        jButton13.setMaximumSize(new java.awt.Dimension(165, 45));
        jButton13.setMinimumSize(new java.awt.Dimension(165, 45));
        jButton13.setPreferredSize(new java.awt.Dimension(165, 45));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        srch2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        srch2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employee Id", "Name" }));
        srch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srch2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(srch2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel15)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(srch2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        del.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        del.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Username", "Password", "Signature", "Date Created", "Date Expire", "Account Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Byte.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        del.setEnabled(false);
        del.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                delMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(del);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DELETE", jPanel6);

        jPanel7.setBackground(new java.awt.Color(153, 204, 255));

        jPanel4.setBackground(new java.awt.Color(255, 193, 7));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 60)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("View Accounts");

        rolebx.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        rolebx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Technician", "Admin" }));
        rolebx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rolebxActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Search by:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Role:");

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton14.setText("Show all accounts");
        jButton14.setMaximumSize(new java.awt.Dimension(165, 45));
        jButton14.setMinimumSize(new java.awt.Dimension(165, 45));
        jButton14.setPreferredSize(new java.awt.Dimension(165, 45));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rolebx, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rolebx, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        viewtbl.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        viewtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee ID", "Username", "Password", "Date Created", "Date Expire", "Account Status"
            }
        ));
        viewtbl.setEnabled(false);
        jScrollPane4.setViewportView(viewtbl);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("VIEW", jPanel7);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Password:");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, 30));

        employeeidtxt.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(employeeidtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 184, 35));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("+63");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 40, 30));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Employee Id:");
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 30));

        createpass.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(createpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 184, 35));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Username:");
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, 30));

        createuser.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(createuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 184, 35));

        retypepass.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(retypepass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 184, 35));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Role:");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, 40));

        roletxt.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        roletxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Head Technician", "Technician" }));
        roletxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roletxtActionPerformed(evt);
            }
        });
        jPanel9.add(roletxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 184, 40));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Retype Password:");
        jPanel9.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, 30));

        jButton15.setBackground(new java.awt.Color(255, 128, 0));
        jButton15.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton15.setText("Add Account");
        jButton15.setBorder(null);
        jButton15.setMaximumSize(new java.awt.Dimension(165, 45));
        jButton15.setMinimumSize(new java.awt.Dimension(165, 45));
        jButton15.setPreferredSize(new java.awt.Dimension(165, 45));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 240, 80));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Name:");
        jPanel9.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, -1, 30));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Account expiration:");
        jPanel9.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, 30));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Phone:");
        jPanel9.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, -1, 30));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Date Created:");
        jPanel9.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 290, -1, 30));

        createname.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(createname, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 210, 184, 35));

        createphone.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(createphone, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 250, 140, 35));

        datecreate.setEditable(false);
        datecreate.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(datecreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 184, 35));

        expire.setEditable(false);
        expire.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jPanel9.add(expire, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, 184, 35));
        jPanel9.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 86, -1, -1));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Status:");
        jPanel9.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 370, -1, 40));

        stat.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        stat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Disabled" }));
        jPanel9.add(stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, 184, -1));

        jLabel30.setFont(new java.awt.Font("Century Gothic", 0, 60)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Create Account");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 11, -1, -1));

        jLabel31.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Fill in all details:");
        jPanel9.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jTabbedPane1.addTab("CREATE", jPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    
    private static void localDatabase(){
       
    }
    private void approveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_approveMouseClicked
        // TODO add your handling code here:

        int index = approve.getSelectedRow();
        DefaultTableModel tbls = (DefaultTableModel) approve.getModel();
        id = tbls.getValueAt(index, 0).toString();
        System.out.println(id);
        new ShowApproveAccountInfo().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_approveMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        //        // TODO add your handling code here:
        //        LogIn.name = "";
        //        new LogIn().setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        //        new AccountsMenu().setVisible(true);
        //        this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void srchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srchActionPerformed

    private void uptxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uptxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uptxtActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbls = (DefaultTableModel) uptbl.getModel();
        tbls.setNumRows(0);
        uptbl.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
            System.out.println("Database Connection Success!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from accounts");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columncount = rsmd.getColumnCount();
            ArrayList data = new ArrayList();
            while(rs.next()){
                tbls.addRow(new Object[]{
                    rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)
                });

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton12ActionPerformed
    
    public static String userid;
    private void uptblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uptblMouseClicked
        int index = uptbl.getSelectedRow();
        DefaultTableModel tbls = (DefaultTableModel) uptbl.getModel();
        userid = tbls.getValueAt(index, 0).toString();
        System.out.println(userid);
        new UpdateAccounts().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_uptblMouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbls = (DefaultTableModel) del.getModel();
        tbls.setNumRows(0);
        del.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
            System.out.println("Database Connection Success!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from accounts");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columncount = rsmd.getColumnCount();
            ArrayList data = new ArrayList();
            while(rs.next()){
                if(rs.getString("user_id").equals(LogIn.id)){
                    JOptionPane.showMessageDialog(this, "Account currently logged in will not be included in the table.");
                }else{

                    tbls.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
                    });
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void srch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srch2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_srch2ActionPerformed

    private void delMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delMouseClicked
        // TODO add your handling code here:
        int index = del.getSelectedRow();
        DefaultTableModel tbls = (DefaultTableModel) del.getModel();
        id = tbls.getValueAt(index, 0).toString();
        new DisplayDelAccount().setVisible(true);
    }//GEN-LAST:event_delMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void rolebxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolebxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rolebxActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tbls = (DefaultTableModel) viewtbl.getModel();
        tbls.setNumRows(0);
        viewtbl.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 12));

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
            System.out.println("Database Connection Success!");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from accounts");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columncount = rsmd.getColumnCount();
            ArrayList data = new ArrayList();
            while(rs.next()){

                tbls.addRow(new Object[]{
                    rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6),rs.getString(7)
                });

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void deltxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deltxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deltxtActionPerformed

    private void approvetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approvetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_approvetxtActionPerformed

    private void roletxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roletxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roletxtActionPerformed
    public static int usernamelength(String username){
        int userlength = username.length();
        return userlength;
    }
    public static int passwordlength(String password){
        int passlength = password.length();
        return passlength;
    }
    public static int confirmphone(String number){
        int numlength = number.length();
        if(numlength > 10){
            return numlength;
        }
        else if(numlength < 10){
           return numlength;
        }
        else if(numlength == 10){
            return numlength;
        }else{
            return numlength;
        }
    }
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        
        try{ 
        URL url = new URL("http://localhost/desktop/cict_add_account.php?newusername="+createuser.getText()+"&newpassword="+createpass.getText()+"&employeeid="+employeeidtxt.getText()+"&role="+roletxt.getSelectedItem()
        +"&newname="+createname.getText()+"&newphone="+createphone.getText()+"&datecreate="+datecreate.getText()+"&dateexpire="+expire.getText());
            URLConnection connection = url.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            ArrayList data = new ArrayList();
            while((inputLine = in.readLine()) != null)
            {
                   returnmessage = inputLine;
            }
            if(returnmessage.equals("New record created successfully")){
                JOptionPane.showMessageDialog(this, returnmessage);
            }else if(returnmessage.equals("No users with that employee id. Account not created.")){
                JOptionPane.showMessageDialog(this, "No users with that employee id. Account not created.");
            }
            else{
                JOptionPane.showMessageDialog(this, returnmessage);
            }
            in.close(); 
        } catch (Exception ex) {
            Logger.getLogger(JavaWithHttp.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }//GEN-LAST:event_jButton15ActionPerformed
    public static void localdb(){
        //        String pass = createpass.getText(), retry = retypepass.getText();
//        if(pass.equals(retry)){
//            if(createuser.getText().equals("") || createpass.getText().equals("") || employeeidtxt.getText().equals("") || retypepass.getText().equals("") || createname.getText().equals("") ||
//                createphone.getText().equals("")){
//                JOptionPane.showMessageDialog(this, "Please do not leave important details blank.");
//            }else{
//                try {
//
//                    Class.forName("com.mysql.jdbc.Driver");
//                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cict_database","root","");
//                    Statement st = con.createStatement();
//                    if(createuser.getText() == null || createpass.getText() == null || datecreate.getText() == null || expire.getText() == null || createname.getText() == null || createphone.getText() == null){
//                        JOptionPane.showMessageDialog(this, "Fill all details please. Do not leave them blank.");
//                    }else{
//                        st.executeUpdate("UPDATE `users` SET `name`='"+createname.getText()+"',`phone`= '"+createphone.getText()+"',`role`= '"+roletxt.getSelectedItem().toString()+"' where `user_id` = '"+employeeidtxt.getText()+"';");
//
//                        // st.executeUpdate("UPDATE `accounts` SET `username`='"+newuser.getText()+"',`password`='"+newpass.getText()+"',`date_created`='"+datecreate.getText()+"',`date_expire`='"+expire.getText()+"' where `user_id` = '"+employeeidtxt.getText()+"';");
//                        InputStream is = new FileInputStream(new File(d));
//                        InputStream fs = new FileInputStream(new File(f));
//                        PreparedStatement ps = con.prepareStatement("UPDATE `accounts` SET `username`= ?,`password`= ?, `signature`= ?, `date_created`= ?, `date_expire`= ?, `acc_status` = ?, `profile_pic` = ? where `user_id` = ?;");
//                        ps.setString(1, createuser.getText());
//                        ps.setString(2, createpass.getText());
//                        ps.setBlob(3, is);
//                        ps.setString(4, datecreate.getText());
//                        ps.setString(5, expire.getText());
//                        ps.setString(6, stat.getSelectedItem().toString());
//                        ps.setBlob(7, fs);
//                        ps.setString(8, employeeidtxt.getText());
//                        ps.executeUpdate();
//                    }
//                    JOptionPane.showMessageDialog(this, "Account Updated into database.");
//                    this.dispose();
//                    new AdminDash().setVisible(true);
//                } catch (HeadlessException | ClassNotFoundException | SQLException ex) {
//                    Logger.getLogger(ApproveAccounts.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(this, ex);
//                }
//            }
//
//        }else if(!pass.equals(retry)){
//            JOptionPane.showMessageDialog(this, "Passwords do not match.");
//            createpass.setText("");
//            retypepass.setText("");
//        }


    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Accounts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Accounts dialog = new Accounts(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable approve;
    private javax.swing.JTextField approvetxt;
    private javax.swing.JTextField createname;
    private javax.swing.JPasswordField createpass;
    private javax.swing.JTextField createphone;
    private javax.swing.JTextField createuser;
    private javax.swing.JTextField datecreate;
    private javax.swing.JTable del;
    private javax.swing.JTextField deltxt;
    private javax.swing.JLabel emlbl;
    private javax.swing.JTextField employeeidtxt;
    private javax.swing.JTextField expire;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPasswordField retypepass;
    private javax.swing.JComboBox<String> rolebx;
    private javax.swing.JComboBox<String> roletxt;
    private javax.swing.JComboBox<String> srch;
    private javax.swing.JComboBox<String> srch2;
    private javax.swing.JComboBox<String> stat;
    private javax.swing.JTable uptbl;
    private javax.swing.JTextField uptxt;
    private javax.swing.JTable viewtbl;
    // End of variables declaration//GEN-END:variables
}
