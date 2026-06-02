import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
         
        String sourceFile = "D:\\Tugas Coding\\Tugas 11\\students.csv";
        String destinationFile = "D:\\Tugas Coding\\Tugas 11\\students_copy.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile))) {

            String line;
            int count = 0;

            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                count++;
            }
            
            System.out.println("Proses copy berhasil!");
            System.out.println("Sebanyak " + count + " baris telah disalin ke: " + destinationFile);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat mengakses file.");
            e.printStackTrace();
        }
    }
}