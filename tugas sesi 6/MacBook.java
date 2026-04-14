 // File: MacBook.java (TUGAS NOMOR 2)
public class MacBook implements Laptop {
    private int volume = 50;
    private boolean is_power_on;

    @Override
    public void powerOn() { is_power_on = true; System.out.println("MacBook Hello!"); }
    @Override
    public void powerOff() { is_power_on = false; System.out.println("MacBook Sleep."); }
    @Override
    public void volumeUp() {
        if (is_power_on) {
            this.volume = Math.min(MAX_VOL, this.volume + 10);
            System.out.println("Volume MacBook: " + this.volume);
        }
    }
    @Override
    public void volumeDown() {
        if (is_power_on) {
            this.volume = Math.max(MIN_VOL, this.volume - 10);
            System.out.println("Volume MacBook: " + this.volume);
        }
    }
}