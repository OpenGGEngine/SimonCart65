/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65;

import com.opengg.core.audio.AudioController;
import com.opengg.core.audio.Soundtrack;
import com.opengg.core.audio.SoundtrackHandler;
import com.opengg.core.engine.*;
import com.opengg.core.gui.GUI;
import com.opengg.core.gui.GUIController;
import com.opengg.core.io.ControlType;
import com.opengg.core.math.Quaternionf;
import com.opengg.core.math.Vector2f;
import com.opengg.core.math.Vector3f;
import com.opengg.core.math.util.Spline2D;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.physics.collision.ConvexHull;
import com.opengg.core.render.ProjectionData;
import com.opengg.core.render.RenderEngine;
import com.opengg.core.render.light.Light;
import com.opengg.core.render.texture.Texture;
import com.opengg.core.render.texture.TextureManager;
import com.opengg.core.render.window.WindowInfo;
import com.opengg.core.render.window.WindowOptions;
import com.opengg.core.world.Skybox;
import com.opengg.core.world.Terrain;
import com.opengg.core.world.WorldEngine;
import com.opengg.core.world.WorldLoader;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.LightComponent;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.TerrainComponent;
import simoncart65.components.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.opengg.core.io.input.keyboard.Key.*;

/**
 *
 * @author Javier
 */
public class SimonCart65 extends GGApplication {

