/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.OpenGG;
import com.opengg.core.engine.WorldEngine;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.util.GGInputStream;
import com.opengg.core.util.GGOutputStream;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.Zone;
import com.opengg.core.world.components.triggers.Trigger;
import com.opengg.core.world.components.triggers.TriggerInfo;
import com.opengg.core.world.components.triggers.Triggerable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        this(0, 0);
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
    
    public static List<Checkpoint> getOrdered(){
       List<Checkpoint> checks = new ArrayList<>();
       int i = 0;
       while(true){
           Checkpoint c = getById(i);
           if(c != null)
            checks.add(c);
           else
               return checks;
           
           i++;
       }
    }
    
    @Override
    public void serialize(GGOutputStream out) throws IOException{
        super.serialize(out);
        out.write(radius);
        out.write(cid);
    }
    
    @Override
    public void deserialize(GGInputStream in) throws IOException{
        super.deserialize(in);
        radius = in.readFloat();
        this.remove(zone);
        zone = new Zone(new AABB(radius,radius,radius));
        zone.addSubscriber(this);
        zone.setSerializable(false);
        this.attach(zone);
        cid = in.readInt();
    }
}
