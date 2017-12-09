/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

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
        zone = new Zone(new AABB(100,100,10));
        zone.addSubscriber(this);
        mrp = new ModelRenderComponent();
    }

    @Override
    public void onTrigger(Trigger source, TriggerInfo info) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
