package edu.agh.wfiis.solid.isp.task1;

class MultiFunctionOfficeDevice implements OfficeDevice {
    @Override
    public void print(String document) {
        System.out.println("MultiFunctionDevice printing: " + document);
    }

    @Override
    public String scan() {
        String scannedContent = "Scanned content from MultiFunctionDevice";
        System.out.println("MultiFunctionDevice scanning...");
        return scannedContent;
    }

    @Override
    public void copy(String document) {
        System.out.println("MultiFunctionDevice copying: " + document);
    }

    @Override
    public void fax(String document) {
        System.out.println("MultiFunctionDevice sending fax: " + document);
    }
}
