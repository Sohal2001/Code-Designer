/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CodeDesigner;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author SOHAL
 */
public class CodeDesigner extends JFrame {

    query q = new query();
    Vector vd = q.showdb();
    Vector vt;
    Vector chead = new Vector(2);
    Vector data = new Vector();
    int ctr = 0, rc, cc = 0, newrc = 0;
    String rsdn, rstn;
    String tablename, field;
    JTable jTable2;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("DataBase Editing");
    DefaultMutableTreeNode create = new DefaultMutableTreeNode("Create");
    DefaultMutableTreeNode show = new DefaultMutableTreeNode("Show Database Tables");
    DefaultMutableTreeNode delete = new DefaultMutableTreeNode("Delete");
    DefaultMutableTreeNode database = new DefaultMutableTreeNode("DataBase Wise Table");
    DefaultMutableTreeNode table = new DefaultMutableTreeNode("Table");
    DefaultMutableTreeNode createdb = new DefaultMutableTreeNode("Create_DataBase");
    DefaultMutableTreeNode createtb = new DefaultMutableTreeNode("Create_Table");
    DefaultMutableTreeNode deletedb = new DefaultMutableTreeNode("Delete_DataBase");
    DefaultMutableTreeNode deletetb = new DefaultMutableTreeNode("Delete_Table");
    DefaultMutableTreeNode tablenode;
    DefaultTableModel tbm, tbm1;
    JTextField jtf1 = new JTextField(16);
    JTextField jtf2 = new JTextField(16);
    JTextField jtf3 = new JTextField(16);
    JLabel l1 = new JLabel("Enter DataBase_Name :");
    JLabel l2 = new JLabel("Enter Table_Name :");
    JLabel l3 = new JLabel("Enter No. of Tb_Columns :");
    JLabel l4 = new JLabel("Enter Column_Name & DataType(row by row)");
    JLabel l5 = new JLabel();
    JLabel dpl = new JLabel();
    JLabel dpl2 = new JLabel();
    JPanel jp = new JPanel();
    JCheckBox p_key = new JCheckBox();
    JCheckBox f_key = new JCheckBox();
    JCheckBox autoinc = new JCheckBox();
    String attr;
    JButton save, ok1, reset, cancel;
    JButton addrow, addcolumn, update, deletrow, deletcolumn;
    JComboBox dbcombo = new JComboBox(vd);
    JComboBox tbcombo = new JComboBox();
    JComboBox dtypecombo = new JComboBox();
    JLabel lbl;
    JTree jTree1;
    final Toolkit kit = Toolkit.getDefaultToolkit();
    final Clipboard clipboard = kit.getSystemClipboard();
    DefaultTreeModel model0 = new DefaultTreeModel(root);
    static final DataFlavor[] supportedFlavors = {null, DataFlavor.imageFlavor};

    static {
        try {
            supportedFlavors[0] = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
        } catch (Exception ex) {
        }
    }
    JEditorPane editor;
    Object object;
    int mousex, mousey;
    drawingPanel drawingpanel;
    Generator generator;

