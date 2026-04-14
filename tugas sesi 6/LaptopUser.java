 // File: LaptopUser.java
public class LaptopUser {
    private Laptop laptop; // Variabel bertipe interface 

    public LaptopUser(Laptop laptop) {
        this.laptop = laptop; // Inisialisasi melalui constructor 
    }

    void turnOnLaptop() { this.laptop.powerOn(); }   // Memanggil method interface 
    void turnOffLaptop() { this.laptop.powerOff(); } // 
    void makeLaptopLouder() { this.laptop.volumeUp(); } // 
    void makeLaptopSilence() { this.laptop.volumeDown(); } // 
}