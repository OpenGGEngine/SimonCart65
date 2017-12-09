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
import com.opengg.core.model.Model;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.render.texture.TextureManager;
import com.opengg.core.world.Action;
import com.opengg.core.world.Actionable;
import com.opengg.core.world.components.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Warren
 */
public class RaceManagerComponent extends Component{
    
    public static TreeSet<CarComponent> racers = new TreeSet<>();
    
    static Model banana;
    
    public static Item[] items = new Item[]{new Item(Resource.getTexture("shell.png"),banana)
    ,new Item(Resource.getTexture("fakebox.png"),banana),new Item(Resource.getTexture("banana.png"),banana),new Item(Resource.getTexture("mushroom.png"),banana)};
    GUITexture itemholder;
    GUITexture item;
    
    int pointer = 0;
    public RaceManagerComponent() throws IOException{
       
       // TextureManager.loadTexture("itemholder");
        
        itemholder = new GUITexture( Resource.getTexture("itemholder.png"),new Vector2f(-0.23f,0.7f), new Vector2f(0.3f,0.3f));
        item = new GUITexture(items[0].t,new Vector2f(-0.185f,0.75f), new Vector2f(0.20f,0.20f));
       item.setLayer(-1f);
        GUI.root.addItem("itemholder", itemholder);
         GUI.root.addItem("item", item);
         banana = ModelLoader.loadNewModel("C:\\res\\banana\\banana.bmf");
    }
    
    public void update(float delta){
        item.setTexture(items[pointer/20].t);
       
        pointer++;
        
        if(pointer > (items.length*20)  -1){
            pointer = 0;
        }
    }

   
    
}
