package finalproject;

import java.io.*;
import java.util.Vector;

import javax.swing.JOptionPane;

public class DataReader {
   static final String[] ListNames = {"Homeplus","himart"};
   
   public static Vector<String[]> getDatas(String path) {
      Vector<String[]> datas = new Vector<String[]>(3);
      try {
         File src = new File(path);
            //file 객체 생성
         FileReader fileReader = new FileReader(src);
          //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(fileReader);
            String line = "";
            while((line = bufReader.readLine()) != null){
               String[] data = line.split(",");
               datas.add(data);
            }
            fileReader.close();
      } catch (FileNotFoundException e){
         JOptionPane.showMessageDialog(null, "파일 경로를 확인해주십시오.");
      } catch (IOException e) { 
         JOptionPane.showMessageDialog(null, "파일 읽기 오류");
      }   
      return datas;
   }
   public static String[] getListNames() {
      return ListNames;
   }
   public static Vector<String> getFileNames() {
      File testFile = new File(System.getProperty("user.dir"));
      File[] fileList = testFile.listFiles();
      Vector<String> fileNames = new Vector<String>(3);
      for (File file : fileList) {
         String name = file.getName();
         if (name.startsWith("20")) {
            int index = name.indexOf(".");
            fileNames.addElement(name.substring(0, index));
         }
      }
      return fileNames;
   }
}