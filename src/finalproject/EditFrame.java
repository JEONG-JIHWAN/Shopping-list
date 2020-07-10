package finalproject;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditFrame extends JFrame {
   private JButton btnAdd = new JButton("수정");
    private JButton btnCancle = new JButton("취소");
    private String[] dates = {"식품","생필품","전자제품","기타"};
    private JComboBox<String> newCategory= new JComboBox<String>(dates);;
    private JTextField newItemName;
    private JTextField newPrice;
    private JTextField newCount;
    private JTextField newEtc;
   EditFrame(DefaultTableModel tm, int selectedRow){;
        setTitle("항목 수정하기");
        setSize(200, 300);
        setResizable(false);
        setLocation(700, 300);
        setVisible(true);

        newCategory.setSelectedItem(tm.getValueAt(selectedRow, 0));
        newItemName = new JTextField((String) tm.getValueAt(selectedRow, 1));
        newPrice = new JTextField((String) tm.getValueAt(selectedRow, 2));
        newCount = new JTextField((String) tm.getValueAt(selectedRow, 3));
        newEtc = new JTextField((String) tm.getValueAt(selectedRow, 5));
        
        GridLayout grid = new GridLayout(6, 2);
        grid.setVgap(5);
        Container c = getContentPane();
        c.setLayout(grid);
        
        c.add(new JLabel(" 분류"));
        c.add(newCategory);
        c.add(new JLabel(" 상품명"));
        c.add(newItemName);
        c.add(new JLabel(" 가격(원)"));
        c.add(newPrice);
        c.add(new JLabel(" 수량"));
        c.add(newCount);
        c.add(new JLabel(" 비고"));
        c.add(newEtc);
        
        btnAdd.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               int price = Integer.parseInt(newPrice.getText());
               int count = Integer.parseInt(newCount.getText());
               int totalCost = price*count;
               tm.setValueAt(newCategory.getSelectedItem(), selectedRow, 0);
               tm.setValueAt(newItemName.getText(), selectedRow, 1);
               tm.setValueAt(newPrice.getText(), selectedRow, 2);
               tm.setValueAt(newCount.getText(), selectedRow, 3);
               tm.setValueAt(Integer.toString(totalCost), selectedRow, 4);
               tm.setValueAt(newEtc.getText(), selectedRow, 5);
               dispose();
            } catch(Exception exception) {
               JOptionPane.showMessageDialog(null, "입력값을 확인해주십시오.");
            }
         }
        });
        btnCancle.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
        });
        c.add(btnAdd);
        c.add(btnCancle);
   }
}