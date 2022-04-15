package com.fixes.Reader.EventHandler;

import com.fixes.Main.Main;
import com.fixes.Reader.Reader;
import com.mot.rfid.api3.*;

public class EventHandler implements RfidEventsListener {
    private Reader reader;
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public void eventReadNotify(RfidReadEvents events) {
        TagData tag = events.getReadEventData().tagData;
        main.stage_0(reader.getID(),tag.getTagID());
    }

    @Override
    public void eventStatusNotify(RfidStatusEvents events) {
        System.out.println("Status Notification: " + events.StatusEventData.getStatusEventType());
    }
}
