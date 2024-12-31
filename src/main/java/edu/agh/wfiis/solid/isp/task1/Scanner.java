package edu.agh.wfiis.solid.isp.task1;

class Scanner implements OfficeDevice {
    @Override
    public void print(String document) {
        throw new UnsupportedOperationException("Scanner cannot print documents.");
    }

    @Override
    public String scan() {
        String scannedContent = "Scanned content from Scanner.";
        System.out.println("Scanning document...");
        return scannedContent;
    }

    @Override
    public void copy(String document) { throw new UnsupportedOperationException("Scanner cannot copy documents."); }

    @Override
    public void fax(String document) {
        throw new UnsupportedOperationException("Scanner cannot fax documents.");
    }
}

