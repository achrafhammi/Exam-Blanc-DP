package achraf.monitors;

public class HdmiDisplay implements Hdmi{
    @Override
    public void showMessage(String message) {
        System.out.println("hdmi monitor:");
        System.out.println(message);
        System.out.print("\n");
    }
}
