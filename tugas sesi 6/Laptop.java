// File: Laptop.java
public interface Laptop {
    int MAX_VOL = 100; // Konstanta volume maksimal [cite: 518]
    int MIN_VOL = 0;   // Konstanta volume minimal [cite: 520]

    void powerOn();    // Method tanpa implementasi [cite: 523]
    void powerOff();   // [cite: 525]
    void volumeUp();   // [cite: 527]
    void volumeDown(); // [cite: 529]
}