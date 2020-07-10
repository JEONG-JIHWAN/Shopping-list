package finalproject;

import java.io.*;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DataWriter {
   public static void saveDatas(DefaultTableModel tm) {
      int year = Calendar.getInstance().get(Calendar.YEAR);
      int month = Calendar.getInstance().get(Calendar.MONTH)+1;
      int date = Calendar.getInstance().get(Calendar.DATE);
      String fileName = Integer.toString(year)+Integer.toString(month)+Integer.toString(date);
      File dest = new File(fileName+".txt");
      Vector<Vector> datas = tm.getDataVector();
      try {
         FileWriter fileWriter = new FileWriter(dest,false);
         for (Vector data :datas) {
            for(int i=0; i<data.size();i++) {
               fileWriter.write((String) data.get(i));
               if(i==data.size()-1)
                  fileWriter.write("\r\n");
               else fileWriter.write(",");
            }
         }
            fileWriter.close();
      } catch (FileNotFoundException e){
         JOptionPane.showMessageDialog(null, "파일 경로를 확인해주십시오.");
      } catch (IOException e) { 
         System.out.println("파일 복사 오류");
      }   
   }
