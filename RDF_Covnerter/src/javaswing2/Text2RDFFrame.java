package javaswing2;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Text2RDFFrame extends JFrame {
  private Task task;
  
  private String inputText;
  
  private String outputText;
  
  private String formatText;
  
  private Mining m;
  
  private ButtonGroup buttonGroup1;
  
  private ButtonGroup buttonGroup2;
  
  private JButton jButton1;
  
  private JComboBox jComboBox1;
  
  private JComboBox jComboBox2;
  
  private JEditorPane jEditorPane2;
  
  private JLabel jLabel1;
  
  private JLabel jLabel10;
  
  private JLabel jLabel11;
  
  private JLabel jLabel12;
  
  private JLabel jLabel13;
  
  private JLabel jLabel14;
  
  private JLabel jLabel15;
  
  private JLabel jLabel16;
  
  private JLabel jLabel2;
  
  private JLabel jLabel3;
  
  private JLabel jLabel4;
  
  private JLabel jLabel5;
  
  private JLabel jLabel6;
  
  private JLabel jLabel7;
  
  private JLabel jLabel8;
  
  private JLabel jLabel9;
  
  private JPanel jPanel1;
  
  private JPanel jPanel2;
  
  private JPanel jPanel3;
  
  private JPanel jPanel4;
  
  private JPanel jPanel5;
  
  private JPanel jPanel6;
  
  private JPanel jPanel7;
  
  private JPanel jPanel8;
  
  private JPanel jPanel9;
  
  private JProgressBar jProgressBar1;
  
  private JRadioButton jRadioButton1;
  
  private JRadioButton jRadioButton2;
  
  private JScrollPane jScrollPane1;
  
  private JScrollPane jScrollPane2;
  
  private JScrollPane jScrollPane3;
  
  private JScrollPane jScrollPane4;
  
  private JScrollPane jScrollPane6;
  
  private JScrollPane jScrollPane7;
  
  private JScrollPane jScrollPane8;
  
  private JSeparator jSeparator1;
  
  private JTabbedPane jTabbedPane1;
  
  private JTabbedPane jTabbedPane2;
  
  private JTextArea jTextArea1;
  
  private JTextArea jTextArea3;
  
  private JTextArea jTextArea4;
  
  private JTextArea jTextArea5;
  
  private JTextPane jTextPane1;
  
  private JTree jTree1;
  
  private Label label1;
  
  public Text2RDFFrame() {
    initLookAndFeel();
    initComponents();
    this.jProgressBar1.setVisible(false);
    this.jLabel9.setCursor(new Cursor(12));
    this.jLabel9.setForeground(Color.BLUE.darker());
    this.jLabel9.addMouseListener(new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            try {
              URI url = new URI("http://www.w3.org/RDF/Validator/");
              Desktop.getDesktop().browse(url);
            } catch (Exception el) {
              el.printStackTrace();
            } 
          }
        });
  }
  
  protected static ImageIcon createImageIcon(String path, String description) {
    URL imgURL = Text2RDFFrame.class.getResource(path);
    if (imgURL != null)
      return new ImageIcon(imgURL, description); 
    System.err.println("Couldn't find file: " + path);
    return null;
  }
  
  class Task extends SwingWorker<Void, Void> {
    public Void doInBackground() {
      Text2RDFFrame.this.jProgressBar1.setValue(50);
      Text2RDFFrame.this.jTabbedPane1.setVisible(true);
      Text2RDFFrame.this.m.processTextContent();
      Text2RDFFrame.this.outputText = Text2RDFFrame.this.m.getTextContent();
      return null;
    }
    
    public void done() {
      Toolkit.getDefaultToolkit().beep();
      Text2RDFFrame.this.jButton1.setEnabled(true);
      Text2RDFFrame.this.jProgressBar1.setValue(100);
      Text2RDFFrame.this.jLabel6.setText("Done");
      Text2RDFFrame.this.outputText = Text2RDFFrame.this.m.getTextContent();
      Text2RDFFrame.this.formatText = Text2RDFFrame.this.m.getFormatText();
      Text2RDFFrame.this.jEditorPane2.setText(Text2RDFFrame.this.outputText);
      Text2RDFFrame.this.jTextArea3.setText(Text2RDFFrame.this.m.getTermList());
      Text2RDFFrame.this.jTextArea5.setText(Text2RDFFrame.this.m.getPhraseList());
      Text2RDFFrame.this.jLabel16.setText(Text2RDFFrame.this.formatText);
      Text2RDFFrame.this.jTextPane1.setText(Text2RDFFrame.this.formatText);
      Text2RDFFrame.this.setCursor((Cursor)null);
      System.exit(0);
    }
  }
  
  private static void initLookAndFeel() {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private void initComponents() {
    this.buttonGroup1 = new ButtonGroup();
    this.buttonGroup2 = new ButtonGroup();
    this.jScrollPane4 = new JScrollPane();
    this.jTextArea4 = new JTextArea();
    this.jTabbedPane2 = new JTabbedPane();
    this.jPanel7 = new JPanel();
    this.label1 = new Label();
    this.jLabel10 = new JLabel();
    this.jLabel14 = new JLabel();
    this.jPanel1 = new JPanel();
    this.jTabbedPane1 = new JTabbedPane();
    this.jPanel2 = new JPanel();
    this.jLabel8 = new JLabel();
    this.jLabel9 = new JLabel();
    this.jScrollPane7 = new JScrollPane();
    this.jEditorPane2 = new JEditorPane();
    this.jPanel3 = new JPanel();
    this.jLabel1 = new JLabel();
    this.jScrollPane2 = new JScrollPane();
    this.jTextArea3 = new JTextArea();
    this.jLabel7 = new JLabel();
    this.jScrollPane3 = new JScrollPane();
    this.jTextArea5 = new JTextArea();
    this.jPanel5 = new JPanel();
    this.jLabel15 = new JLabel();
    this.jLabel16 = new JLabel();
    this.jScrollPane8 = new JScrollPane();
    this.jTextPane1 = new JTextPane();
    this.jPanel4 = new JPanel();
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    this.jLabel2 = new JLabel();
    this.jButton1 = new JButton();
    this.jProgressBar1 = new JProgressBar();
    this.jPanel8 = new JPanel();
    this.jLabel3 = new JLabel();
    this.jSeparator1 = new JSeparator();
    this.jLabel4 = new JLabel();
    this.jRadioButton2 = new JRadioButton();
    this.jRadioButton1 = new JRadioButton();
    this.jComboBox1 = new JComboBox();
    this.jComboBox2 = new JComboBox();
    this.jLabel5 = new JLabel();
    this.jLabel6 = new JLabel();
    this.jPanel6 = new JPanel();
    this.jPanel9 = new JPanel();
    this.jScrollPane6 = new JScrollPane();
    this.jTree1 = new JTree();
    this.jLabel11 = new JLabel();
    this.jLabel13 = new JLabel();
    this.jLabel12 = new JLabel();
    this.jTextArea4.setColumns(20);
    this.jTextArea4.setRows(5);
    this.jScrollPane4.setViewportView(this.jTextArea4);
    setDefaultCloseOperation(3);
    setTitle("TEXT2RDF Application");
    this.label1.setAlignment(1);
    this.label1.setFont(new Font("Arial", 1, 14));
    this.label1.setText("TEXT2RDF Application");
    this.jLabel10.setFont(new Font("Calibri", 0, 14));
    this.jLabel10.setText("<html>The information resources in an organization are generally described in the form of text documents published as electronic documents or hardcopy. Such documents are self-contained sets of sentences delineating certain information about the company. It is necessary to develop a standard mechanism for modelling, management and exchange of information in an explicit and structured manner. <br><br> <b>The TEXT2RDF is developed for extracting information from unstructured web documents and transforming it into semantic web resources. </b> </html>");
    this.jLabel10.setAlignmentX(0.5F);
    this.jLabel14.setIcon(new ImageIcon(getClass().getResource("/javaswing2/t2r.PNG")));
    this.jLabel14.setToolTipText("TEXT2RDF Application");
    GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
    this.jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGap(123, 123, 123).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGap(230, 230, 230).addComponent(this.label1, -2, -1, -2)).addGroup(jPanel7Layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel10, -2, 741, -2)))).addGroup(jPanel7Layout.createSequentialGroup().addGap(216, 216, 216).addComponent(this.jLabel14))).addContainerGap(117, 32767)));
    jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addGap(21, 21, 21).addComponent(this.label1, -2, -1, -2).addGap(25, 25, 25).addComponent(this.jLabel10).addGap(18, 18, 18).addComponent(this.jLabel14).addContainerGap(56, 32767)));
    this.jTabbedPane2.addTab("About", this.jPanel7);
    this.jPanel1.setBorder(BorderFactory.createTitledBorder("TEXT2RDF Application"));
    this.jTabbedPane1.setAutoscrolls(true);
    this.jPanel2.setAutoscrolls(true);
    this.jLabel8.setText("To check and visualize this RDF, use W3C validation service");
    this.jLabel9.setText("<html>(<a href=\"http://www.w3.org/RDF/Validator/\">http://www.w3.org/RDF/Validator/</a>)</html>");
    this.jScrollPane7.setViewportView(this.jEditorPane2);
    GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
    this.jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel8).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel9).addContainerGap(463, 32767)).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane7).addContainerGap())));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addGap(219, 219, 219).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel8).addComponent(this.jLabel9)).addContainerGap(20, 32767)).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane7, -2, 198, -2).addContainerGap(44, 32767))));
    this.jTabbedPane1.addTab("RDF", this.jPanel2);
    this.jPanel3.setAutoscrolls(true);
    this.jLabel1.setText("List of terms:");
    this.jTextArea3.setColumns(20);
    this.jTextArea3.setLineWrap(true);
    this.jTextArea3.setRows(5);
    this.jTextArea3.setWrapStyleWord(true);
    this.jScrollPane2.setViewportView(this.jTextArea3);
    this.jLabel7.setText("List of phrases");
    this.jTextArea5.setColumns(20);
    this.jTextArea5.setRows(5);
    this.jScrollPane3.setViewportView(this.jTextArea5);
    GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
    this.jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jScrollPane2, -2, 303, -2)).addGap(24, 24, 24).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel7).addComponent(this.jScrollPane3, -1, 593, 32767)).addContainerGap()));
    jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jLabel7)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane2, -1, 211, 32767).addComponent(this.jScrollPane3, -1, 211, 32767)).addContainerGap()));
    this.jTabbedPane1.addTab("BOP", this.jPanel3);
    this.jPanel5.setAutoscrolls(true);
    this.jLabel15.setText("<html><b>Formatted text</b> (with <u>terms</u> and <b>keywords</b>):</html>");
    this.jLabel16.setText("Wait for processing...");
    this.jScrollPane8.setViewportView(this.jTextPane1);
    GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
    this.jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel16, -2, 707, -2).addComponent(this.jLabel15).addComponent(this.jScrollPane8, -2, 497, -2)).addContainerGap(223, 32767)));
    jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel15).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel16).addGap(56, 56, 56).addComponent(this.jScrollPane8, -2, 49, -2).addContainerGap(103, 32767)));
    this.jTabbedPane1.addTab("TEXT", this.jPanel5);
    this.jTextArea1.setColumns(20);
    this.jTextArea1.setLineWrap(true);
    this.jTextArea1.setRows(5);
    this.jTextArea1.setText("My name is Nitesh. ");
    this.jTextArea1.setWrapStyleWord(true);
    this.jScrollPane1.setViewportView(this.jTextArea1);
    this.jLabel2.setText("Enter the text here:");
    this.jButton1.setText("SUBMIT");
    this.jButton1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            //Text2RDFFrame.this.jButton1ActionPerformed(evt);
          }
        });
    this.jButton1.addPropertyChangeListener(new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent evt) {
            Text2RDFFrame.this.jButton1PropertyChange(evt);
          }
        });
    this.jPanel8.setAlignmentX(0.0F);
    this.jPanel8.setAlignmentY(0.0F);
    this.jLabel3.setText("Additional Information");
    this.jLabel4.setText("Type of Content:");
    this.buttonGroup1.add(this.jRadioButton2);
    this.jRadioButton2.setText("Specific");
    this.buttonGroup1.add(this.jRadioButton1);
    this.jRadioButton1.setText("General");
    this.jRadioButton1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Text2RDFFrame.this.jRadioButton1ActionPerformed(evt);
          }
        });
    this.jComboBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "Select...", "Information Technology", "Manufacturing", "Pharmasutical", "Construction" }));
    GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
    this.jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jSeparator1, -1, 139, 32767)).addGroup(jPanel8Layout.createSequentialGroup().addGap(22, 22, 22).addComponent(this.jLabel3)).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel4)).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jRadioButton1, -2, 87, -2)).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addComponent(this.jRadioButton2, -2, 77, -2))).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup().addContainerGap(20, 32767).addComponent(this.jComboBox1, -2, -1, -2)));
    jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator1, -2, 10, -2).addGap(3, 3, 3).addComponent(this.jLabel4, -2, 14, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jRadioButton2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jComboBox1, -2, -1, -2).addContainerGap(53, 32767)));
    this.jComboBox2.setModel(new DefaultComboBoxModel<String>(new String[] { "Select...", "Sample 1", "Sample 2", "Sample 3", "Sample 4" }));
    this.jLabel5.setText("Sample Text");
    this.jLabel6.setText("Click here!");
    GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
    this.jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addGap(2, 2, 2).addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jProgressBar1, -2, 154, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 305, 32767).addComponent(this.jLabel5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jComboBox2, -2, 115, -2)).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING, -1, 766, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jPanel8, -2, -1, -2)).addComponent(this.jLabel2, -2, 145, -2)).addContainerGap()));
    jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jPanel8, -2, -1, -2).addContainerGap()).addGroup(jPanel4Layout.createSequentialGroup().addComponent(this.jScrollPane1, -1, 172, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jComboBox2, -2, -1, -2).addComponent(this.jLabel5)).addGroup(GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel6).addComponent(this.jButton1, -2, 23, -2).addComponent(this.jProgressBar1, -1, 23, 32767))).addGap(15, 15, 15)))));
    GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
    this.jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jTabbedPane1, GroupLayout.Alignment.TRAILING, -1, 945, 32767).addComponent(this.jPanel4, GroupLayout.Alignment.TRAILING, -1, -1, 32767)).addContainerGap()));
    jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jPanel4, -2, -1, -2).addGap(1, 1, 1).addComponent(this.jTabbedPane1, -1, 281, 32767)));
    this.jTabbedPane2.addTab("TEXT2RDF", this.jPanel1);
    this.jTree1.setFont(new Font("Tahoma", 0, 10));
    DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("EUROVOC");
    DefaultMutableTreeNode treeNode2 = new DefaultMutableTreeNode("POLITICS");
    DefaultMutableTreeNode treeNode3 = new DefaultMutableTreeNode("political framework");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("political party");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("electoral procedure and voting");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("parliament");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("parliamentary proceedings");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("politics and public safety");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("executive power and public service");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("INTERNATIONAL RELATIONS");
    treeNode3 = new DefaultMutableTreeNode("international affairs");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("cooperation policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("international balance");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("defence");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("EUROPEAN COMMUNITIES");
    treeNode3 = new DefaultMutableTreeNode("Community institutions and European civil service");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("European Union law");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("European construction");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("Community finance");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("LAW");
    treeNode3 = new DefaultMutableTreeNode("sources and branches of the law");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("civil law");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("criminal law");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("justice");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("organisation of the legal system");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("international law");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("rights and freedoms");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("ECONOMICS");
    treeNode3 = new DefaultMutableTreeNode("economic policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("economic growth");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("economic structure");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("national accounts");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("economic analysis");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("TRADE");
    treeNode3 = new DefaultMutableTreeNode("trade policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("tariff policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("trade");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("international trade");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("consumption");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("marketing");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("distributive trades");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("FINANCE");
    treeNode3 = new DefaultMutableTreeNode("monetary relations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("monetary economics");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("financial institutions and credit");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("free movement of capital");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("financing and investmen");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("insurance");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("public finance and budget policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("budget");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("taxation");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("prices");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("SOCIAL QUESTIONS");
    treeNode3 = new DefaultMutableTreeNode("family");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("migration");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("demography and population");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("social framework");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("social affairs");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("culture and religion");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("social protection");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("health");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("construction and town planning");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("EDUCATION AND COMMUNICATIONS");
    treeNode3 = new DefaultMutableTreeNode("education");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("teaching");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("organisation of teaching");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("documentation");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("communications");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("information and information processing");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("information technology and data processing");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("SCIENCE");
    treeNode3 = new DefaultMutableTreeNode("natural and applied sciences");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("humanities");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("BUSINESS AND COMPETITION");
    treeNode3 = new DefaultMutableTreeNode("business organisation");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("business classification");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("legal form of organisations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("management");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("accounting");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("competition");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("EMPLOYMENT AND WORKING CONDITIONS");
    treeNode3 = new DefaultMutableTreeNode("employment");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("labour market");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("organisation of work and working conditions");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("personnel management and staff remuneration");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("labour law and labour relations");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("TRANSPORT");
    treeNode3 = new DefaultMutableTreeNode("transport policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("organisation of transport");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("land transport");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("maritime and inland waterway transport");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("air and space transport");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("ENVIRONMENT");
    treeNode3 = new DefaultMutableTreeNode("environmental policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("natural environment");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("deterioration of the environment");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("AGRICULTURE, FORESTRY AND FISHERIES");
    treeNode3 = new DefaultMutableTreeNode("agricultural policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("agricultural structures and production");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("farming systems");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("cultivation of agricultural land");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("means of agricultural production");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("agricultural activity");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("forestry");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("fisheries");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("AGRI-FOODSTUFFS");
    treeNode3 = new DefaultMutableTreeNode("plant product");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("animal product");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("processed agricultural produce");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("beverages and sugar");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("foodstuff");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("agri-foodstuffs");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("food technology");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("PRODUCTION, TECHNOLOGY AND RESEARCH");
    treeNode3 = new DefaultMutableTreeNode("production");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("technology and technical regulations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("research and intellectual property");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("ENERGY");
    treeNode3 = new DefaultMutableTreeNode("energy policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("coal and mining industries");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("oil industry");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("electrical and nuclear industries");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("soft energy");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("INDUSTRY");
    treeNode3 = new DefaultMutableTreeNode("industrial structures and policy");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("chemistry");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("iron, steel and other metal industries");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("mechanical engineering");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("electronics and electrical engineering");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("building and public works");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("wood industry");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("leather and textile industries");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("miscellaneous industries");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("GEOGRAPHY");
    treeNode3 = new DefaultMutableTreeNode("Europe");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("regions of EU Member States");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("America");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("Africa");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("Asia and Oceania");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("economic geography");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("political geography");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("overseas countries and territories");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new DefaultMutableTreeNode("INTERNATIONAL ORGANISATIONS");
    treeNode3 = new DefaultMutableTreeNode("United Nations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("European organisations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("extra-European organisations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("world organisations");
    treeNode2.add(treeNode3);
    treeNode3 = new DefaultMutableTreeNode("non-governmental organisations");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    this.jTree1.setModel(new DefaultTreeModel(treeNode1));
    this.jScrollPane6.setViewportView(this.jTree1);
    this.jLabel11.setText("<html>In this demonstration, <b>EUROVOC Thesaurus</b> is used</html>");
    this.jLabel13.setText("European Union, 2010, http://eurovoc.europa.eu/ ");
    GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
    this.jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup().addContainerGap().addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jScrollPane6, GroupLayout.Alignment.LEADING, -1, 375, 32767).addComponent(this.jLabel11, GroupLayout.Alignment.LEADING, -1, 375, 32767).addComponent(this.jLabel13, GroupLayout.Alignment.LEADING)).addContainerGap()));
    jPanel9Layout.setVerticalGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel9Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel11, -2, 14, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jScrollPane6, -1, 464, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jLabel13).addContainerGap()));
    GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
    this.jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel9, -2, -1, -2).addContainerGap(576, 32767)));
    jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel9, -1, -1, 32767).addContainerGap()));
    this.jTabbedPane2.addTab("Glossary", this.jPanel6);
    this.jLabel12.setText("Nitesh Khilwani, Loughborough University 2010");
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jTabbedPane2, -2, 986, -2).addComponent(this.jLabel12, GroupLayout.Alignment.TRAILING)).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTabbedPane2, -2, 576, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jLabel12, -2, 14, -2)));
    pack();
  }
  
  private void jRadioButton1ActionPerformed(ActionEvent evt) {}
  
