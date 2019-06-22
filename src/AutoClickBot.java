import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

class AutoClickBot {
    AutoClickBot(ArrayList x, ArrayList y) throws Exception{
        try {
            int index = 0;
            Robot clicker = new Robot();
            for(int i = 0; i < 10; i++) {
                index = 0;
                while (index < x.size()) {
                    clicker.mouseMove(Integer.parseInt(x.get(index).toString()), Integer.parseInt(y.get(index).toString()));
                    clicker.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    clicker.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        throw new Exception("Interrupted By User");
                    }
                    index++;
                }
            }

        }catch(AWTException ex){
            throw new Exception("Failed to Create Bot");
        }

    }
}
