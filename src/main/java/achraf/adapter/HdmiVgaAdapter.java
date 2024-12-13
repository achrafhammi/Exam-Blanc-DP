package achraf.adapter;

import achraf.monitors.Hdmi;
import achraf.monitors.Vga;
import achraf.monitors.VgaDisplay;

public class HdmiVgaAdapter implements Hdmi {
    private Vga vgaCable;

    public HdmiVgaAdapter(VgaDisplay vgaDisplay) {
        vgaCable = vgaDisplay;
    }

    @Override
    public void showMessage(String message) {
        System.out.println("passe par adapter vers vga monitor: ");
        vgaCable.showMessage(message);
        System.out.print("\n");
    }
}
