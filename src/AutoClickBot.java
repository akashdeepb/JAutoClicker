import java.awt.*;
import java.awt.event.InputEvent;
import java.util.ArrayList;

/**
 * AutoClickBot
 * This class contains the bot for clicker,[Constructor]it takes inputs as ArrayList of x coordinates, y coordinates
 * and delay. It loops through the coordinates and delay with indexes and clicks
 *
 * @author akashdeepb
 */
class AutoClickBot {

    // Constructor for AutoClickBot
    AutoClickBot(ArrayList x, ArrayList y, ArrayList delayArray) throws Exception{
        try {
            int index;
            Robot clicker = new Robot();            // Creating instance of Robot for CLicking
            for(int i = 0; i < 10; i++) {
                index = 0;
                while (index < x.size()) {          // Loop through all the coordinates

                    // Moving mouse to recorded coordinate and click
                    clicker.mouseMove(Integer.parseInt(x.get(index).toString()), Integer.parseInt(y.get(index).toString()));
                    clicker.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    clicker.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                    // Delay
                    try {
                        Thread.sleep(Integer.parseInt(delayArray.get(index).toString()));

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
