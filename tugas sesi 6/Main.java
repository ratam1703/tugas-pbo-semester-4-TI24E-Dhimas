// File: Main.java
import java.util.Scanner; // Menggunakan Scanner untuk input user [cite: 651]

public class Main {
    public static void main(String[] args) {
        // Objek laptop bisa diganti sesuai merk yang ingin dites
        Laptop thinkpad = new Lenovo(); 
        LaptopUser andri = new LaptopUser(thinkpad);
        
        Scanner input = new Scanner(System.in);
        String action;

        System.out.println("=== SISTEM OPERASI LAPTOP ===");
        System.out.println("Ketik perintah: ON, OFF, UP, DOWN, atau EXIT");

        while (true) {
            System.out.print("> ");
            action = input.nextLine().toUpperCase(); // Membaca input dan ubah ke kapital

            if (action.equals("ON")) {
                andri.turnOnLaptop();
            } else if (action.equals("OFF")) {
                andri.turnOffLaptop();
            } else if (action.equals("UP")) {
                andri.makeLaptopLouder();
            } else if (action.equals("DOWN")) {
                andri.makeLaptopSilence();
            } else if (action.equals("EXIT")) {
                System.out.println("Keluar program...");
                break;
            } else {
                System.out.println("Perintah salah!");
            }
        }
        input.close();
    }
}