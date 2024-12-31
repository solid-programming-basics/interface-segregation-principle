package edu.agh.wfiis.solid.isp.task1;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class DeviceClientTest {

    @Test
    void testUsedDevices() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        DeviceClient client = new DeviceClient();
        client.usedDevices();

        System.setOut(originalOut);

        String output = outputStream.toString();

        assertTrue(output.contains("Using device: Printer"));
        assertTrue(output.contains("Printer printing: Document to print"));

        assertTrue(output.contains("Using device: Scanner"));
        assertTrue(output.contains("Scanning document..."));

        assertTrue(output.contains("Using device: MultiFunctionOfficeDevice"));
        assertTrue(output.contains("MultiFunctionDevice copying: Document to copy"));
        assertTrue(output.contains("MultiFunctionDevice scanning..."));
        assertTrue(output.contains("MultiFunctionDevice sending fax: Document to fax"));
    }
}

