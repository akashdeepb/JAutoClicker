import java.awt.*;
import java.util.ArrayList;

class AutoClickBot {
    AutoClickBot(ArrayList x, ArrayList y) throws Exception{
        try {
            Robot clicker = new Robot();
            clicker.mouseMove(Integer.parseInt(x.get(0).toString()),Integer.parseInt(y.get(0).toString()));
            try{
                Thread.sleep(2000);
            }catch (InterruptedException ex){
                throw new Exception("Interrupted By User");
            }
            clicker.mouseMove(Integer.parseInt(x.get(1).toString()), Integer.parseInt(y.get(1).toString()));

        }catch(AWTException ex){
            throw new Exception("Failed to Create Bot");
        }

    }
}
