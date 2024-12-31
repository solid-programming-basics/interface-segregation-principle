package edu.agh.wfiis.solid.isp.task1;

interface OfficeDevice {
    void print(String document);
    String scan();
    void copy(String document);
    void fax(String document);
}

