import java.util.*;
import java.io.*;
public class CsvToXml3 {
    public static void main(String[] args) {
        try {
          
            File fil = new File("C:\\Users\\souhaib\\Desktop\\XML\\NoteGinf.csv");
            FileInputStream loc = new FileInputStream(fil);
            BufferedReader reader = new BufferedReader(new InputStreamReader(loc));
            StringBuilder xml = new StringBuilder();
            String lineBreak = System.getProperty("line.separator");
            String line = null;
            List<String> headers = new ArrayList<String>();
            boolean isHeader = true;
            int count =0;
            int nbre_stu = 1;
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
            xml.append(lineBreak);
            xml.append("<Notes>");
            xml.append(lineBreak);
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                if (isHeader) {
                    isHeader = false;
                    while (tokenizer.hasMoreTokens()) {
                        headers.add(tokenizer.nextToken());
                    }
                } else {
                    count = 0;
                    xml.append("\t<Student>");
                    xml.append(lineBreak);
                    while (tokenizer.hasMoreTokens()) {
                            xml.append("\t\t<");
                            xml.append(headers.get(count));
                            xml.append(">");
                            xml.append(tokenizer.nextToken());
                            xml.append("</");
                            xml.append(headers.get(count));
                            xml.append(">");
                            xml.append(lineBreak);
                            count++;
                        }
                    xml.append("\t</Student>");
                    xml.append(lineBreak);
                    nbre_stu++;
                    }
                }
            xml.append("</Notes>");
            StringTokenizer full_name = new StringTokenizer(fil.getName(), ".");
            String name = full_name.nextToken();
            File myObj = new File(name + ".xml");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myFile = new FileWriter(name + ".xml");
            myFile.write(xml.toString());
            System.out.println("Successfully wrote to the file.");
            myFile.close();
            loc.close();
        } 
    catch (FileNotFoundException fnfe) 
    { 
        System.out.println("NO Such File Exists"); 
    } 
    catch (IOException excpt) 
    { 
        System.out.println("IOException occured"); 
    }
}
}
