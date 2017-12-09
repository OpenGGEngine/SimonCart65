/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.physics.collision.AABB;
import com.opengg.core.physics.collision.ColliderGroup;
import com.opengg.core.physics.collision.ConvexHull;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.physics.CollisionComponent;
import com.opengg.core.world.components.physics.PhysicsComponent;

/**
 *
 * @author Warren
 */
public class ItemComponent extends Component{
    public Item item;
    public PhysicsComponent pc = new PhysicsComponent();
    ModelRenderComponent m;
    public ItemComponent(Item item){
        this.item = item;
        this.m = new ModelRenderComponent(item.display);
        this.attach(m);
        
        pc.addCollider(new ColliderGroup(new AABB(10,10,3), new ConvexHull(item.display.ch)));
        this.attach(pc);
    }
}
