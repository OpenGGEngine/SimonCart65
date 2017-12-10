/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.util.GGInputStream;
import com.opengg.core.util.GGOutputStream;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.Zone;
import com.opengg.core.world.components.physics.PhysicsComponent;
import com.opengg.core.world.components.triggers.Trigger;
import com.opengg.core.world.components.triggers.TriggerInfo;
import com.opengg.core.world.components.triggers.Triggerable;
import java.io.IOException;

import static simoncart65.components.RaceManagerComponent.p;

/**
 *
 * @author Warren
 */
public class ItemBoxSpawner extends Component implements Triggerable{
    public boolean istaken = true;
    ModelRenderComponent ms;
    int pointer = -1;
    
    public ItemBoxSpawner(){
        try {
            Model m = ModelLoader.loadNewModel("resources\\models\\teapot\\teapot.bmf");
            ms = new ModelRenderComponent(m);
            ms.setSerializable(false);

        
      Zone zone = new Zone(new AABB(20,20,20));
      
      zone.addSubscriber(this);
      zone.setSerializable(false);
      this.attach(zone);
        this.attach(ms);
        } catch (IOException ex) {
            System.out.println("no more");
        }
    }
    
    public ItemBoxSpawner(Model m){
        ms = new ModelRenderComponent(m);
        
      Zone zone = new Zone(new AABB(20,20,20));
      
      zone.addSubscriber(this);
      this.attach(zone);
        this.attach(ms);
    }
    @Override
    public void update(float delta){
        ms.setEnabled(istaken);
        if(pointer >0){
            pointer--;
        }else{
            istaken = true;
        }
    }

    @Override
    public void serialize(GGOutputStream out) throws IOException{
        super.serialize(out);
    }
    
    
    @Override
    public void deserialize(GGInputStream in) throws IOException{
        super.deserialize(in);
    }
    
    @Override
    public void onTrigger(Trigger source, TriggerInfo info) {
        System.out.println("resfadfas");
       if(info.data instanceof PlayerCarComponent){
           istaken = false;
          
           pointer = 500;
           if (p.currentitem == null) {
              
                  RaceManagerComponent.spinitem();
                            
           }
       }
    }
}
