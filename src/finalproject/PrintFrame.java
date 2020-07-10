package finalproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PrintFrame extends JFrame {
   String[] tableHeader = {"CheckBox","분류","상품명","가격(원)","수량","총 금액(원)","비고"};
   Object[][] tableBody = {};
   String[] dates = {"전체","식품","생필품","전자제품","기타"};
   JPanel jp = new JPanel();
   DefaultTableModel newTm ;
   PrintFrame(DefaultTableModel tm){
      DefaultTableModel oldTm = tm;
      setTitle("쇼핑 리스트 작성 프로그램");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jp.setLayout(new BorderLayout(15,15));
      jp.setBackground(new Color(255,255,255));
      
      //frame 상단부
      JPanel jpNorth = new JPanel();
      jpNorth.setBackground(new Color(255,255,255));
      jpNorth.setLayout(new BoxLayout(jpNorth,BoxLayout.X_AXIS));
      //간격 만들기
      jpNorth.add(Box.createHorizontalStrut(20));
      //콤보박스 추가
      JPanel test = new JPanel();
      test.setBackground(new Color(255,255,255));
      test.setLayout(new BorderLayout());
      JComboBox<String> comboDates= new JComboBox<String>(dates);
      comboDates.setPreferredSize(new Dimension(20, 20));
      comboDates.setBackground(new Color(255,255,255));
      comboDates.setSelectedIndex(0);
      comboDates.addItemListener(new ItemListener() {
         @Override
         public void itemStateChanged(ItemEvent e) {
            newTm.setNumRows(0);
            String category = (String)comboDates.getSelectedItem();
            Vector<Vector> datas = tm.getDataVector();
            for (Vector data : datas) {
               if(category.equals("전체")) {
                  newTm.addRow(data);
               }
               else if(category.equals(data.get(1))) {
                  newTm.addRow(data);
               }
            }
         }
         
      });
      test.add(comboDates,"South");
      jpNorth.add(test);

      //간격 만들기
      jpNorth.add(Box.createHorizontalStrut(20));
      //텍스트 추가
      JLabel title = new JLabel("쇼핑 리스트");
      title.setFont(new Font("맑은 고딕",Font.BOLD, 40));
      title.setPreferredSize(new Dimension(280, 80));
      //간격 만들기
      jpNorth.add(Box.createHorizontalStrut(200));
      jpNorth.add(title);
      jp.add(jpNorth,"North");
      
      //frame 중단부
      newTm = new DefaultTableModel(tableBody,tableHeader) {
         Class[] columnTypes = new Class[] {
               Boolean.class, String.class, String.class, String.class, String.class
            };
      };
      newTm.setRowCount(0);
      for(Vector rowData : tm.getDataVector()) {
         rowData.add(0, false);
         newTm.addRow(rowData);
      }
      JTable jt = new JTable(newTm);
      jt.removeEditor();
      JScrollPane scrollPane = new JScrollPane(jt);
      scrollPane.setBackground(new Color(255,255,255));

      //체크박스 추가
      JCheckBox box = new JCheckBox();
      box.setHorizontalAlignment(JLabel.CENTER);
      DefaultCellEditor checkEditor = new DefaultCellEditor(box){
         public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column){
            JCheckBox editor;
            editor = (JCheckBox)super.getTableCellEditorComponent(table,value,isSelected,row,column);
            return editor;
         }
      };
      jt.getColumn("CheckBox").setCellEditor(checkEditor);
      jt.getColumn("CheckBox").setCellRenderer(new cellCheckRenderer());
      //박스 추가 끝
      System.out.println("박스추가 끝");
      
      
      jt.getColumn("CheckBox").setPreferredWidth(40);
      jt.getColumn("분류").setPreferredWidth(80);
      jt.getColumn("상품명").setPreferredWidth(220);
      jt.getColumn("가격(원)").setPreferredWidth(80);
      jt.getColumn("수량").setPreferredWidth(160);
      jt.getColumn("총 금액(원)").setPreferredWidth(140);
      jt.getColumn("비고").setPreferredWidth(40);
      jt.getTableHeader().setResizingAllowed(false);
      jt.getTableHeader().setReorderingAllowed(false);      
      jt.setAlignmentX(CENTER_ALIGNMENT);
      jt.setShowVerticalLines(false);
      jt.setShowHorizontalLines(false);
      jp.add(scrollPane,"Center");
      
      //frame 하단 부
      JPanel jpSouth = new JPanel();
      jpSouth.setBackground(new Color(255,255,255));
      jpSouth.setLayout(new BoxLayout(jpSouth,BoxLayout.X_AXIS));
      jpSouth.setSize(new Dimension(200,200));
      //간격 만들기
      jpSouth.add(Box.createHorizontalStrut(400));
      //버튼 추가
      JButton btnAdd = new JButton("수정");
      btnAdd.setPreferredSize(new Dimension(120, 60));
      btnAdd.setBackground(new Color(255,255,255));
      btnAdd.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tm.setRowCount(0);
            for (Vector rowData : newTm.getDataVector()) {
               rowData.remove(0);
               tm.addRow(rowData);
            }
            new MainFrame(tm);
            dispose();
         }
      });
      jpSouth.add(btnAdd);
      //간격 만들기
      jpSouth.add(Box.createHorizontalStrut(30));
      //frame 하단부 삽입
      jp.add(jpSouth,"South");

      
      
        setSize(960, 540); 
        setVisible(true);
        setLocationRelativeTo(null); 
        this.add(jp);
   }
}
class cellCheckRenderer extends DefaultTableCellRenderer{
   public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected,boolean hasFocus, int row,int column){   
      JCheckBox check = new JCheckBox(); check.setSelected(((Boolean)value).booleanValue()); check.setHorizontalAlignment(SwingConstants.CENTER);
      return check;
   
   }
}