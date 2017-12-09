/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.Resource;
import com.opengg.core.gui.GUI;
import com.opengg.core.gui.GUITexture;
import com.opengg.core.math.Vector2f;
import com.opengg.core.render.texture.TextureManager;
import com.opengg.core.world.components.Component;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Warren
 */
public class RaceManagerComponent extends Component{
    
    public static TreeSet<CarComponent> racers = new TreeSet<>();
    GUITexture itemholder;
    GUITexture item;
    public RaceManagerComponent(){
       
       // TextureManager.loadTexture("itemholder");
        
        itemholder = new GUITexture( Resource.getTexture("itemholder.png"),new Vector2f(-0.23f,0.7f), new Vector2f(0.3f,0.3f));
        item = new GUITexture( Resource.getTexture("shell.png"),new Vector2f(-0.185f,0.75f), new Vector2f(0.20f,0.20f));
       item.setLayer(-1f);
        GUI.root.addItem("itemholder", itemholder);
         GUI.root.addItem("item", item);
    }
    
    public void update(){
        
    }
}
