/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.BindController;
import com.opengg.core.engine.Resource;
import com.opengg.core.gui.GUI;
import com.opengg.core.gui.GUIGroup;
import com.opengg.core.gui.GUITexture;
import com.opengg.core.math.Vector2f;
import com.opengg.core.world.Action;
import com.opengg.core.world.ActionType;
import com.opengg.core.world.Actionable;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.UserControlComponent;

/**
 *
 * @author Warren
 */
public class MainMenuComponent extends Component implements Actionable{
    
    GUITexture highlighter;
    GUITexture stalin,simon,kingbomb,barry,counterterror,beaver;
    
    GUIGroup characterselect;
    UserControlComponent uc = new UserControlComponent();
    
    int pointerselect = 0;
    public MainMenuComponent(){
        
        characterselect = new GUIGroup(new Vector2f(0,0));
        
        highlighter = new GUITexture(Resource.getTexture("selector.png"),new Vector2f(-0.2f,0f),new Vector2f(0.3f,0.39f));
        stalin = new GUITexture(Resource.getTexture("stalin.png"),new Vector2f(-0.8f,0f),new Vector2f(0.3f,0.39f));
        simon = new GUITexture(Resource.getTexture("simon.png"),new Vector2f(-0.2f,0f),new Vector2f(0.3f,0.39f));
        
        kingbomb = new GUITexture(Resource.getTexture("simon.png"),new Vector2f(0.4f,0f),new Vector2f(0.30f,0.39f));
        barry = new GUITexture(Resource.getTexture("benson.png"),new Vector2f(0.4f,-0.65f),new Vector2f(0.30f,0.39f));
        
        counterterror = new GUITexture(Resource.getTexture("simon.png"),new Vector2f(-0.8f,-0.65f),new Vector2f(0.30f,0.39f));
        beaver = new GUITexture(Resource.getTexture("beaver.png"),new Vector2f(-0.2f,-0.65f),new Vector2f(0.30f,0.39f));
        
        stalin.setLayer(-1f);
        simon.setLayer(-1f);
        kingbomb.setLayer(-1f);
        barry.setLayer(-1f);
        beaver.setLayer(-1f);
        counterterror.setLayer(-1f);
        characterselect.addItem("highlighter", highlighter);
        characterselect.addItem("stalin", stalin);
        characterselect.addItem("simon", simon);
        characterselect.addItem("bomb", kingbomb);
        characterselect.addItem("barry", barry);
        characterselect.addItem("beaver", beaver);
        characterselect.addItem("counterterror", counterterror);
        
        GUI.root.addItem("characterselect", characterselect);
        characterselect.enabled = true;
        BindController.addController(uc);
        this.attach(uc);
     //   GUI.root.getItem("sd")
    }
    public void update(float delta){
        pointerselect = Math.min(5, pointerselect);
        pointerselect = Math.max(0, pointerselect);
        
        highlighter.setPositionOffset(new Vector2f(-0.8f +( 0.6f*(pointerselect%3)),0f + pointerselect/3 * -0.65f));
    }
    @Override
    public void onAction(Action action) {
        if(action.type == ActionType.PRESS){
            System.out.println("sdf");
            switch(action.name){
                
                case "left":
                    System.out.println("left");
                    pointerselect--;
                    break;
                case "right":
                    System.out.println("right");
                    pointerselect++;
                  break;
            }
        }
    }
}
