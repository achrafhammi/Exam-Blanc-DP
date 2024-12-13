package achraf.monitors;

public class VgaDisplay implements Vga{
    @Override
    public void showMessage(String message) {
        System.out.println("vga monitor: ");
        System.out.println(message);
    }
}
