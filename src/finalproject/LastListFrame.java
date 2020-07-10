package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class LastListFrame extends JFrame {
	JPanel jp = new JPanel();
	JLabel title;
	JTable jt;
	String[] tableHeader = {"분류","상품명","가격(원)"};
	Object[][] tableBody = {{"과일","사과","5,000"}};
	Vector<String> dates = DataReader.getFileNames();
	DefaultTableModel newTm = new DefaultTableModel(tableBody,tableHeader) {
		public boolean isCellEditable(int rowIndex, int mColIndex) {
            return false;
        }
	};
	LastListFrame(DefaultTableModel tm){
		setTitle("이전 쇼핑 리스트");
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
		String path = comboDates.getItemAt(0)+".txt";
		Vector<String[]> datas = DataReader.getDatas(path);
		for (String[] data : datas) {
			newTm.addRow(data);
		}
		comboDates.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				newTm.setNumRows(0);
				title.setText(comboDates.getSelectedItem()+" 쇼핑리스트");
				String path = comboDates.getSelectedItem()+".txt";
				Vector<String[]> datas = DataReader.getDatas(path);
				for (String[] data : datas) {
					newTm.addRow(data);;
				}
			}
			
		});
		test.add(comboDates,"South");
		jpNorth.add(test);
		//간격 만들기
//		jpNorth.add(Box.createHorizontalStrut(20));
		//텍스트 추가
		title = new JLabel(comboDates.getSelectedItem()+" 쇼핑리스트");
		title.setFont(new Font("맑은 고딕",Font.BOLD, 20));
		title.setPreferredSize(new Dimension(200, 80));
		jpNorth.add(title);
		//간격 만들기
		jpNorth.add(Box.createHorizontalStrut(80));
		jp.add(jpNorth,"North");
		
		
		//frame 중단부
		jt = new JTable(newTm);
		jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(jt);
		scrollPane.setBackground(new Color(255,255,255));
		jt.getColumn("분류").setPreferredWidth(80);
		jt.getColumn("상품명").setPreferredWidth(220);
		jt.getColumn("가격(원)").setPreferredWidth(80);
		jt.getTableHeader().setResizingAllowed(false);
		jt.getTableHeader().setReorderingAllowed(false);		
		jt.setAlignmentX(CENTER_ALIGNMENT);
		jt.setAlignmentY(CENTER_ALIGNMENT);
		jt.setShowVerticalLines(false);
		jt.setShowHorizontalLines(false);
		jp.add(scrollPane,"Center");
		
		
		//frame 하단 부
		JPanel jpSouth = new JPanel();
		jpSouth.setBackground(new Color(255,255,255));
		jpSouth.setLayout(new BoxLayout(jpSouth,BoxLayout.X_AXIS));
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnSeletedDelete = new JButton("선택 추가");
		btnSeletedDelete.setPreferredSize(new Dimension(160, 60));
		btnSeletedDelete.setBackground(new Color(224,224,224));
		btnSeletedDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	for(int rowIndex : jt.getSelectedRows()) {
					String[] newRow = {
							(String)newTm.getValueAt(rowIndex, 0),//분류
							(String)newTm.getValueAt(rowIndex, 1),//상품명
							(String)newTm.getValueAt(rowIndex, 2),//가격
							"1",//수량
							(String)newTm.getValueAt(rowIndex, 2),//총금액
							""//비고
							};
		        	tm.addRow(newRow);
		        	}
		    }
		});
		jpSouth.add(btnSeletedDelete);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnAllDelete = new JButton("전체 추가");
		btnAllDelete.setPreferredSize(new Dimension(160, 60));
		btnAllDelete.setBackground(new Color(224,224,224));
		btnAllDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	//add기능 추가
		    	for(Vector rowData : newTm.getDataVector()) {
					rowData.add("1"); //수량
					rowData.add(rowData.get(2));//총금액
					rowData.add(""); //비고
			    	tm.addRow(rowData);
		    	}
		    }
		});
		jpSouth.add(btnAllDelete);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnSucces = new JButton("추가 완료");
		btnSucces.setPreferredSize(new Dimension(120, 60));
		btnSucces.setBackground(new Color(25,140,235));
		btnSucces.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jpSouth.add(btnSucces);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		
		//frame 하단부 삽입
		jp.add(jpSouth,"South");

        setSize(400, 540); 
        setVisible(true);
        this.add(jp);
	}
}
