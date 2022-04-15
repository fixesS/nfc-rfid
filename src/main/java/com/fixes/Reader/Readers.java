package com.fixes.Reader;

import com.fixes.Main.Main;
import com.fixes.Reader.EventHandler.EventHandler;
import com.mot.rfid.api3.RFIDReader;

import java.util.ArrayList;
import java.util.List;

public class Readers {
    public List<Reader> readersList = new ArrayList<>();

    private Main main ;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Reader> getReadersList() {
        return readersList;
    }

    public void addReader(String hostHame, int port, int timeOut, String id){
        Reader reader = new Reader();
        reader.setHostName(hostHame);
        reader.setPort(port);
        reader.setTimeout(timeOut);
        reader.setID(id);
        readersList.add(reader);
    }

    public void setEventHndlerToReaders(){
        for(Reader reader : readersList){
            EventHandler eventHandler = new EventHandler();
            eventHandler.setReader(reader);
            eventHandler.setMain(main);
            reader.Events.addEventsListener(eventHandler);

            // Subscribe required status notification
            reader.Events.setInventoryStartEvent(true);
            reader.Events.setInventoryStopEvent(true);
            reader.Events.setAccessStartEvent(true);
            reader.Events.setAccessStopEvent(true);

            // enables tag read notification. if this is set to false, no tag read notification will be send
            reader.Events.setTagReadEvent(true);
            reader.Events.setAntennaEvent(true);
            reader.Events.setBufferFullEvent(true);
            reader.Events.setBufferFullWarningEvent(true);
            reader.Events.setGPIEvent(true);
            reader.Events.setReaderDisconnectEvent(true);

        }
    }
}
