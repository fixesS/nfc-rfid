package com.fixes;

import com.fixes.GUI.MainFrame;
import com.fixes.Main.Main;

public class Start {
    private static final Main main = new Main();
    private static final MainFrame mainFrame = new MainFrame();

    public static void main(String[] args) {
        main.main();
        mainFrame.setMain(main);
        main.setMainFrame(mainFrame);
        mainFrame.start();
    }
}