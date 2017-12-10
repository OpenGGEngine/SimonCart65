/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.math.Vector3f;
import com.opengg.core.physics.Force;
import com.opengg.core.physics.collision.AABB;
import com.opengg.core.physics.collision.ColliderGroup;
import com.opengg.core.physics.collision.ConvexHull;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.physics.CollisionComponent;
import com.opengg.core.world.components.physics.PhysicsComponent;
import java.util.Iterator;

/**
 *
 * @author Warren
 */
public class ItemComponent extends Component{
    public Item item;
    public PhysicsComponent pc = new PhysicsComponent();
    ModelRenderComponent m;
    
    int charge = 30;
    public ItemComponent(Item item){
   
        this.item = item;
        this.m = new ModelRenderComponent(item.display);
        this.attach(m);
        
        pc.addCollider(new ColliderGroup(new AABB(10,10,3), new ConvexHull(item.display.ch)));
        this.attach(pc);
        
//          if(item instanceof ShellItem){
//            Force sd = new Force();
//            sd.force = new Vector3f();
//          //  pc.getEntity().velocity = new Vector3f(0.1f).add(RaceManagerComponent.p.p.getEntity().velocity);
//           // this.pc.getEntity().addForce(sd);
//        }
          
          m.setScaleOffset(item.scale);
    }
    
    public void update(float delta){
        Iterator<CarComponent> cp = RaceManagerComponent.racers.iterator();
        CarComponent top = null;
        float force = Float.NEGATIVE_INFINITY;
        float distance1 = 1;
        while(cp.hasNext()){
            CarComponent temp1 = cp.next();
            
            float distance = Vector3f.getDistance(temp1.getPosition(),this.getPosition());
            
            float temp = (this.charge * temp1.charge * 0.001f)/ (distance *distance);
            
            if(temp>force){
                force = temp;
                top = temp1;
                distance1 = distance;
            }
            
        }
       
       Vector3f ds =  ((top.getPosition().subtract(this.getPosition())).multiply(10/distance1));
         pc.getEntity().velocity = pc.getEntity().velocity.add(ds);
    }
   
    
}