    public RaceManagerComponent mg;
    public ModelRenderComponent ceesdasdf;
    public MainMenuComponent mm;
    public static SimonCart65 sc65;
    public static boolean inmenu = false;
    public static TerrainComponent tc;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowInfo winfo = new WindowInfo();
        winfo.width = 1280;
        winfo.height = 720;
        winfo.displaymode = WindowOptions.WINDOWED;
        winfo.name = "Simon Cart 65";
        winfo.vsync = true;
        sc65 = new SimonCart65();
        sc65.applicationID = 69420;
        sc65.applicationName = "Simon Cart 65";
        OpenGG.initialize(sc65, winfo);
    }

    @Override
    public void setup() {
        ArrayList<Vector3f> v2 = new ArrayList<>();
        v2.add(new Vector3f(-1,-1,-1));
        v2.add(new Vector3f(-1,1,-1));
        v2.add(new Vector3f(-1,-1,1));
        v2.add(new Vector3f(-1,1,1));
        v2.add(new Vector3f(1,-1,-1));
        v2.add(new Vector3f(1,1,-1));
        v2.add(new Vector3f(1,-1,1));
        v2.add(new Vector3f(1,1,1));
        Soundtrack menu = new Soundtrack();
        menu.addSong(Resource.getSoundData("windgarden.ogg"));

        menu.shuffle();
        menu.play();
        AudioController.setGlobalGain(0f);
        SoundtrackHandler.setSoundtrack(menu);

        Soundtrack game = new Soundtrack();
        game.addSong(Resource.getSoundData("windgarden.ogg"));

        game.shuffle();
        game.play();
        AudioController.setGlobalGain(0f);
        mm = new MainMenuComponent();
        WorldEngine.useWorld(WorldLoader.loadWorld(Resource.getWorldPath("map2")));
        try {
            mg = new RaceManagerComponent();
            mg.checkpoints = 4;
        } catch (IOException ex) {
        }

        WorldEngine.getCurrent().attach(new LightComponent(Light.createDirectional(new Quaternionf(new Vector3f(30, -45, 0)), new Vector3f(1,1,1))));

        Terrain t = Terrain.generate(Resource.getTextureData("rainbowheight.png"));

        tc = new TerrainComponent(t);
        tc.enableCollider();
        TextureManager.unloadTexture(Resource.getTexturePath("rainbowblend.png"));
        tc.setBlotmap(Texture.get2DTexture(TextureManager.loadTexture(Resource.getTexturePath("rainbowblend.png"), false)));
        tc.setGroundArray(Texture.getArrayTexture(Resource.getTextureData("black.png"), Resource.getTextureData("rainbowroad.png"), Resource.getTextureData("black.png"), Resource.getTextureData("black.png")));
        tc.setPositionOffset(new Vector3f(-200, 60, -200));
        tc.setScaleOffset(new Vector3f(400, 60f, 400));

        WorldEngine.getCurrent().attach(tc);
/*
        FinishLine check = new FinishLine();
        check.setPositionOffset(new Vector3f(0, -30, 0));
        WorldEngine.getCurrent().attach(check);

        Checkpoint c1 = new Checkpoint(5, 1);
        c1.setPositionOffset(new Vector3f(0, -30, -40));
        ModelRenderComponent cm1 = new ModelRenderComponent(ModelManager.getDefaultModel());
        cm1.setPositionOffset(c1.getPosition());
        WorldEngine.getCurrent().attach(c1);
        WorldEngine.getCurrent().attach(cm1);

        Checkpoint c2 = new Checkpoint(5, 2);
        c2.setPositionOffset(new Vector3f(-70, -30, -40));
        ModelRenderComponent cm2 = new ModelRenderComponent(ModelManager.getDefaultModel());
        cm2.setPositionOffset(c2.getPosition());
        WorldEngine.getCurrent().attach(c2);
        WorldEngine.getCurrent().attach(cm2);

        Checkpoint c3 = new Checkpoint(5, 3);
        c3.setPositionOffset(new Vector3f(-40, -30, 0));
        ModelRenderComponent cm3 = new ModelRenderComponent(ModelManager.getDefaultModel());
        cm3.setPositionOffset(c3.getPosition());
        WorldEngine.getCurrent().attach(c3);
        WorldEngine.getCurrent().attach(cm3);

        ceesdasdf = new ModelRenderComponent(ModelManager.getDefaultModel());
        WorldEngine.getCurrent().attach(ceesdasdf);*/

        for(Component c : WorldEngine.getCurrent().getAll())
            if(c instanceof TerrainComponent)
                tc = (TerrainComponent) c;

        BindController.addBind(ControlType.KEYBOARD, "forward", KEY_W);
        BindController.addBind(ControlType.KEYBOARD, "backward", KEY_S);
        BindController.addBind(ControlType.KEYBOARD, "left", KEY_A);
        BindController.addBind(ControlType.KEYBOARD, "right", KEY_D);
        BindController.addBind(ControlType.KEYBOARD, "up", KEY_SPACE);
        BindController.addBind(ControlType.KEYBOARD, "down", KEY_LEFT_SHIFT);
        BindController.addBind(ControlType.KEYBOARD, "lookright", KEY_RIGHT);
        BindController.addBind(ControlType.KEYBOARD, "lookleft", KEY_LEFT);
        BindController.addBind(ControlType.KEYBOARD, "lookup", KEY_UP);
        BindController.addBind(ControlType.KEYBOARD, "lookdown", KEY_DOWN);
        BindController.addBind(ControlType.KEYBOARD, "useitem", KEY_SPACE);
        BindController.addBind(ControlType.KEYBOARD, "pause", KEY_ESCAPE);
        BindController.addBind(ControlType.KEYBOARD, "meme", KEY_G);

        RenderEngine.setProjectionData(ProjectionData.getPerspective(100, 0.2f, 3000f));
        RenderEngine.setSkybox(new Skybox(Texture.getCubemap(
                Resource.getTexturePath("skybox\\sky_ft.png"),
                Resource.getTexturePath("skybox\\sky_bk.png"),
                Resource.getTexturePath("skybox\\sky_up.png"),
                Resource.getTexturePath("skybox\\sky_dn.png"),
                Resource.getTexturePath("skybox\\sky_rt.png"),
                Resource.getTexturePath("skybox\\sky_lf.png")), 1500f));

        
        generateNodesFromCheckpoints(Checkpoint.getOrdered());
        mg.path = new Spline2D(RaceManagerComponent.nodes.stream().map(Component::getPosition).map(v -> new Vector2f(v.x,v.z)).collect(Collectors.toList()));

        for(Component c : WorldEngine.getCurrent().getAll()){
            if(c instanceof CarSpawner){
                ((CarSpawner)c).spawn();
                break;
            }
        }
        
        try{
             PlayerCarComponent pcc = new PlayerCarComponent(ModelLoader.loadNewModel("resources\\models\\banana\\Banana.bmf"));
            pcc.setPositionOffset(Checkpoint.getById(0).getPosition());
            ((ConvexHull)pcc.p.getEntity().getColliders().get(0).getColliders().get(0)).vertices = v2;
            RaceManagerComponent.racers.add(pcc);
WorldEngine.getCurrent().attach(pcc);
RaceManagerComponent.p = pcc;
        }catch(Exception e){}
        
        ItemBoxSpawner i = null;
        try {
            i = new ItemBoxSpawner(ModelLoader.loadNewModel("resources\\models\\banana\\Banana.bmf"));
        } catch (IOException ex) {
           
        }
        WorldEngine.getCurrent().attach(i);

    }

    public void generateNodesFromCheckpoints(List<Checkpoint> checkpoints) {
        int s = 0;
        for (Checkpoint c : checkpoints) {
            Node n = new Node(Integer.toString(s), WorldEngine.getCurrent().getAll().indexOf(c) == WorldEngine.getCurrent().getAll().size() ? Integer.toString(0) : Integer.toString(s - 1));
            n.setPositionOffset(c.getPosition());
            WorldEngine.getCurrent().attach(n);
            RaceManagerComponent.nodes.add(n);
        }
    }

    @Override
    public void render() {

    }
    float full = 0;

    @Override
    public void update(float delta) {

        if (inmenu == false) {
            mm.update(delta);
            GUIController.getDefault().getRoot().getItem("itemholder").enabled = false;
            GUIController.getDefault().getRoot().getItem("sidebar").enabled = false;
            GUIController.getDefault().getRoot().getItem("item").enabled = false;
            mm.enabled = true;
            mg.enabled = false;
        } else {
            GUIController.getDefault().getRoot().getItem("itemholder").enabled = true;
            GUIController.getDefault().getRoot().getItem("sidebar").enabled = true;

            GUIController.getDefault().getRoot().getItem("characterselect").enabled = false;
            mg.enabled = true;
            mm.enabled = false;
            mg.update(delta);
        }

    }

}
