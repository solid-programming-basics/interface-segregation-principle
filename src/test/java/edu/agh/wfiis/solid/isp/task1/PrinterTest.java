package edu.agh.wfiis.solid.isp.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    private final Printer underTest = new Printer();

    @Test
    void testPrinterPrint() {
        assertDoesNotThrow(() -> underTest.print("Test document"));
    }

    @Test
    void testPrinterScanThrowsException() {
        assertThrows(UnsupportedOperationException.class, underTest::scan);
    }

    @Test
    void testPrinterCopyThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> underTest.copy("Test document"));
    }

    @Test
    void testPrinterFaxThrowsException() {
        assertThrows(UnsupportedOperationException.class, () -> underTest.fax("Test document"));
    }
}
