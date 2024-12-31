package edu.agh.wfiis.solid.isp.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiFunctionOfficeDeviceTest {

    private final MultiFunctionOfficeDevice underTest = new MultiFunctionOfficeDevice();

    @Test
    void testMultiFunctionPrinterPrint() {
        assertDoesNotThrow(() -> underTest.print("Test document"));
    }

    @Test
    void testMultiFunctionPrinterScan() {
        String scannedContent = underTest.scan();
        assertNotNull(scannedContent);
        assertEquals("Scanned content from MultiFunctionDevice", scannedContent);
    }

    @Test
    void testMultiFunctionPrinterCopy() {
        assertDoesNotThrow(() -> underTest.copy("Test document"));
    }

    @Test
    void testMultiFunctionPrinterFax() {
        assertDoesNotThrow(() -> underTest.fax("Test document"));
    }
}