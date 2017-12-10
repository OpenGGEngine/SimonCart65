/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.Zone;
import com.opengg.core.world.components.physics.PhysicsComponent;
import com.opengg.core.world.components.triggers.Trigger;
import com.opengg.core.world.components.triggers.TriggerInfo;
import com.opengg.core.world.components.triggers.Triggerable;
import static simoncart65.components.RaceManagerComponent.p;

/**
 *
 * @author Warren
 */
public class ItemBoxSpawner extends Component implements Triggerable{
    public boolean istaken = true;
    ModelRenderComponent ms;
    PhysicsComponent pc = new PhysicsComponent();
    int pointer = -1;
    public ItemBoxSpawner(Model m){
        ms = new ModelRenderComponent(m);
        this.attach(pc);
        
      Zone zone = new Zone(new AABB(20,20,20));
      
      zone.addSubscriber(this);
      this.attach(zone);
        this.attach(ms);
    }
    @Override
    public void update(float delta){
        System.out.println(istaken);
        ms.setEnabled(istaken);
        if(pointer >0){
            pointer--;
        }else{
            istaken = true;
        }
    }

    @Override
    public void onTrigger(Trigger source, TriggerInfo info) {
       if(info.data instanceof PlayerCarComponent){
           istaken = false;
           pointer = 500;
           if (p.currentitem == null) {
                  RaceManagerComponent.spinitem();
                            
           }
       }
    }
}
