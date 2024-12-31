package edu.agh.wfiis.solid.isp.task1;

import java.util.List;

class DeviceClient {

    private static final List<OfficeDevice> DEVICES = List.of(
            new Printer(),
            new Scanner(),
            new MultiFunctionOfficeDevice()
    );

    void useDevices(){
        for (OfficeDevice device : DEVICES) {
            System.out.println("Using device: " + device.getClass().getSimpleName());

            if (device instanceof Printer || device instanceof MultiFunctionOfficeDevice) {
                device.print("Document to print");
            }

            if (device instanceof Scanner || device instanceof MultiFunctionOfficeDevice) {
                String scannedContent = device.scan();
                System.out.println("Scanned content: " + scannedContent);
            }

            if (device instanceof MultiFunctionOfficeDevice) {
                device.copy("Document to copy");
                device.fax("Document to fax");
            }

            System.out.println("----------------------");
        }
    }
    public static void main(String[] args) {
        var client = new DeviceClient();
        client.useDevices();
    }
}