    public CodeDesigner() throws SQLException {

        initComponents();
//        jInternalFrame1.setVisible(false);
//        jInternalFrame2.setVisible(false);
//        jInternalFrame3.setVisible(false);
//        jInternalFrame4.setVisible(false);
//        jInternalFrame5.setVisible(false);
//        jInternalFrame6.setVisible(false);
        this.setAlwaysOnTop(false);
        this.setLocation(0, 0);
        initree();
        jtp1.removeAll();
        jp.removeAll();
        jp.setName("");
        jp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        l1.setFont(new java.awt.Font("Tahoma", 1, 11));
        l1.setForeground(Color.DARK_GRAY);
        l2.setFont(new java.awt.Font("Tahoma", 1, 11));
        l2.setForeground(Color.DARK_GRAY);
        l3.setFont(new java.awt.Font("Tahoma", 1, 11));
        l3.setForeground(Color.DARK_GRAY);
        l4.setFont(new java.awt.Font("Tahoma", 1, 11));
        l4.setForeground(Color.DARK_GRAY);

        c.fill = GridBagConstraints.HORIZONTAL;
        l5.setFont(new java.awt.Font("Tahoma", 1, 12));
        l5.setForeground(Color.DARK_GRAY);
        l5.setText(" /* NOTE_1:Choose any Node From DataBase_tree in Database_window - To do the operations related to DataBase");
        JLabel l8 = new JLabel("      NOTE_2:Drag Button from ToolBox And Drop it to FlowChart Window */");
        l8.setFont(new java.awt.Font("Tahoma", 1, 12));
        l8.setForeground(Color.DARK_GRAY);
        c.gridx = 0;
        c.gridy = 0;
        jp.add(l5, c);
        c.gridx = 0;
        c.gridy = 1;
        jp.add(l8, c);
        jtp1.add(jp);
        jtp1.setVisible(true);
        jtp1.repaint();
        jTable2 = new JTable();
        tbm1 = new DefaultTableModel();
        dtypecombo.addItem("INT(20)");
        dtypecombo.addItem("TEXT");
        dtypecombo.addItem("VARCHAR(20)");
        dtypecombo.addItem("NUMBER(5)");
        save = new JButton("OK");
        ok1 = new JButton("OK");
        tree.expandRow(0);
        drawingpanel = new drawingPanel();
        dpl.setText(" NOTE : /*Drag and Drop Button from ToolBox ");
        dpl.setFont(new java.awt.Font("Tahoma", 1, 12));
        dpl.setForeground(Color.DARK_GRAY);
        drawingpanel.add(dpl);
        dpl2.setText(" Window to generate BackEnd Code... */");
        dpl2.setFont(new java.awt.Font("Tahoma", 1, 12));
        dpl2.setForeground(Color.DARK_GRAY);
        drawingpanel.add(dpl2);
        dpl.setAlignmentX(Component.CENTER_ALIGNMENT);
        jtab.addTab("Design", drawingpanel);
        jtab.addTab("Code", new codePanel());
        jtab.setVisible(true);
        generator = new Generator();
        mylistener dlistener = new mylistener(jPanel4, drawingpanel);
        DragSource ds1 = new DragSource();
        ds1.createDefaultDragGestureRecognizer(jLabel1, DnDConstants.ACTION_COPY, dlistener);
        DragSource ds2 = new DragSource();
        ds2.createDefaultDragGestureRecognizer(jLabel2, DnDConstants.ACTION_COPY, dlistener);
        DragSource ds3 = new DragSource();
        ds3.createDefaultDragGestureRecognizer(jLabel3, DnDConstants.ACTION_COPY, dlistener);
        DragSource ds4 = new DragSource();
        ds4.createDefaultDragGestureRecognizer(jLabel4, DnDConstants.ACTION_COPY, dlistener);

//        int cn=0;
//        p_key.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//               cn=cn+1;
//               if(cn==1)
//               {
//                   
//               }
//            }
//        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    initree();
                    if (save.getParent().getName() == "Create_DataBase") {
                        q.createdb(jtf1.getText());
                        jtf1.setText("");
                        initree();
                    } else if (save.getParent().getName() == "Delete_Table") {
                        q.deleteTb(dbcombo.getSelectedItem().toString(), tbcombo.getSelectedItem().toString());
                        initree();
                    } else if (save.getParent().getName() == "Delete_DataBase") {
                        q.deletedb(dbcombo.getSelectedItem().toString());
                        jtf1.setText("");
                        jtf2.setText("");
                        initree();
                    } else if (save.getParent().getName() == "Create_Table") {
                        if (ctr == 0) {
                            jp.repaint();
                            tbm1 = new DefaultTableModel(new Object[]{"Field_Name", "Data_Type", "Primary_Key", "Auto_Inc"}, Integer.parseInt(jtf3.getText()));
                            jTable2.setModel(tbm1);
                            jTable2.setName(dbcombo.getSelectedItem().toString() + "." + jtf2.getText());
                            jTable2.getCellSelectionEnabled();
                            TableColumn comboCol1 = jTable2.getColumnModel().getColumn(1);
                            comboCol1.setCellEditor(new DefaultCellEditor(dtypecombo));
                            TableColumn p_keycol = jTable2.getColumnModel().getColumn(2);
                            p_keycol.setCellEditor(new DefaultCellEditor(p_key));
                            TableColumn autoinccol = jTable2.getColumnModel().getColumn(3);
                            autoinccol.setCellEditor(new DefaultCellEditor(autoinc));
                            jp.removeAll();
                            jp.setLayout(new GridBagLayout());
                            GridBagConstraints c = new GridBagConstraints();
                            c.fill = GridBagConstraints.HORIZONTAL;
                            c.gridx = 0;
                            c.gridy = 2;
                            jp.add(l4, c);
                            c.gridx = 1;
                            c.gridy = 2;
                            jp.add(jTable2, c);
                            c.gridx = 0;
                            c.gridy = 3;
                            jp.add(save, c);
                            c.gridx = 1;
                            c.gridy = 3;
                            jp.add(cancel, c);
                            l5.setFont(new java.awt.Font("Tahoma", 1, 12));
                            l5.setForeground(Color.DARK_GRAY);
                            l5.setText("/*Enter Field_Name and Corresponding DataType");
                            JLabel l6 = new JLabel();
                            l6.setFont(new java.awt.Font("Tahoma", 1, 12));
                            l6.setForeground(Color.DARK_GRAY);
                            l6.setText("  Mark Primary_Key(NOT NULL)(Not More Then One Time)");
                            JLabel l7 = new JLabel();
                            l7.setFont(new java.awt.Font("Tahoma", 1, 12));
                            l7.setForeground(Color.DARK_GRAY);
                            l7.setText(" Mark Auto_Inc for Autometic Increment(Optional)");
                            c.gridx = 0;
                            c.gridy = 0;
                            jp.add(l5, c);
                            c.gridx = 1;
                            c.gridy = 0;
                            jp.add(l6, c);
                            c.gridx = 0;
                            c.gridy = 1;
                            jp.add(l7, c);
                            ctr++;
                            initree();
                        } else if (ctr == 1) {
                            ctr++;
                            for (int i = 0; i < jTable2.getRowCount(); i++) {
                                if (i == 0) {
                                    if (jTable2.getValueAt(0, 2) == "true") {
                                        if (jTable2.getValueAt(0, 3) == "true") {
                                            attr = "NOT NULL PRIMARY KEY AUTO_INCREMENT";
                                        } else {
                                            attr = "NOT NULL PRIMARY KEY";
                                        }
                                    } else {
                                        if (jTable2.getValueAt(0, 3) == "true") {
                                            attr = "AUTO_INCREMENT";
                                        } else {
                                            attr = "NULL";
                                        }
                                    }
                                    q.createTb(dbcombo.getSelectedItem().toString(), jtf2.getText(), jTable2.getValueAt(0, 0).toString(), jTable2.getValueAt(0, 1).toString(), attr);
                                } else {
                                    if (jTable2.getValueAt(i, 2) == "true") {
                                        if (jTable2.getValueAt(i, 3) == "true") {
                                            attr = "NOT NULL PRIMARY KEY AUTO_INCREMENT";
                                        } else {
                                            attr = "NOT NULL PRIMARY KEY";
                                        }
                                    } else {
                                        if (jTable2.getValueAt(i, 3) == "true") {
                                            attr = "AUTO_INCREMENT";
                                        } else {
                                            attr = "NULL";
                                        }
                                    }
                                    q.alterTb(jtf2.getText(), jTable2.getValueAt(i, 0).toString(), jTable2.getValueAt(i, 1).toString(), attr);
                                }
                            }
                            initree();
                        } else {
                            String s = dbcombo.getSelectedItem().toString() + "." + jtf2.getText();
                            System.out.println(s);
                            tbm = q.table(s);
                            jTable1.setModel(tbm);
                            jp1.setName("Show Table");
                            jtp1.removeAll();
                            jtp1.add(jp1);
                            jtp1.setVisible(true);
                            if (jTable1.getRowCount() > 0 || jTable1.getColumnCount() > 1) {
                                jButton21.setVisible(true);
                                jButton23.setVisible(true);
                            } else {
                                jButton21.setVisible(false);
                                jButton23.setVisible(false);
                            }
                            jtp1.repaint();
                            initree();
                        }
                    }
//                    initree();
//                    tree.revalidate();
                    tree.repaint();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtf1.setText("");
                jtf3.setText("");
                jtf2.setText("");
                initree();
                dbcombo.repaint();
                tbcombo.repaint();
            }
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Action canceled...");
                jtf1.setText("");
                jtf2.setText("");
                jtf3.setText("");
//                jp.removeAll();
                jp.repaint();
                initree();
            }
        });
        dbcombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vt = q.showtb(dbcombo.getSelectedItem().toString());
                tbcombo.removeAll();
                jp.repaint();
                for (int i = 0; i < vt.size(); i++) {
                    rstn = vt.get(i).toString();
                    tbcombo.addItem(rstn);
                }
                initree();
            }
        });

        try {
            if (q.con.isClosed() == false) {
                jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Connect.png")));
                jToggleButton1.setText("CONNECTED with DataBase");
            } else {
                jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Disconnect.png")));
                jToggleButton1.setText("DISCONNECTED with DataBase");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar2 = new javax.swing.JToolBar();
        jToggleButton1 = new javax.swing.JToggleButton();
        jInternalFrame4 = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtp1 = new javax.swing.JTabbedPane();
        jp1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton23 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jtab = new javax.swing.JTabbedPane();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tree = new JTree();
        tree.setModel(model0);
        root.add(create);
        root.add(show);
        root.add(delete);

        create.add(createdb);
        create.add(createtb);
        show.add(database);
        show.add(table);
        delete.add(deletedb);
        delete.add(deletetb);

        tree.setShowsRootHandles(true); 
        tree.setEditable(false);            
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jScrollPane5 = new javax.swing.JScrollPane();
        jInternalFrame5 = new javax.swing.JInternalFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jInternalFrame6 = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem42 = new javax.swing.JMenuItem();
        jMenuItem43 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem44 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem37 = new javax.swing.JMenuItem();
        jMenuItem38 = new javax.swing.JMenuItem();
        jMenuItem39 = new javax.swing.JMenuItem();
        jMenuItem47 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem40 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem45 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem50 = new javax.swing.JMenuItem();
        jMenuItem49 = new javax.swing.JMenuItem();
        jMenuItem53 = new javax.swing.JMenuItem();
        jMenuItem48 = new javax.swing.JMenuItem();
        jMenuItem51 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Code Designer");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);

        jToolBar2.setBorder(null);
        jToolBar2.setRollover(true);

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(102, 102, 102));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Disconnect.png"))); // NOI18N
        jToggleButton1.setText("CONNECTED WITH DATABASE");
        jToggleButton1.setFocusable(false);
        jToggleButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToggleButton1.setIconTextGap(1);
        jToggleButton1.setOpaque(true);
        jToggleButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jToggleButton1);

        jInternalFrame4.setClosable(true);
        jInternalFrame4.setIconifiable(true);
        jInternalFrame4.setMaximizable(true);
        jInternalFrame4.setResizable(true);
        jInternalFrame4.setTitle("Tools Box");
        jInternalFrame4.setAutoscrolls(true);
        jInternalFrame4.setVisible(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon1.png"))); // NOI18N
        jLabel1.setText("Start");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon2.PNG"))); // NOI18N
        jLabel2.setText("I/O");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon3.png"))); // NOI18N
        jLabel3.setText("Process");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icon4.png"))); // NOI18N
        jLabel4.setText("Decision");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addContainerGap(590, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel4);

        javax.swing.GroupLayout jInternalFrame4Layout = new javax.swing.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Database Design");
        jInternalFrame1.setToolTipText("Drop,Dragged Component  here");
        jInternalFrame1.setVisible(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jTable1.setDragEnabled(true);
        jTable1.setName(""); // NOI18N
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jButton23.setText("Delete Column");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton20.setText("Insert Row");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setText("Delete Row");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setText("Insert Column");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton24.setText("Update");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp1Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton20)
                    .addComponent(jButton22)
                    .addComponent(jButton21)
                    .addComponent(jButton23)
                    .addComponent(jButton24)))
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jp1Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 405, Short.MAX_VALUE))
        );

        jtp1.addTab("tab1", jp1);

        jScrollPane3.setViewportView(jtp1);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jInternalFrame3.setClosable(true);
        jInternalFrame3.setIconifiable(true);
        jInternalFrame3.setMaximizable(true);
        jInternalFrame3.setResizable(true);
        jInternalFrame3.setTitle("Flowchart");
        jInternalFrame3.setVisible(true);

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtab, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtab)
        );

        jInternalFrame2.setClosable(true);
        jInternalFrame2.setIconifiable(true);
        jInternalFrame2.setMaximizable(true);
        jInternalFrame2.setResizable(true);
        jInternalFrame2.setTitle("Project Explorer");
        jInternalFrame2.setVisible(true);

        tree.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tree.setToolTipText("");
        tree.setAutoscrolls(true);
        tree.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tree.setDragEnabled(true);
        tree.setEditable(true);
        tree.setLargeModel(true);
        tree.setName("tree"); // NOI18N
        tree.setNextFocusableComponent(jInternalFrame1);
        tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                treeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(tree);
        tree.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
            .addComponent(jScrollPane1)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
        );

        jInternalFrame5.setClosable(true);
        jInternalFrame5.setIconifiable(true);
        jInternalFrame5.setMaximizable(true);
        jInternalFrame5.setResizable(true);
        jInternalFrame5.setTitle("Output Window");
        jInternalFrame5.setAutoscrolls(true);
        jInternalFrame5.setVisible(true);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        javax.swing.GroupLayout jInternalFrame5Layout = new javax.swing.GroupLayout(jInternalFrame5.getContentPane());
        jInternalFrame5.getContentPane().setLayout(jInternalFrame5Layout);
        jInternalFrame5Layout.setHorizontalGroup(
            jInternalFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jInternalFrame5Layout.setVerticalGroup(
            jInternalFrame5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );

        jInternalFrame6.setClosable(true);
        jInternalFrame6.setIconifiable(true);
        jInternalFrame6.setMaximizable(true);
        jInternalFrame6.setResizable(true);
        jInternalFrame6.setTitle("Desktop sharing");
        jInternalFrame6.setVisible(true);

        jButton1.setText("Start Server");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Run Client");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout jInternalFrame6Layout = new javax.swing.GroupLayout(jInternalFrame6.getContentPane());
        jInternalFrame6.getContentPane().setLayout(jInternalFrame6Layout);
        jInternalFrame6Layout.setHorizontalGroup(
            jInternalFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame6Layout.setVerticalGroup(
            jInternalFrame6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseClicked(evt);
            }
        });

        jMenu1.setMnemonic('F');
        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setMnemonic('N');
        jMenuItem1.setText("New Project");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator6);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setMnemonic('O');
        jMenuItem2.setText("Open Project");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem42.setText("Open File");
        jMenu1.add(jMenuItem42);

        jMenuItem43.setText("Close Project");
        jMenu1.add(jMenuItem43);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setMnemonic('S');
        jMenuItem3.setText("Save");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setMnemonic('S');
        jMenuItem4.setText("Save As");
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem6.setMnemonic('a');
        jMenuItem6.setText("Page Preview");
        jMenu1.add(jMenuItem6);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setMnemonic('P');
        jMenuItem5.setText("Print");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setMnemonic('x');
        jMenuItem7.setText("Exit");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setMnemonic('E');
        jMenu2.setText("Edit");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setMnemonic('u');
        jMenuItem8.setText("Undo");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem44.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem44.setText("Redo");
        jMenu2.add(jMenuItem44);
        jMenu2.add(jSeparator3);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setMnemonic('C');
        jMenuItem9.setText("Cut");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setMnemonic('o');
        jMenuItem10.setText("Copy");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setMnemonic('p');
        jMenuItem11.setText("Paste");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItem12.setMnemonic('D');
        jMenuItem12.setText("Delete");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);
        jMenu2.add(jSeparator4);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setMnemonic('F');
        jMenuItem13.setText("Find");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem14.setMnemonic('N');
        jMenuItem14.setText("Find Next");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setMnemonic('R');
        jMenuItem15.setText("Replace");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setMnemonic('G');
        jMenuItem16.setText("Go to");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);
        jMenu2.add(jSeparator5);

        jMenu3.setMnemonic('o');
        jMenu3.setText("Format");

        jCheckBoxMenuItem1.setText("Word Wrap");
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jCheckBoxMenuItem1);

        jMenuItem20.setText("Font...");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem20);

        jMenu2.add(jMenu3);

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem17.setMnemonic('A');
        jMenuItem17.setText("Select All");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem18.setMnemonic('D');
        jMenuItem18.setText("Date/Tile");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuBar1.add(jMenu2);

        jMenu4.setMnemonic('V');
        jMenu4.setText("View");
        jMenu4.add(jSeparator7);

        jMenu8.setText("Toolbar");

        jMenuItem37.setText("File Tools");
        jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem37ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem37);

        jMenuItem38.setText("Edit Tools");
        jMenu8.add(jMenuItem38);

        jMenuItem39.setText("Clip Board");
        jMenu8.add(jMenuItem39);

        jMenuItem47.setText("Web Services Tools");
        jMenu8.add(jMenuItem47);

        jMenu4.add(jMenu8);
        jMenu4.add(jSeparator8);

        jMenuItem40.setText("Line Numbers");
        jMenu4.add(jMenuItem40);

        jCheckBoxMenuItem2.setText("Status Bar");
        jMenu4.add(jCheckBoxMenuItem2);
        jMenu4.add(jSeparator9);

        jMenuItem45.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem45.setText("Full Screen");
        jMenu4.add(jMenuItem45);

        jMenuBar1.add(jMenu4);

        jMenu9.setText("Window");

        jMenuItem50.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem50.setText("Project Explorer Window");
        jMenuItem50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem50ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem50);

        jMenuItem49.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem49.setText("Tools Box");
        jMenuItem49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem49ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem49);

        jMenuItem53.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem53.setText("Flowchart Window");
        jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem53ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem53);

        jMenuItem48.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem48.setText("Database Window");
        jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem48ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem48);

        jMenuItem51.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem51.setText("Chat Window");
        jMenu9.add(jMenuItem51);

        jMenuItem36.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem36.setText("Screen Share Window");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem36);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setText("Output Window");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem19);

        jMenuBar1.add(jMenu9);

        jMenu6.setText("Tools");

        jMenuItem29.setText("New Registration");
        jMenu6.add(jMenuItem29);

        jMenuItem28.setText("User Login");
        jMenu6.add(jMenuItem28);
        jMenu6.add(jSeparator10);

        jMenuItem31.setText("Chat Service");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem31);

        jMenuItem32.setText("Screen Share");
        jMenu6.add(jMenuItem32);

        jMenuBar1.add(jMenu6);

        jMenu5.setText("Help");

        jMenuItem22.setText("Help Contents");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);
        jMenu5.add(jSeparator11);

        jMenuItem26.setText("Online Support");
        jMenu5.add(jMenuItem26);

        jMenuItem21.setText("Check For Updates");
        jMenuItem21.setActionCommand("Check for Updates");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem21);
        jMenu5.add(jSeparator12);

        jMenuItem25.setText("Start Page");
        jMenu5.add(jMenuItem25);

        jMenuItem27.setText("Shortcuts");
        jMenu5.add(jMenuItem27);
        jMenu5.add(jSeparator13);

        jMenuItem23.setText("About Notepad");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem23);

        jMenuItem24.setText("About");
        jMenu5.add(jMenuItem24);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jInternalFrame4)
                    .addComponent(jInternalFrame2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame1)
                    .addComponent(jInternalFrame5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame3)
                    .addComponent(jInternalFrame6)))
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInternalFrame1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInternalFrame5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInternalFrame4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jInternalFrame3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jInternalFrame6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void initree() {
        DefaultMutableTreeNode tempnodetb;
        try {
            database.removeAllChildren();
            table.removeAllChildren();
            for (int i = 0; i < vd.size(); i++) {
                rsdn = null;
                rsdn = (String) vd.get(i);
                DefaultMutableTreeNode tempnodedb = new DefaultMutableTreeNode(rsdn);
                database.add(tempnodedb);
                vt = q.showtb(rsdn);
                if (vt.size() >= 0) {
                    for (int j = 0; j < vt.size(); j++) {
                        rstn = (String) vt.get(j);
                        tempnodetb = new DefaultMutableTreeNode(rstn);
                        tempnodedb.add(tempnodetb);
                        tablenode = new DefaultMutableTreeNode(rsdn + "." + rstn);
                        table.add(tablenode);
                    }
                }
                vt.clear();
                model0.reload(root);
                tree.repaint();
                jInternalFrame2.repaint();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void treeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_treeValueChanged
        try {
            tree.repaint();
            DefaultMutableTreeNode selectednode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            String nodetname = selectednode.toString();
            if (selectednode.isLeaf()) {
                DefaultMutableTreeNode parentnode = (DefaultMutableTreeNode) selectednode.getParent();
                String nodedname = parentnode.toString();
                if (nodetname.toString() == "Create_DataBase") {
                    jtp1.removeAll();
                    jp.removeAll();
                    jp.setName("Create_DataBase");
                    jp.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    l5.setFont(new java.awt.Font("Tahoma", 1, 12));
                    l5.setForeground(Color.DARK_GRAY);
                    l5.setText(" /* Enter Any Name of your DATABASE */");
                    c.gridx = 0;
                    c.gridy = 0;
                    jp.add(l1, c);
                    c.gridx = 1;
                    c.gridy = 0;
                    jp.add(jtf1, c);
                    c.gridx = 0;
                    c.gridy = 1;
                    jp.add(save, c);
                    c.gridx = 1;
                    c.gridy = 1;
                    jp.add(cancel, c);
                    c.gridx = 1;
                    c.gridy = 2;
                    jp.add(l5, c);
                    jtp1.add(jp);
                    jtp1.setVisible(true);
                    jtp1.repaint();
                } else if (nodetname.toString() == "Create_Table") {
                    jtp1.removeAll();
                    jp.removeAll();
                    jp.setName("Create_Table");
                    jp.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 0;
                    jp.add(l1, c);
                    c.gridx = 1;
                    c.gridy = 0;
                    jp.add(dbcombo, c);
                    c.gridx = 0;
                    c.gridy = 1;
                    jp.add(l2, c);
                    c.gridx = 1;
                    c.gridy = 1;
                    jp.add(jtf2, c);
                    c.gridx = 0;
                    c.gridy = 2;
                    jp.add(l3, c);
                    c.gridx = 1;
                    c.gridy = 2;
                    jp.add(jtf3, c);
                    c.gridx = 0;
                    c.gridy = 3;
                    jp.add(save, c);
                    c.gridx = 1;
                    c.gridy = 3;
                    jp.add(reset, c);
                    c.gridx = 2;
                    c.gridy = 3;
                    jp.add(cancel, c);
                    l5.setFont(new java.awt.Font("Tahoma", 1, 12));
                    l5.setForeground(Color.DARK_GRAY);
                    l5.setText("/* Choose Database in which you want to create Table");
                    JLabel l6 = new JLabel();
                    l6.setFont(new java.awt.Font("Tahoma", 1, 12));
                    l6.setForeground(Color.DARK_GRAY);
                    l6.setText("   Type valid TABLE_Name");
                    JLabel l7 = new JLabel();
                    l7.setFont(new java.awt.Font("Tahoma", 1, 12));
                    l7.setForeground(Color.DARK_GRAY);
                    l7.setText("   Enter Columnn_No and press OK to further process..*/");
                    c.gridx = 0;
                    c.gridy = 4;
                    jp.add(l5, c);
                    c.gridx = 1;
                    c.gridy = 4;
                    jp.add(l6, c);
                    c.gridx = 2;
                    c.gridy = 4;
                    jp.add(l7, c);
                    jtp1.add(jp);
                    jtp1.setVisible(true);
                    jtp1.repaint();
                } else if (nodetname.toString() == "Delete_Table") {
                    jtp1.removeAll();
                    jp.removeAll();
                    jp.setName("Delete_Table");
                    jp.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 0;
                    jp.add(l1, c);
                    c.gridx = 1;
                    c.gridy = 0;
                    jp.add(dbcombo, c);
                    c.gridx = 0;
                    c.gridy = 1;
                    jp.add(l2, c);
                    c.gridx = 1;
                    c.gridy = 1;
                    jp.add(tbcombo, c);
                    c.gridx = 0;
                    c.gridy = 2;
                    jp.add(save, c);
                    c.gridx = 1;
                    c.gridy = 2;
                    jp.add(cancel, c);
                    l5.setFont(new java.awt.Font("Tahoma", 1, 12));
                    l5.setForeground(Color.DARK_GRAY);
                    l5.setText("/*NOTE:--Choose DataBase and corresponding ");
                    JLabel l6 = new JLabel();
                    l6.setText("  Table name which you want to delete*/");
                    c.gridx = 0;
                    c.gridy = 3;
                    jp.add(l5, c);
                    c.gridx = 1;
                    c.gridy = 3;
                    jp.add(l6, c);
                    jtp1.add(jp);
                    jtp1.setVisible(true);
                    jtp1.repaint();
                } else if (nodetname.toString() == "Delete_DataBase") {
                    jtp1.removeAll();
                    jp.removeAll();
                    jp.setName("Delete_DataBase");
                    jp.setLayout(new GridBagLayout());
                    GridBagConstraints c = new GridBagConstraints();
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 0;
                    jp.add(l1, c);
                    c.gridx = 1;
                    c.gridy = 0;
                    jp.add(dbcombo, c);
                    c.gridx = 0;
                    c.gridy = 1;
                    jp.add(save, c);
                    c.gridx = 1;
                    c.gridy = 1;
                    jp.add(cancel, c);
                    l5.setText("//Choose DataBase_Name to Delete ");
                    c.gridx = 0;
                    c.gridy = 2;
                    jp.add(l5, c);
                    jtp1.add(jp);
                    jtp1.setVisible(true);
                    jtp1.repaint();
                } else if (nodedname == "Table") {
                    jtp1.removeAll();
                    tbm = q.table(nodetname);
                    rc = tbm.getRowCount();
                    cc = tbm.getColumnCount();
                    jTable1.setModel(tbm);
                    jTable1.setName(nodedname + "." + nodetname);
                    jp1.setName("Show Table");
                    jtp1.add(jp1);
                    if (jTable1.getRowCount() > 0 || jTable1.getColumnCount() > 1) {
                        jButton21.setVisible(true);
                        jButton23.setVisible(true);
                    } else {
                        jButton21.setVisible(false);
                        jButton23.setVisible(false);
                    }
                    jtp1.setVisible(true);
                    jtp1.repaint();
                } else {
                    String s = nodedname + "." + nodetname;
                    jtp1.removeAll();
                    tbm = q.table(s);
                    jTable1.setModel(tbm);
                    rc = tbm.getRowCount();
                    cc = tbm.getColumnCount();
                    jTable1.setName(s);
                    jp1.setName("Show Table");
                    jtp1.add(jp1);
                    if (jTable1.getRowCount() > 0 || jTable1.getColumnCount() > 1) {
                        jButton21.setVisible(true);
                        jButton23.setVisible(true);
                    } else {
                        jButton21.setVisible(false);
                        jButton23.setVisible(false);
                    }
                    jtp1.setVisible(true);
                    jtp1.repaint();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_treeValueChanged

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        TableColumn TableColumn1 = jTable1.getColumnModel().getColumn(jTable1.getSelectedColumn());
        int w = TableColumn1.getModelIndex();
        System.out.println(tbm.getColumnName(jTable1.getSelectedColumn()));
        try {
            q.deletecol(jTable1.getName(), tbm.getColumnName(jTable1.getSelectedColumn()));
        } catch (SQLException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.removeColumn(TableColumn1);
        if (jTable1.getColumnCount() == 1) {
            jButton23.setVisible(false);
        }
        JOptionPane.showMessageDialog(null, "Column Deleted.....");
        initree();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        tbm.addRow(new Object[]{});
        jButton21.setVisible(true);
        initree();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        int selRow = jTable1.getSelectedRow();
        try {
            q.delete(jTable1.getName(), jTable1.getColumnName(0), Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()));
        } catch (SQLException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
        tbm.removeRow(selRow);
        JOptionPane.showMessageDialog(null, "Row Deleted.....");
        if (jTable1.getRowCount() == 0) {
            jButton21.setVisible(false);
        }
        initree();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        String dname = JOptionPane.showInputDialog(null, "ENTER FIELD NAME", "TABLE HEADER");
        String datatype = JOptionPane.showInputDialog(null, "ENTER DataType with Size of the FIELD [Ex.= NUMBER(20)] ");
        JTableHeader th = jTable1.getTableHeader();
        tbm.addColumn(dname);
        th.repaint();
        jTable1.repaint();
        int newcc = 0, dc = 0;
        newcc = tbm.getColumnCount();
        try {
            q.insertcol(jTable1.getName(), dname, datatype);
        } catch (SQLException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
        initree();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped

    }//GEN-LAST:event_jTable1KeyTyped

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        int actionDialog = chooser.showOpenDialog(this);
        if (actionDialog == JFileChooser.APPROVE_OPTION) {
            File fileName = new File(chooser.getSelectedFile() + "");
            if (fileName == null) {
                return;
            }
            if (fileName.exists()) {
                actionDialog = JOptionPane.showConfirmDialog(this, "Replace existing file?");
                if (actionDialog == JOptionPane.NO_OPTION) {
                    return;
                }
            }
            try {
                String strLine;
                File f = chooser.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(f));
                while ((strLine = br.readLine()) != null) {
                    //                    jTextArea1.append(strLine + "\n");
                    System.out.println(strLine);
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        //        JFileChooser fc=new JFileChooser();
        //        if(fc.showSaveDialog(jDialog2)==JFileChooser.APPROVE_OPTION);
        //        {
        //            //            String s=jTextArea1.getText();
        //
        //        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        //        Document doc = jTextArea1.getDocument();
        //        final UndoManager undo = new UndoManager();
        //        doc.addUndoableEditListener(new UndoableEditListener() {
        //
        //            public void undoableEditHappened(UndoableEditEvent e) {
        //                undo.addEdit(e.getEdit());
        //            }});
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        //        jTextArea1.cut();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        //        jTextArea1.copy();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        //        jTextArea1.paste();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // jTextArea1.remove();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        //        String s=jTextArea1.getText();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        // if(jCheckBoxMenuItem1.{
        //            jTextArea1.setWrapStyleWord(true);
    }//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem37ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem37ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        if (jInternalFrame3.isVisible() == true) {
            jInternalFrame3.setVisible(false);
        } else {
            jInternalFrame3.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1MouseClicked

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            if (evt.getActionCommand() == "DISCONNECTED with DataBase") {
                q.openconn();
                jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Connect.png")));
                jToggleButton1.setText("CONNECTED with DataBase");
            } else {
                q.con.close();
                jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Disconnect.png")));
                jToggleButton1.setText("DISCONNECTED with DataBase");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
        initree();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if (jTable1.getRowCount() > rc) {
            for (int i = rc; i < jTable1.getRowCount(); i++) {
                for (int j = 0; j < jTable1.getColumnCount(); j++) {
                    if (j == 0) {
                        try {
                            q.insertrow(jTable1.getName(), jTable1.getColumnName(j), Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
                        } catch (SQLException ex) {
                            //                        Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            q.update(jTable1.getName(), jTable1.getColumnName(j), jTable1.getValueAt(i, j).toString(), jTable1.getColumnName(0), jTable1.getValueAt(i, 0).toString());
                            //                            q.update(jTable1.getName(),jTable1.getColumnName(j),Integer.parseInt(jTable1.getValueAt(i,j).toString()),jTable1.getColumnName(0),jTable1.getValueAt(i,0).toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            JOptionPane.showMessageDialog(null, " Row(s) Updated..");
        } else {
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                for (int j = 1; j < jTable1.getColumnCount(); j++) {
                    try {
                        q.update(jTable1.getName(), jTable1.getColumnName(j), jTable1.getValueAt(i, j).toString(), jTable1.getColumnName(0), Integer.parseInt(jTable1.getValueAt(i, 0).toString()));
                        //q.update(jTable1.getName(),jTable1.getColumnName(j),Integer.parseInt(jTable1.getValueAt(i,j).toString()),jTable1.getColumnName(0),jTable1.getValueAt(i,0).toString());
                    } catch (SQLException ex) {
                        //Logger.getLogger(CodeMaker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Updated.....");
        }
        initree();
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        if (jInternalFrame4.isVisible() == true) {
            jInternalFrame4.setVisible(false);
        } else {
            jInternalFrame4.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        if (jInternalFrame1.isVisible() == true) {
            jInternalFrame1.setVisible(false);
        } else {
            jInternalFrame1.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem50ActionPerformed
        if (jInternalFrame2.isVisible() == true) {
            jInternalFrame2.setVisible(false);
        } else {
            jInternalFrame2.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem50ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        if (jInternalFrame6.isVisible() == true) {
            jInternalFrame6.setVisible(false);
        } else {
            jInternalFrame6.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        if (jInternalFrame5.isVisible() == true) {
            jInternalFrame5.setVisible(false);
        } else {
            jInternalFrame5.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        final JTextField jtf1 = new JTextField(150);
        final JTextField jtf2 = new JTextField(150);
        final JTextField jtf3 = new JTextField(150);
        jtf3.setEditable(false);
        jtf2.setText("C:\\Users\\SOHAL\\Documents\\Code Designer");
        JButton jb1 = new JButton("Browse");
        final JButton jb2 = new JButton("OK");
        JButton jb3 = new JButton("Cancel");
        final JLabel lbl4 = new JLabel("Fill all two fields if it is empty");
        final JDialog d = new JDialog(this, "New Project");
        d.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        d.add(new JLabel("Project Name :"), c);
        c.gridx = 1;
        c.gridy = 0;
        d.add(jtf1, c);
        c.gridx = 0;
        c.gridy = 1;
        d.add(new JLabel("Project Location :"), c);
        c.gridx = 1;
        c.gridy = 1;
        d.add(jtf2, c);
        c.gridx = 2;
        c.gridy = 1;
        d.add(jb1, c);
        c.gridx = 0;
        c.gridy = 2;
        d.add(new JLabel("Project Folder :"), c);
        c.gridx = 1;
        c.gridy = 2;
        d.add(jtf3, c);
        c.gridx = 0;
        c.gridy = 3;
        d.add(jb2, c);
        //jb2.setEnabled(false);
        c.gridx = 1;
        c.gridy = 3;
        d.add(jb3, c);
        c.gridx = 1;
        c.gridy = 4;
        d.add(lbl4, c);
        d.setSize(400, 200);
        d.setVisible(true);
        d.setLocation(300, 200);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("C:\\Users\\SOHAL\\Documents\\Code Designer");
                chooser.setApproveButtonText("Select");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                int a = chooser.showOpenDialog(d);
                if (a != JFileChooser.APPROVE_OPTION) {
                    chooser = null;
                    return;
                } else {
                    jtf2.setText(chooser.getSelectedFile().getPath().toString());
                    jtf3.setText(jtf2.getText() + "\\" + jtf1.getText());
                    jb2.setEnabled(true);
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File(jtf3.getText());
                f.mkdir();
                jInternalFrame2.setVisible(true);
                jInternalFrame3.setVisible(true);
                jInternalFrame4.setVisible(true);

                DefaultMutableTreeNode fileroot = new DefaultMutableTreeNode(jtf1.getText());
                DefaultMutableTreeNode flowchart = new DefaultMutableTreeNode(jtf1.getText() + ".flw");
                DefaultMutableTreeNode classfile = new DefaultMutableTreeNode(jtf1.getText() + ".java");
                fileroot.add(flowchart);
                fileroot.add(classfile);
                DefaultTreeModel model = new DefaultTreeModel(fileroot);
                jTree1 = new JTree(model);
                model.reload(fileroot);
                jScrollPane5.add(jTree1);
                jScrollPane5.removeAll();
                jTree1.setShowsRootHandles(true);
                jTree1.setEditable(false);
                jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
                jTree1.expandRow(0);
                jTree1.setEnabled(true);
                jTree1.setRootVisible(true);
                jTree1.setVisible(true);
                if (jtf1.getText() != null && jtf2.getText() != null && jtf3.getText() != null) {
                    d.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(d, "Please Fill all Text Fields...");
                }
            }
        });
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });
        jtf1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jtf3.setText(jtf2.getText() + "\\" + jtf1.getText());
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Runtime rt = Runtime.getRuntime();
        try {
            Process p1 = rt.exec("d:\\start.cmd");
            JOptionPane.showMessageDialog(this, "Server is Ready");
        } catch (IOException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }

//            server.ServerFrame sf=new server.ServerFrame();
//            sf.setVisible(true);
//            sf.setAlwaysOnTop(true);
//        try {
//            DesktopClient d=new DesktopClient();
//        } catch (NamingException ex) {
//            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        com_ui.ChatFrame cf=new com_ui.ChatFrame();
//        cf.setVisible(true);
//        cf.setAlwaysOnTop(true);
        Runtime rt1 = Runtime.getRuntime();
        try {
            Process p11 = rt1.exec("d:\\client.cmd");
        } catch (IOException ex) {
            Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

//    public class DesktopClient 
//    {
//        class ImageThreader extends Thread
//        {
//            public void run()
//            {
//                while(true)
//                {
//                    try
//                    {
//                        byte[] data = obj.getDesktop();
//                        InputStream in = new ByteArrayInputStream(data);
//                        BufferedImage img = ImageIO.read(in);
//                        setIcon(img);
//                       // Thread.sleep(100);
//                    }
//                    catch(Exception ex)
//                    {
//                        System.out.println(ex);
//                    }
//                }
//            }
//        }
//
//        private JLabel label;
//        private IDesktop obj;
//        public DesktopClient()throws RemoteException,NamingException,IOException
//        {
//            label=new JLabel();
//            jInternalFrame6.remove(jPanel1);
//            jInternalFrame6.add(label);
//            InitialContext cnt= new InitialContext();
//            obj=(IDesktop)cnt.lookup("rmi://192.168.0.1/desktop");
//            ImageThreader th=new ImageThreader();
//            th.start();
//        }
//        public void setIcon(BufferedImage img)
//        {
//            label.setIcon(new ImageIcon(img));
//        }
//    }
    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CodeDesigner().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(CodeDesigner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JButton jButton20;
    public javax.swing.JButton jButton21;
    public javax.swing.JButton jButton22;
    public javax.swing.JButton jButton23;
    public javax.swing.JButton jButton24;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    public javax.swing.JInternalFrame jInternalFrame1;
    public javax.swing.JInternalFrame jInternalFrame2;
    public javax.swing.JInternalFrame jInternalFrame3;
    public javax.swing.JInternalFrame jInternalFrame4;
    private javax.swing.JInternalFrame jInternalFrame5;
    public javax.swing.JInternalFrame jInternalFrame6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    public javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    public javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    public javax.swing.JToolBar jToolBar2;
    public javax.swing.JPanel jp1;
    public javax.swing.JTabbedPane jtab;
    public javax.swing.JTabbedPane jtp1;
    public javax.swing.JTree tree;
    // End of variables declaration//GEN-END:variables

//    public void copytoclip(JLabel jl)
//    {
//        String selection = jl.getText();
//        StringSelection data = new StringSelection(selection);
//        clipboard.setContents(data, data);
//    }
    class drawingPanel extends JPanel {

        drawingPanel() {
            this.setBackground(Color.white);
            this.setLayout(null);
            this.addMouseMotionListener(new MymouseListener());
        }

        @Override
        public void paint(Graphics g) {
            super.paintComponents(g);
        }
    }

    public class MymouseListener implements MouseMotionListener {

        @Override
        public void mouseMoved(MouseEvent e) {
            mousex = e.getX();
            mousey = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }
    }

    class codePanel extends JPanel {

        codePanel() {
            editor = new JEditorPane();
            this.setBackground(Color.white);
            this.setLayout(null);
            editor.setBounds(0, 0, 1000, 600);
            this.add(editor);
        }
    }
    Object ob[] = new Object[2];

    class mylistener implements DragGestureListener, DragSourceListener, DropTargetListener, Transferable {

        JPanel dsl, dtl;
        DropTarget dropTarget;
        Object tx[] = new Object[2];

        public mylistener(JPanel p1, JPanel p2) {
            dsl = p1;
            dtl = p2;
            if (dsl != dtl) {
                dropTarget = new DropTarget(dtl, DnDConstants.ACTION_COPY, this, true, null);
            } else {
                dropTarget = new DropTarget(dtl, DnDConstants.ACTION_MOVE, this, true, null);
            }
        }

        @Override
        public void dragGestureRecognized(DragGestureEvent ev) {
            JLabel label = (JLabel) ev.getComponent();
            final Icon ico = label.getIcon();
            final String lblname = label.getText();
            Transferable transferable = new Transferable() {
                @Override
                public DataFlavor[] getTransferDataFlavors() {
                    return supportedFlavors;
                }

                @Override
                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return isDataFlavorSupported(flavor);
                }

                @Override
                public Object[] getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                    tx[0] = ico;
                    tx[1] = lblname;
                    return tx;
                }
            };
            ev.startDrag(null, transferable);
        }

        @Override
        public void dragEnter(DragSourceDragEvent dsde) {
        }

        @Override
        public void dragOver(DragSourceDragEvent ev) {
            object = ev.getSource();
        }

        @Override
        public void dropActionChanged(DragSourceDragEvent dsde) {
        }

        @Override
        public void dragExit(DragSourceEvent dse) {
        }

        @Override
        public void dragDropEnd(DragSourceDropEvent dsde) {
        }

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragOver(DropTargetDragEvent ev) {
            dropTargetDrag(ev);
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
        }

        @Override
        public void drop(DropTargetDropEvent event) {
            drawingpanel.remove(dpl);
            drawingpanel.remove(dpl2);
            try {
                DropTarget test = (DropTarget) event.getSource();
                Component ca = (Component) test.getComponent();
                Point dropPoint = ca.getMousePosition();
                Transferable tr = event.getTransferable();

                if (event.isDataFlavorSupported(DataFlavor.imageFlavor) && event.isDataFlavorSupported(new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType))) {
                    ob = (Object[]) tr.getTransferData(DataFlavor.imageFlavor);
                    Icon ico = (Icon) ob[0];
                    String s = (String) ob[1];
                    if (ico != null) {
                        JLabel jl = new JLabel(ico);
                        Draggable dl = new Draggable(jl, dropPoint.x, dropPoint.y);
                        dl.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                        dtl.add(dl);
                        generator.generate(s);
                        dtl.revalidate();
                        dtl.repaint();
                    }
                } else {
                    event.rejectDrop();
                }
            } catch (HeadlessException | ClassNotFoundException | UnsupportedFlavorException | IOException ex) {
            }
            event.dropComplete(true);
            jInternalFrame3.repaint();
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return supportedFlavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
                return object;
            } else {
                return null;
            }
        }

        void dropTargetDrag(DropTargetDragEvent ev) {
            ev.acceptDrag(ev.getDropAction());
        }
    }

    class Generator {

        String s1, s2, s, m = "";

        public void generate(String command) {
            s1 = "  class Test{  \n "
                    + "  public static void main(String [] a){  \n";
            s2 = "   }\n"
                    + "}"
                    + "";
            switch (command) {
                case "Start":
                    m += " System.out.println(); \n";
                    s = s1 + m + s2;
                    editor.setText(s);
                    break;
                case "Decision":
                    m += "if \n" + "{ \n" + "} \n" + "else \n" + "{ \n" + "} \n";
                    s = s1 + m + s2;
                    editor.setText(s);
                    break;
                case "Process":
                    m += "for(int i=0;i<100;i++)\n" + "{\n" + "//block of codes\n" + "}";
                    s = s1 + m + s2;
                    editor.setText(s);
                    break;
                case "I/O":
                    m += "Object o[];\n" + "int i;\n" + "String s;";
                    s = s1 + m + s2;
                    editor.setText(s);
                    break;
            }
        }
    }

    public class Draggable extends JComponent {

        private Point pointPressed;
        private final JComponent draggable;

        public Draggable(final JComponent component, final int x, final int y) {
            draggable = component;
            //        draggable.setCursor(draggable.getCursor());  
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setLocation(x, y);
            setSize(component.getPreferredSize());
            setLayout(new BorderLayout());
            add(component);
            MouseInputAdapter mouseAdapter = new MouseHandler();
            addMouseMotionListener(mouseAdapter);
            addMouseListener(mouseAdapter);
        }

        @Override
        public void setBorder(final Border border) {
            super.setBorder(border);
            if (border != null) {
                Dimension size = draggable.getPreferredSize();
                Insets insets = border.getBorderInsets(this);
                size.width += (insets.left + insets.right + 5);
                size.height += (insets.top + insets.bottom);
                setSize(size);
            }
        }

        private class MouseHandler extends MouseInputAdapter {

            @Override
            public void mouseDragged(final MouseEvent e) {
                Point pointDragged = e.getPoint();
                Point loc = getLocation();
                loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
                setLocation(loc);
                jInternalFrame3.repaint();
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                pointPressed = e.getPoint();
            }
        }
    }
}
