/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.WorldEngine;
import com.opengg.core.math.Vector3f;
import com.opengg.core.model.Model;
import com.opengg.core.model.ModelManager;
import com.opengg.core.world.components.ModelRenderComponent;

/**
 *
 * @author Javier
 */
public class AICarComponent extends CarComponent{
    public AIFollow follow = new AIFollow();
    float fspeed = 10;
    public AICarComponent(Model m) {
        super(m);
        follow = new AIFollow();
        follow.c = this;
        WorldEngine.getCurrent().attach(follow);
        follow.attach(new ModelRenderComponent(ModelManager.getDefaultModel()));
    }
    
    @Override
    public void update(float delta){    
        super.update(delta);

        Vector3f diff = follow.getPosition().subtract(getPosition());

        Vector3f nextforce = f.force.reflect(diff);

        p.getEntity().velocity = diff.normalize().multiply(fspeed);
    }
}
