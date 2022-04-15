package com.fixes.Reader;

import com.mot.rfid.api3.RFIDReader;

public class Reader extends RFIDReader {
    private String ID = "";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
