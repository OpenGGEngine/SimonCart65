/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.audio.Sound;
import com.opengg.core.audio.SoundData;
import com.opengg.core.engine.BindController;
import com.opengg.core.engine.Resource;
import com.opengg.core.gui.GUI;
import com.opengg.core.gui.GUIGroup;
import com.opengg.core.gui.GUIText;
import com.opengg.core.gui.GUITexture;
import com.opengg.core.math.Vector2f;
import com.opengg.core.model.Model;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.render.Text;
import com.opengg.core.render.texture.Texture;
import com.opengg.core.render.texture.TextureManager;
import com.opengg.core.render.texture.text.GGFont;
import com.opengg.core.world.Action;
import com.opengg.core.world.ActionType;
import com.opengg.core.world.Actionable;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.UserControlComponent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Warren
 */
public class RaceManagerComponent extends Component implements Actionable{
    
    public static TreeSet<CarComponent> racers = new TreeSet<>();
    
    static Model banana;
    
    public static Item[] items = new Item[]{new Item(Resource.getTexture("shell.png"),"C:\\res\\banana\\banana.bmf")
    ,new Item(Resource.getTexture("fakebox.png"),"C:\\res\\banana\\banana.bmf"),new Item(Resource.getTexture("banana.png"),"C:\\res\\banana\\banana.bmf"),new Item(Resource.getTexture("mushroom.png"),"C:\\res\\banana\\banana.bmf")};
    GUITexture itemholder;
    GUITexture item;
    GUIGroup sidebar;
    
    Texture simon = Resource.getTexture("simon.png");
    Texture bobomb = Resource.getTexture("simon.png");
    Texture stalin = Resource.getTexture("stalin.png");
    
    GUITexture first,second,third,fourth  ,fp,sp,tp,frp;
    
    GGFont  font = Resource.getFont("test", "test.png");
    GUIText g = new GUIText(new Text("1st" ,new Vector2f(), 3.2f, 0.5f, false),font,new Vector2f(0.04f,-0.05f));
    
    Sound s;
    
    UserControlComponent uc = new UserControlComponent();
    int pointer = 0;
    int pointer2 = 0;
    public RaceManagerComponent() throws IOException{
       
       // TextureManager.loadTexture("itemholder");
        s =new Sound(Resource.getSoundData("item.ogg"));
       itemholder = new GUITexture( Resource.getTexture("itemholder.png"),new Vector2f(-0.23f,0.7f), new Vector2f(0.3f,0.3f));
         item = new GUITexture(items[0].t,new Vector2f(-0.185f,0.75f), new Vector2f(0.20f,0.20f));
         
         first = new GUITexture(Resource.getTexture("selector.png"),new Vector2f(-0.94f,0.3f), new Vector2f(0.25f,0.25f));
          second = new GUITexture(Resource.getTexture("secondplace.png"),new Vector2f(-0.94f,-0.1f), new Vector2f(0.25f,0.25f));
           third = new GUITexture(Resource.getTexture("thirdplace.png"),new Vector2f(-0.94f,-0.5f), new Vector2f(0.25f,0.25f));
            fourth= new GUITexture(Resource.getTexture("fourthplace.png"),new Vector2f(-0.94f,-0.9f), new Vector2f(0.25f,0.25f));
            fp = new GUITexture(simon,new Vector2f(-0.94f,0.3f), new Vector2f(0.25f,0.25f));
             sp = new GUITexture(bobomb,new Vector2f(-0.94f,-0.1f), new Vector2f(0.25f,0.25f));
              tp = new GUITexture(stalin,new Vector2f(-0.94f,-0.5f), new Vector2f(0.25f,0.25f));
               frp = new GUITexture(simon,new Vector2f(-0.94f,-0.9f), new Vector2f(0.25f,0.25f));
              
               fp.setLayer(-1);
                 sp.setLayer(-1);
                   tp.setLayer(-1);
                   
                     frp.setLayer(-1);
                     
            sidebar = new GUIGroup(new Vector2f(0,0));
            sidebar.addItem("first", first);
             sidebar.addItem("second", second);
             sidebar.addItem("third", third);
               sidebar.addItem("fourth", fourth);
               sidebar.addItem("fp", fp);
               sidebar.addItem("sp", sp);
               sidebar.addItem("tp", tp);
               sidebar.addItem("frp", frp);
               sidebar.addItem("place", g);
        item.setLayer(-1f);
        GUI.root.addItem("itemholder", itemholder);
        GUI.root.addItem("sidebar", sidebar);
        GUI.root.addItem("item", item);
        
        BindController.addController(uc);
        this.attach(uc);
        
    }
    
    public void update(float delta){
        item.setTexture(items[pointer/20].t);
       
        if(pointer2 !=0){
        pointer++;
        
        if(pointer > (items.length*20)  -1){
            pointer = 0;
        }
        pointer2--;
        }
    }

    @Override
    public void onAction(Action action) {
        if(action.type == ActionType.PRESS){
            switch(action.name){
                case "useitem":
                    s.play();
                    pointer2 = 250 + (int)(Math.random()*100);
                    break;
            }
        }
    }

   
    
}
