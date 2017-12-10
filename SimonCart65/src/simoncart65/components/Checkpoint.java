/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.OpenGG;
import com.opengg.core.engine.WorldEngine;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.Zone;
import com.opengg.core.world.components.triggers.Trigger;
import com.opengg.core.world.components.triggers.TriggerInfo;
import com.opengg.core.world.components.triggers.Triggerable;
import simoncart65.SimonCart65;

/**
 *
 * @author Javier
 */
public class Checkpoint extends Component implements Triggerable{
    float radius = 5;
    Zone zone;
    int cid;
    
    public Checkpoint(){
        this(5, 1);
    }
    
    public Checkpoint(float rad, int id){
        radius = rad;
        zone = new Zone(new AABB(rad,rad,rad));
        zone.addSubscriber(this);
        zone.setSerializable(false);
        this.attach(zone);
        this.cid = id;
    }

    @Override
    public void onTrigger(Trigger source, TriggerInfo info) {
        Component comp = (Component) info.data;
        if(comp instanceof CarComponent){
            updateCar((CarComponent) comp);
        }
    }
    
    public void updateCar(CarComponent c){
        if(c.currentcheck == cid-1 || (cid==0 && c.currentcheck == ((SimonCart65)OpenGG.getApp()).mg.checkpoints-1)){
            OpenGG.asyncExec(()->{c.currentcheck = cid;});
        }
    }
    
    public static Checkpoint getById(int id){
        for(Component c : WorldEngine.getCurrent().getAll()){
            if(c instanceof Checkpoint){
                if(((Checkpoint)c).cid == id) return (Checkpoint) c;
            }
        }
        return null;
    }
    
    //public static List<Checkpoint> getOrdered(){
        //List<Checkpoint> checks
    //}
}
