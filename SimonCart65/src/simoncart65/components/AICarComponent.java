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
    float fspeed = 0;
    float mspeed = 10;
    float accel = 5;
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
        if(diff.length()>follow.skill){
            fspeed += delta*accel*diff.length();
        }else if(getPosition().subtract(follow.getPosition()).length()<follow.skill){
            fspeed -= delta*(accel/5)*diff.length();
        }

        if(fspeed > mspeed) fspeed = mspeed;
        if(fspeed < 0) fspeed = 0;
        
        Vector3f nextforce = f.force.reflect(diff);

        p.getEntity().velocity = diff.normalize().multiply(fspeed);
    }
}
