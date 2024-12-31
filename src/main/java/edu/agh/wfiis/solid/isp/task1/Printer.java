package edu.agh.wfiis.solid.isp.task1;

class Printer implements OfficeDevice {
    @Override
    public void print(String document) {
        System.out.println("Printer printing: " + document);
    }

    @Override
    public String scan() {
        throw new UnsupportedOperationException("Printer cannot scan");
    }

    @Override
    public void copy(String document) {
        throw new UnsupportedOperationException("Printer cannot copy");
    }

    @Override
    public void fax(String document) {
        throw new UnsupportedOperationException("Printer cannot fax");
    }
}

