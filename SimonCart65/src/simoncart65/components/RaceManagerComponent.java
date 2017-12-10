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
import com.opengg.core.engine.WorldEngine;
import com.opengg.core.gui.GUI;
import com.opengg.core.gui.GUIGroup;
import com.opengg.core.gui.GUIText;
import com.opengg.core.gui.GUITexture;
import com.opengg.core.math.Vector2f;
import com.opengg.core.math.Vector3f;
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
import java.util.LinkedList;
import java.util.TreeSet;
import simoncart65.Spline2D;

/**
 *
 * @author Warren
 */
public class RaceManagerComponent extends Component implements Actionable {

    public static TreeSet<CarComponent> racers = new TreeSet<>();

    static Model banana;
    public boolean enabled = false;
    public static PlayerCarComponent p;
    public static Item[] items = new Item[]{new ShellItem(Resource.getTexture("shell.png"), "C:\\res\\GreenShell\\GreenShell.bmf"),
         new Item(Resource.getTexture("fakebox.png"), "C:\\res\\banana\\banana.bmf", new Vector3f(0.3f)), new BananaItem(Resource.getTexture("banana.png"), "C:\\res\\banana\\banana.bmf"), new MushroomItem(Resource.getTexture("mushroom.png"), "C:\\res\\banana\\banana.bmf")};
    GUITexture itemholder;
   static  GUITexture item;
    static GUIGroup sidebar;

    Texture simon = Resource.getTexture("simon.png");
    Texture bobomb = Resource.getTexture("simon.png");
    Texture stalin = Resource.getTexture("stalin.png");

    GUITexture first, second, third, fourth, fp, sp, tp, frp;

    GGFont font = Resource.getFont("test", "test.png");
    GUIText g = new GUIText(new Text("1st", new Vector2f(), 3.2f, 0.5f, false), font, new Vector2f(0.04f, -0.05f));
    GUIText spedometer = new GUIText(new Text("Quickness", new Vector2f(), 0.9f, 0.5f, false), font, new Vector2f(0.8f, -0.8f));

    GUIText lapcounter = new GUIText(new Text("1/3 Lap", new Vector2f(), 1.2f, 0.5f, false), font, new Vector2f(0.8f, -0.05f));

    Sound itemuse;

    public int checkpoints = 0;

    public static LinkedList<Node> nodes = new LinkedList<>();

    public Spline2D path;
    static Sound s;

    UserControlComponent uc = new UserControlComponent();
    static int pointer = 0;
    static int pointer2 = 0;

    public RaceManagerComponent() throws IOException {

        // TextureManager.loadTexture("itemholder");
        s = new Sound(Resource.getSoundData("item.ogg"));
        itemuse = new Sound(Resource.getSoundData("itemuse.ogg"));
        itemholder = new GUITexture(Resource.getTexture("itemholder.png"), new Vector2f(-0.21f, 0.7f), new Vector2f(0.3f, 0.3f));
        item = new GUITexture(items[0].t, new Vector2f(-0.165f, 0.75f), new Vector2f(0.20f, 0.20f));

        first = new GUITexture(Resource.getTexture("selector.png"), new Vector2f(-0.94f, 0.3f), new Vector2f(0.25f, 0.25f));
        second = new GUITexture(Resource.getTexture("secondplace.png"), new Vector2f(-0.94f, -0.1f), new Vector2f(0.25f, 0.25f));
        third = new GUITexture(Resource.getTexture("thirdplace.png"), new Vector2f(-0.94f, -0.5f), new Vector2f(0.25f, 0.25f));
        fourth = new GUITexture(Resource.getTexture("fourthplace.png"), new Vector2f(-0.94f, -0.9f), new Vector2f(0.25f, 0.25f));
        fp = new GUITexture(simon, new Vector2f(-0.94f, 0.3f), new Vector2f(0.25f, 0.25f));
        sp = new GUITexture(bobomb, new Vector2f(-0.94f, -0.1f), new Vector2f(0.25f, 0.25f));
        tp = new GUITexture(stalin, new Vector2f(-0.94f, -0.5f), new Vector2f(0.25f, 0.25f));
        frp = new GUITexture(simon, new Vector2f(-0.94f, -0.9f), new Vector2f(0.25f, 0.25f));

        fp.setLayer(-1);
        sp.setLayer(-1);
        tp.setLayer(-1);

        frp.setLayer(-1);

        sidebar = new GUIGroup(new Vector2f(0, 0));
        sidebar.addItem("first", first);
        sidebar.addItem("second", second);
        sidebar.addItem("third", third);
        sidebar.addItem("fourth", fourth);
        sidebar.addItem("fp", fp);
        sidebar.addItem("sp", sp);
        sidebar.addItem("tp", tp);
        sidebar.addItem("frp", frp);
        sidebar.addItem("place", g);
        sidebar.addItem("lapcounter", lapcounter);
        sidebar.addItem("spedometer", spedometer);
        item.setLayer(-1f);
        GUI.root.addItem("itemholder", itemholder);
        GUI.root.addItem("sidebar", sidebar);
        GUI.root.addItem("item", item);

//        itemholder.enabled = false;
//        sidebar.enabled = false;
//        item.enabled = false;
        BindController.addController(uc);
        this.attach(uc);

    }

    @Override
    public void update(float delta) {

        racers = new TreeSet<>(racers);
        ((GUIText) sidebar.getItem("spedometer")).setText("Quickness: \n" + (int) Math.abs(p.p.getEntity().velocity.x()) + " speeds");

        p.raceposition = racers.tailSet(p).size();
        //System.out.println(racers.size());
        //System.out.println(p.raceposition);
        switch (p.raceposition) {
            case 1:
                g.setText("1st");
                break;
            case 2:
                g.setText("2nd");
                break;
            case 3:
                g.setText("3rd");
                break;
            default:
                g.setText(p.raceposition + "th");
                break;
        }
        item.setTexture(items[pointer / 15].t);
        
        if (pointer2 == 0) {
            p.currentitem = items[pointer / 15];
            pointer2 = -1;
        }
        if (pointer2 > 0) {
            pointer++;

            if (pointer > (items.length * 15) - 1) {

                pointer = 0;

            }
            pointer2--;
        }

        lapcounter.setText("Lap: " + (p.lap + 1) + "/3");
    }

    @Override
    public void onAction(Action action) {
        if (enabled) {
            if (action.type == ActionType.PRESS) {
                switch (action.name) {
                    case "useitem":
                        if (p.currentitem != null) {
                            System.out.println("sa");
                            itemuse.play();

                            p.currentitem = null;
                            item.enabled = false;
                       
                        }
                        break;
                }
            }
        }
    }
    
    public static void spinitem(){
         if (p.currentitem == null) {
             item.enabled = true;
                            s.play();
                            pointer2 = 250 + (int) (Math.random() * 100);
                         
                        }
    }

}
