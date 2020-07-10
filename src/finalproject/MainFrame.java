package finalproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;




public class MainFrame extends JFrame {
	JPanel jp = new JPanel();
	String[] tableHeader = {"분류","상품명","가격(원)","수량","총 금액(원)","비고"};
	Object[][] tableBody = {{"과일","사과","5,000","1","5,000"," "}};
	MainFrame(){
		DefaultTableModel tm = new DefaultTableModel(tableBody,tableHeader) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
	            return false;
	        }
		};
        tm.setNumRows(0);
		new MainFrame(tm);
	}
	@SuppressWarnings("serial")
	MainFrame(DefaultTableModel tm){
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
		//버튼 추가
		JButton btnItemList = new JButton("상품 리스트");
		btnItemList.setPreferredSize(new Dimension(160, 60));
		btnItemList.setBackground(new Color(224,224,224));
		btnItemList.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		    	new ItemListFrame(tm); 
		    }    
		});
		jpNorth.add(btnItemList);
		//간격 만들기
		jpNorth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnLastList = new JButton("이전 쇼핑 리스트");
		btnLastList.setPreferredSize(new Dimension(160, 60));
		btnLastList.setBackground(new Color(224,224,224));
		btnLastList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LastListFrame(tm);
			}
		});
		jpNorth.add(btnLastList);
		//간격 만들기
		jpNorth.add(Box.createHorizontalStrut(100));
		//텍스트 추가
		JLabel title = new JLabel("쇼핑 리스트");
		title.setFont(new Font("맑은 고딕",Font.BOLD, 40));
		title.setPreferredSize(new Dimension(280, 80));
		jpNorth.add(title);
		jp.add(jpNorth,"North");
		
		
		//frame 중단부
		JTable jt = new JTable(tm);
		jt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		jt.getColumn("분류").setPreferredWidth(80);
		jt.getColumn("상품명").setPreferredWidth(220);
		jt.getColumn("가격(원)").setPreferredWidth(80);
		jt.getColumn("수량").setPreferredWidth(40);
		jt.getColumn("총 금액(원)").setPreferredWidth(140);
		jt.getColumn("비고").setPreferredWidth(40);
		jt.getTableHeader().setResizingAllowed(false);
		jt.getTableHeader().setReorderingAllowed(false);		
		jt.setAlignmentX(CENTER_ALIGNMENT);
		jt.setShowVerticalLines(false);
		jt.setShowHorizontalLines(false);
		jt.setRowHeight(30);
		DefaultTableCellRenderer jtCellRenderer = new DefaultTableCellRenderer();
		jtCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("맑은 고딕",Font.BOLD,200);
		jtCellRenderer.setFont(font);
		jt.setDefaultRenderer(Object.class, jtCellRenderer);

		JScrollPane scrollPane = new JScrollPane(jt);
		scrollPane.setBackground(new Color(255,255,255));
		jp.add(scrollPane,"Center");
		
		
		//frame 하단 부
		JPanel jpSouth = new JPanel();
		jpSouth.setBackground(new Color(255,255,255));
		jpSouth.setLayout(new BoxLayout(jpSouth,BoxLayout.X_AXIS));
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		
		//버튼 추가
		JButton btnSeletedEdit = new JButton("수정 하기");
		btnSeletedEdit.setPreferredSize(new Dimension(160, 60));
		btnSeletedEdit.setBackground(new Color(224,224,224));
		btnSeletedEdit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (jt.getSelectedRow() != -1) {
			    	new EditFrame(tm,jt.getSelectedRow());
		        }
		    }
		});
		jpSouth.add(btnSeletedEdit);
		
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnSeletedDelete = new JButton("선택 삭제");
		btnSeletedDelete.setPreferredSize(new Dimension(160, 60));
		btnSeletedDelete.setBackground(new Color(224,224,224));
		btnSeletedDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        while (jt.getSelectedRow() != -1) {
		            tm.removeRow(jt.getSelectedRow());
		        }
		    }
		});
		jpSouth.add(btnSeletedDelete);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnAllDelete = new JButton("전체 삭제");
		btnAllDelete.setPreferredSize(new Dimension(160, 60));
		btnAllDelete.setBackground(new Color(224,224,224));
		btnAllDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        tm.setNumRows(0);
		    }
		});
		jpSouth.add(btnAllDelete);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(310));
		//버튼 추가
		JButton btnAdd = new JButton("직접 추가");
		btnAdd.setPreferredSize(new Dimension(120, 60));
		btnAdd.setBackground(new Color(224,224,224));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddFrame(tm);
			}
		});
		jpSouth.add(btnAdd);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		//버튼 추가
		JButton btnSucces = new JButton("작성 완료");
		btnSucces.setPreferredSize(new Dimension(120, 60));
		btnSucces.setBackground(new Color(25,140,235));
		btnSucces.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataWriter.saveDatas(tm);
				new PrintFrame(tm);
				dispose();
			}
		});
		jpSouth.add(btnSucces);
		//간격 만들기
		jpSouth.add(Box.createHorizontalStrut(20));
		
		//frame 하단부 삽입
		jp.add(jpSouth,"South");

        setSize(960, 540); 
        setVisible(true);
        setLocationRelativeTo(null); 
        this.add(jp);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
