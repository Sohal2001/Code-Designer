/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDesigner;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class query {

    Connection con;
    String url = "jdbc:mysql://localhost/";
    Statement st;
    ResultSet rsd, rst, rsheadn, rstbd;
    String rsdn, rstn, q, dname, qs;
    Vector db = new Vector();
    Vector tb = new Vector();
    DefaultTableModel tm;
    Vector head, data, row;
    JTable table;

    query() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
//            System.out.println("Connected...");
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Exception caught...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void openconn() throws SQLException {
        con = DriverManager.getConnection(url, "root", "");
        st = con.createStatement();
    }

    public void insertrow(String s1, String s2, int s3) throws SQLException {
        q = String.format("insert into %s (%s) values (%s)", s1, s2, s3);
        st.executeUpdate(q);
    }

    public void insertcol(String s1, String s2, String s3) throws SQLException {
        q = String.format("alter table %s add(%s %s)", s1, s2, s3);
        int n = st.executeUpdate(q);
        if (n > 0) {
            JOptionPane.showMessageDialog(null, "Column Inserted successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Column not inserted");
        }
    }

    public void update(String s1, String s2, String s3, String s4, int s5) throws SQLException {
        q = String.format("update %s set %s='%s' where %s=%s", s1, s2, s3, s4, s5);
        int i = st.executeUpdate(q);
    }

    public void update(String s1, String s2, String s3, String s4, String s5) throws SQLException {
        q = String.format("update %s set %s='%s' where %s=%s", s1, s2, s3, s4, s5);
        st.executeUpdate(q);
    }

    public void delete(String s1, String s2, int s3) throws SQLException {
        q = String.format("delete from %s where %s like %s", s1, s2, s3);
        st.executeUpdate(q);
    }

    public void deletecol(String s1, String s2) throws SQLException {
        q = String.format("alter table %s drop column %s", s1, s2);
        st.executeUpdate(q);
    }

    public void createdb(String dbname) throws SQLException {
        q = String.format("create database %s", dbname);
        int n = st.executeUpdate(q);
        if (n == 1) {
            JOptionPane.showMessageDialog(null, dbname + " created successfully");
        } else {
            JOptionPane.showMessageDialog(null, dbname + " not created");
        }
    }

    public Vector showdb() {
        try {
            q = String.format("show databases");
            rsd = st.executeQuery(q);
            while (rsd.next()) {
                db.add(rsd.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return db;
    }

    public Vector showtb(String db) {
        try {
            q = String.format("use %s", db);
            st.executeUpdate(q);
            qs = String.format("show tables");
            rst = st.executeQuery(qs);
            while (rst.next()) {
                tb.add(rst.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return tb;
    }

    public void deletedb(String dbname) {
        try {
            q = String.format("drop database %s", dbname);
            int n = st.executeUpdate(q);
            if (n == 0) {
                JOptionPane.showMessageDialog(null, dbname + " deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, dbname + " not deleted");
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void deleteTb(String dbname, String tname) {
        try {
            q = String.format("drop table %s.%s", dbname, tname);
            int i = st.executeUpdate(q);
            System.out.println(i);
            if (i == 0) {
                JOptionPane.showMessageDialog(null, tname + " deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, tname + " not deleted");
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void renamedb(String dbname) throws SQLException {
        dname = JOptionPane.showInputDialog(null, "ENTER THE NAME OF YOUR New DATABASE", "MyDataBase");
        q = String.format("rename database %s to %s", dbname, dname);

        int rndb = st.executeUpdate(q);

        if (rndb == 0) {
            JOptionPane.showMessageDialog(null, dbname + "renamed successfully to " + dname);
        } else {
            JOptionPane.showMessageDialog(null, dbname + " not renamed successfully to" + dname);
        }
    }

    public DefaultTableModel table(String tbname) {
        try {
            head = new Vector();
            q = String.format("desc %s", tbname);
            rsheadn = st.executeQuery(q);
            while (rsheadn.next()) {
                head.add(rsheadn.getString(1));
            }

            data = new Vector();
            q = String.format("select * from %s", tbname);
            rstbd = st.executeQuery(q);
            int countxx = 0;
            while (rstbd.next()) {
                countxx = 1;
                row = new Vector();
                while (countxx <= head.size()) {
                    row.add(rstbd.getString(countxx));
                    countxx++;
                }
                data.add(row);
            }
            tm = new DefaultTableModel(data, head);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return tm;
    }

    void createTb(String dname, String tbl, String fieldname, String datatype, String attributes) throws SQLException {

        q = String.format("use %s", dname);
        st.executeUpdate(q);
        q = String.format("create table %s (%s %s %s)", tbl, fieldname, datatype, attributes);
        int m = st.executeUpdate(q);
        if (m == 0) {
            JOptionPane.showMessageDialog(null, tbl + "Created Succeed...");
        } else {
            JOptionPane.showMessageDialog(null, tbl + " not Succeed...");
        }
    }

    void alterTb(String tbl, String fieldname, String datatype, String attributes) throws SQLException {
        q = String.format("alter table %s add (%s %s %s)", tbl, fieldname, datatype, attributes);
        int m = st.executeUpdate(q);
    }
}