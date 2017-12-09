/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.physics.Force;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.physics.collision.ColliderGroup;
import com.opengg.core.physics.collision.ConvexHull;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.physics.PhysicsComponent;

/**
 *
 * @author Warren
 */
public class CarComponent extends Component {
    public boolean last = false;
    public int lap = 0;
    public int raceposition;
    
    public Item currentitem = RaceManagerComponent.items[0];
    public ModelRenderComponent mc;
    public PhysicsComponent p; 
    public Force f;

    public CarComponent(Model m){
        mc = new ModelRenderComponent(m);
        p = new PhysicsComponent();
        p.addCollider(new ColliderGroup(new AABB(10,10,10), new ConvexHull(m.ch)));
        p.getEntity().addForce(f = new Force());
        
        this.attach(p);
        this.attach(mc);
    }
}
