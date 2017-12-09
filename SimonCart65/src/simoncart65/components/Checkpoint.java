/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.Resource;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.Zone;
import com.opengg.core.world.components.triggers.Trigger;
import com.opengg.core.world.components.triggers.TriggerInfo;
import com.opengg.core.world.components.triggers.Triggerable;

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
        mrp = new ModelRenderComponent(Resource.getModel("flafg"));
        this.attach(mrp);
        this.attach(zone);
    }

    @Override
    public void onTrigger(Trigger source, TriggerInfo info) {

    }
}