//  private void jButton1ActionPerformed(ActionEvent evt) {
//	System.out.println("fdasfdaaa");
//    this.inputText = this.jTextArea1.getText();
//    this.m = new Mining();
//    this.m.setTextContent(this.inputText);
//    this.jButton1.setEnabled(false);
//    this.jProgressBar1.setVisible(true);
//    this.jProgressBar1.setValue(0);
//    this.jLabel6.setText("Processing...");
//    setCursor(Cursor.getPredefinedCursor(3));
//    this.task = new Task();
//    this.task.execute();
//  }
  public void convertText2RDF() throws IOException {
	  	File f = new File("data/Test_Example.txt");
	  	FileReader fr = new FileReader(f);
	  	BufferedReader br = new BufferedReader(fr);
	  	String inputText = "";
	  	String line = br.readLine();
	  	while(line != null) {
	  		inputText += line+" ";
	  		//System.out.println(line);
	  		line = br.readLine();
	  	}
	    this.m = new Mining();
	    this.m.setTextContent(inputText);
	    this.jButton1.setEnabled(false);
	    this.jProgressBar1.setVisible(true);
	    this.jProgressBar1.setValue(0);
	    this.jLabel6.setText("Processing...");
	    setCursor(Cursor.getPredefinedCursor(3));
	    this.task = new Task();
	    this.task.execute();	
	    
  }
  
  private void jButton1PropertyChange(PropertyChangeEvent evt) {}
  
}
