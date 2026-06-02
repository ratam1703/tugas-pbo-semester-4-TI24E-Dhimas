import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
         
        String csvFile = "D:\\Tugas Coding\\Tugas 11\\new_students.csv";
        Scanner scanner = new Scanner(System.in);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            System.out.print("Berapa jumlah data mahasiswa yang ingin diinput? ");
            int jumlahData = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < jumlahData; i++) {
                System.out.println("\nMasukkan Data ke-" + (i + 1));
                
                System.out.print("NIM   : ");
                String nim = scanner.nextLine();
                
                System.out.print("NAMA  : ");
                String nama = scanner.nextLine();
                
                System.out.print("UMUR  : ");
                String umur = scanner.nextLine();
                
                System.out.print("PRODI : ");
                String prodi = scanner.nextLine();

                String barisData = nim + ", " + nama + ", " + umur + ", " + prodi;
                
                bw.write(barisData);
                bw.newLine();
            }
            
            System.out.println("\nData berhasil ditulis ke: " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Error: Pastikan Anda memasukkan angka untuk jumlah data.");
        } finally {
            scanner.close();
        }
    }
}