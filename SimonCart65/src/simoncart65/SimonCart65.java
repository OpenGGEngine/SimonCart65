/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65;

import com.opengg.core.engine.GGApplication;
import com.opengg.core.engine.OpenGG;
import com.opengg.core.render.window.WindowInfo;
import com.opengg.core.render.window.WindowOptions;

/**
 *
 * @author Javier
 */
public class SimonCart65 extends GGApplication{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowInfo winfo = new WindowInfo();
        winfo.width = 1920;
        winfo.height = 1080;
        winfo.displaymode = WindowOptions.WINDOWED;
        winfo.name = "Simon Cart 65";
        SimonCart65 sc65 = new SimonCart65();
        sc65.applicationID = 69420;
        sc65.applicationName = "Simon Cart 65";
        OpenGG.initialize(sc65, winfo);
    }

    @Override
    public void setup() {
        
    }

    @Override
    public void render() {

    }

    @Override
    public void update(float delta) {
    
    }
    
}
