package naumen.framework;

import java.awt.*;
import java.awt.event.KeyEvent;


public class DelayedKeyPressThread implements Runnable{

    Thread runner;
    private int delay ;

    public DelayedKeyPressThread() {
    }

    public DelayedKeyPressThread(String threadName) {
        this.delay = 5000 ;
        init (threadName) ;

    }
    public DelayedKeyPressThread(String threadName, int delay) {
        this.delay = delay ;
        init (threadName) ;
    }
    private void init(String threadName) {
        runner = new Thread(this, threadName); // (1) Create a new thread.
        runner.start(); // (2) Start the thread.
    }
    public void run() {
        Robot robot = null ;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        for (int i=0; i<5; i++){
            robot.delay(delay);
            robot.keyPress(KeyEvent.VK_ESCAPE) ;
            robot.keyRelease(KeyEvent.VK_ESCAPE) ;
        }
    }

}