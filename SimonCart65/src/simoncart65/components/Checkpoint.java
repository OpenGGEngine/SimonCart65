/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.Resource;
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

/**
 *
 * @author Javier
 */
public class Checkpoint extends Component implements Triggerable{
    Zone zone;
    ModelRenderComponent mrp;
    public Checkpoint(){
        zone = new Zone(new AABB(10,10,1));
        zone.addSubscriber(this);
        zone.setSerializable(false);
        mrp = new ModelRenderComponent(Resource.getModel("flafg"));
        mrp.setSerializable(false);
        this.attach(mrp);
        this.attach(zone);
    }

    @Override
    public void onTrigger(Trigger source, TriggerInfo info) {
        Component comp = (Component) info.data;
        if(comp instanceof CarComponent){
            checkLap((CarComponent) comp);
        }
    }
    
    public void checkLap(CarComponent c){
        if(!c.last){
            c.lap++;
            c.last = true;
        }
    }
    
    @Override
    public void serialize(GGOutputStream out) throws IOException{
        super.serialize(out);
        out.write(mrp.getName());
    }
    
    
    @Override
    public void deserialize(GGInputStream in) throws IOException{
        super.deserialize(in);
        mrp = new ModelRenderComponent(ModelLoader.loadModel(in.readString()));
    }
}
