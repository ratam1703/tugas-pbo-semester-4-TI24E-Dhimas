import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Simulasi Input Mahasiswa
        System.out.println("--- Input Data Mahasiswa ---");
        System.out.print("Nama Mahasiswa: ");
        String sName = input.nextLine();
        System.out.print("Alamat Mahasiswa: ");
        String sAddress = input.nextLine();
        Student mhs = new Student(sName, sAddress);

        System.out.print("Banyak Mata Kuliah yang diambil: ");
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Nama MK ke-" + (i+1) + ": ");
            String mk = input.next();
            System.out.print("Nilai: ");
            int nilai = input.nextInt();
            mhs.addCourseGrade(mk, nilai);
        }

        // Simulasi Input Dosen
        input.nextLine(); // clear buffer
        System.out.println("\n--- Input Data Dosen ---");
        System.out.print("Nama Dosen: ");
        String tName = input.nextLine();
        System.out.print("Alamat Dosen: ");
        String tAddress = input.nextLine();
        Teacher dsn = new Teacher(tName, tAddress);

        System.out.print("Tambah Mata Kuliah yang diampu: ");
        String mkDosen = input.next();
        if (dsn.addCourse(mkDosen)) {
            System.out.println("Berhasil menambahkan " + mkDosen);
        } else {
            System.out.println("Gagal, MK sudah ada.");
        }

        // Output Hasil
        System.out.println("\n--- Hasil Simulasi ---");
        System.out.println(mhs.toString());
        mhs.printGrades();
        System.out.println("Rata-rata Nilai: " + mhs.getAverageGrade());

        System.out.println("\n" + dsn.toString());
        
        input.close();
    }
}