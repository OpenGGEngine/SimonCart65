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
import static simoncart65.SimonCart65.sc65;

/**
 *
 * @author Warren
 */
public class CarComponent extends Component implements Comparable{
    public boolean last = false;
    public int lap = 0;
    public int currentcheck = 0;
    public int raceposition = 0;
    public boolean dtock = false;
    public boolean tick = false;
    
    public Item currentitem = null;
    public ModelRenderComponent mc;
    public PhysicsComponent p; 
    public Force f;

    public CarComponent(Model m){
        mc = new ModelRenderComponent(m);
        p = new PhysicsComponent();
        p.addCollider(new ColliderGroup(new AABB(10,10,10), new ConvexHull(m.ch)));
        p.getEntity().addForce(f = new Force());
        f.velLimit = 3f;
        this.attach(p);
        this.attach(mc);
    }
    
    @Override
    public void update(float delta){
        p.getEntity().velocity = p.getEntity().velocity.subtract(p.getEntity().velocity.multiply(0.8f).multiply(delta));
        if(!tick){
            last = false;
        }
        if(tick){
            tick = false;
        }
        
        
    }

    @Override
    public int compareTo(Object o) {
        CarComponent c = (CarComponent) o;
        if(c.lap < lap) return 1;
        else if(c.lap > lap) return -1;
        else{
            if(c.currentcheck<currentcheck) return 1;
            else if(c.currentcheck>currentcheck) return -1;
            else{
                Checkpoint cp = Checkpoint.getById(currentcheck==sc65.mg.checkpoints-1?0:currentcheck+1);
                if(c.getPosition().subtract(cp.getPosition()).length()>
                        getPosition().subtract(cp.getPosition()).length()) return 1;
                return -1;
            }
        }
    }
}
