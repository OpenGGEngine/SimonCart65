/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65;

import com.opengg.core.audio.Soundtrack;
import com.opengg.core.audio.SoundtrackHandler;
import com.opengg.core.engine.AudioController;
import com.opengg.core.engine.BindController;
import com.opengg.core.engine.GGApplication;
import com.opengg.core.engine.OpenGG;
import com.opengg.core.engine.ProjectionData;
import com.opengg.core.engine.RenderEngine;
import com.opengg.core.engine.Resource;
import com.opengg.core.engine.WorldEngine;
import com.opengg.core.io.ControlType;
import static com.opengg.core.io.input.keyboard.Key.*;
import com.opengg.core.math.Vector3f;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.model.ModelManager;
import com.opengg.core.render.light.Light;
import com.opengg.core.render.texture.Texture;
import com.opengg.core.render.texture.TextureManager;
import com.opengg.core.render.window.WindowInfo;
import com.opengg.core.render.window.WindowOptions;
import com.opengg.core.world.Skybox;
import com.opengg.core.world.Terrain;
import com.opengg.core.world.components.*;
import java.io.IOException;
import simoncart65.components.*;

/**
 *
 * @author Javier
 */
public class SimonCart65 extends GGApplication{
    public RaceManagerComponent mg;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowInfo winfo = new WindowInfo();
        winfo.width = 1920;
        winfo.height = 1080;
        winfo.displaymode = WindowOptions.WINDOWED;
        winfo.name = "Simon Cart 65";
        winfo.vsync = true;
        SimonCart65 sc65 = new SimonCart65();
        sc65.applicationID = 69420;
        sc65.applicationName = "Simon Cart 65";
        OpenGG.initialize(sc65, winfo);
    }

    @Override
    public void setup() {
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
        
        //WorldEngine.useWorld(WorldLoader.loadWorld(Resource.getWorldPath("map1")));

        PlayerCarComponent pcc = null;
        try {
            pcc = new PlayerCarComponent(ModelLoader.loadNewModel("resources\\models\\GreenShell\\GreenShell.bmf"));
            WorldEngine.getCurrent().attach(pcc);
        } catch (IOException ex) {
            System.out.println("stop");
        }
        
        
        
        WorldEngine.getCurrent().attach(new LightComponent(new Light(new Vector3f(0,20,200), new Vector3f(1,1,1), 100000, 0))); 
        
        Terrain t = Terrain.generate(Resource.getTextureData("map1.png"));
        
        TerrainComponent tc = new TerrainComponent(t);
        //tc.enableCollider();
        tc.setBlotmap(Texture.get2DTexture(TextureManager.loadTexture(Resource.getTexturePath("blend.png"), false)));
        tc.setGroundArray(Texture.getArrayTexture(Resource.getTextureData("grass.png"), Resource.getTextureData("flower2.png"), Resource.getTextureData("dirt.png"), Resource.getTextureData("road.png")));
        tc.setPositionOffset(new Vector3f(-200, 60, -200));
        tc.setScaleOffset(new Vector3f(400,60f, 400));
        
        WorldEngine.getCurrent().attach(tc);
        
        FinishLine check = new FinishLine();
        check.setPositionOffset(new Vector3f(0,-30,0));
        WorldEngine.getCurrent().attach(check);  
        
        Checkpoint c1 = new Checkpoint(5,1);
        c1.setPositionOffset(new Vector3f(0,-30,-20));
        ModelRenderComponent cm1 = new ModelRenderComponent(ModelManager.getDefaultModel());
        cm1.setPositionOffset(c1.getPosition());
        WorldEngine.getCurrent().attach(c1);  
        WorldEngine.getCurrent().attach(cm1); 
        
        Checkpoint c2 = new Checkpoint(5,2);
        c2.setPositionOffset(new Vector3f(-20,-30,-20));
        ModelRenderComponent cm2 = new ModelRenderComponent(ModelManager.getDefaultModel());
        cm2.setPositionOffset(c2.getPosition());
        WorldEngine.getCurrent().attach(c2);  
        WorldEngine.getCurrent().attach(cm2); 
        
        Checkpoint c3 = new Checkpoint(5,3);
        c3.setPositionOffset(new Vector3f(-20,-30,0));
        ModelRenderComponent cm3 = new ModelRenderComponent(ModelManager.getDefaultModel());
        cm3.setPositionOffset(c3.getPosition());
        WorldEngine.getCurrent().attach(c3);  
        WorldEngine.getCurrent().attach(cm3); 
        
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

        try {
            mg = new RaceManagerComponent();
            mg.checkpoints = 4;
        } catch (IOException ex) {
            
        }
        RaceManagerComponent.p =pcc;
        RenderEngine.setProjectionData(ProjectionData.getPerspective(100, 0.2f, 3000f));
        RenderEngine.setSkybox(new Skybox(Texture.getCubemap(
                Resource.getTexturePath("skybox\\majestic_ft.png"),
                Resource.getTexturePath("skybox\\majestic_bk.png"),
                Resource.getTexturePath("skybox\\majestic_up.png"),
                Resource.getTexturePath("skybox\\majestic_dn.png"),
                Resource.getTexturePath("skybox\\majestic_rt.png"),
                Resource.getTexturePath("skybox\\majestic_lf.png")), 1500f));
    }

    @Override
    public void render() {

    }

    @Override
    public void update(float delta) {
        mg.update(delta);
    }
    
}
