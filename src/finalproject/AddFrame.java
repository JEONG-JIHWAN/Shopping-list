package finalproject;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddFrame extends JFrame {
   private JButton btnAdd = new JButton("추가");
    private JButton btnCancle = new JButton("취소");
    private String[] dates = {"식품","생필품","전자제품","기타"};
    private JComboBox<String> newCategory= new JComboBox<String>(dates);;
    private JTextField newItemName = new JTextField("");
    private JTextField newPrice = new JTextField("0");
    private JTextField newCount = new JTextField("0");
    private JTextField newEtc = new JTextField("");
   AddFrame(DefaultTableModel tm){
        setTitle("직접 추가하기");
        setSize(200, 300);
        setResizable(false);
        setLocation(950, 300);
        setVisible(true);
        
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
               String[] newRow = {
                     (String)newCategory.getSelectedItem(),
                     newItemName.getText(),
                     newPrice.getText(),
                     newCount.getText(),
                     Integer.toString(totalCost),
                     newEtc.getText()};
               tm.addRow(newRow);
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
