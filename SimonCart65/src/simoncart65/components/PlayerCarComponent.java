/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.BindController;
import com.opengg.core.engine.WorldEngine;
import com.opengg.core.math.Quaternionf;
import com.opengg.core.math.Vector3f;
import com.opengg.core.math.Vector3fm;
import com.opengg.core.model.Model;
import com.opengg.core.world.Action;
import com.opengg.core.world.ActionType;
import com.opengg.core.world.Actionable;
import com.opengg.core.world.components.CameraComponent;
import com.opengg.core.world.components.UserControlComponent;

/**
 *
 * @author Warren
 */
public class PlayerCarComponent extends CarComponent implements Actionable{
    Vector3fm control = new Vector3fm();
    Vector3fm controlrot = new Vector3fm();
    UserControlComponent uc = new UserControlComponent();
    CameraComponent c;
    float forcelength = 5;
    float turnspeed = 40;
    
    public PlayerCarComponent(Model m) {
        super(m);
        this.attach(uc);
        BindController.addController(uc);
        c = new CameraComponent();
        c.setPositionOffset(new Vector3f(0,5,10));
        c.use();
        this.attach(c);
    }

    @Override
    public void update(float delta){
        super.update(delta);
        this.setRotationOffset(this.getRotation().multiply(new Quaternionf(new Vector3f(0,-control.x,0).multiply(delta).multiply(turnspeed))));
        f.force = getRotation().transform(new Vector3f(control).multiply(forcelength));
    }
    
    @Override
    public void onAction(Action action) {
        if(action.type == ActionType.PRESS){
            switch(action.name){
                case "useitem":
                    if(this.currentitem != null){
                    ItemComponent im = new ItemComponent(this.currentitem);
                    if(currentitem instanceof ShellItem){
                        im.setPositionOffset(p.getPosition().add(getRotation().transform(new Vector3f(0,0,-8))));
                        WorldEngine.getCurrent().attach(im);
                        
                        im.pc.getEntity().velocity = getRotation().transform(new Vector3f(0,0,-23));
                        WorldEngine.getCurrent().rescanRenderables();
                    }else if(currentitem instanceof BananaItem){
                        
                        System.out.println("banana");
                        im.setPositionOffset(p.getPosition().add(getRotation().transform(new Vector3f(0,4,-3))));
                        WorldEngine.getCurrent().attach(im);
                        
                        im.pc.getEntity().velocity = getRotation().transform(new Vector3f(0,0,6));
                        WorldEngine.getCurrent().rescanRenderables();
                    }else if(currentitem instanceof MushroomItem){
                          this.p.getEntity().velocity =(getRotation().transform(new Vector3f(0,0,-40)));
                    }
                    }
                    break;
                case "forward":
                    control.z -= 1;
                    break;
                case "backward":
                    control.z += 1;
                    break;
                case "left":
                    control.x -= 1;
                    break;
                case "right":
                    control.x += 1;
                    break;               
            }
        }if(action.type == ActionType.RELEASE){
            switch(action.name){
                case "useitem":
                    
                    break;
                case "forward":
                    control.z += 1;
                    break;
                case "backward":
                    control.z -= 1;
                    break;
                case "left":
                    control.x += 1;
                    break;
                case "right":
                    control.x -= 1;
                    break;               
            }
        }
    }
    
}
