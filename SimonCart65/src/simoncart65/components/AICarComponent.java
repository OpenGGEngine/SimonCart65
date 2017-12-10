/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.math.Vector3f;
import com.opengg.core.model.Model;

/**
 *
 * @author Javier
 */
public class AICarComponent extends CarComponent{
    AIFollow follow = new AIFollow();
    float fspeed = 10;
    public AICarComponent(Model m) {
        super(m);
        follow = new AIFollow();
        attach(follow);
    }
    
    @Override
    public void update(float delta){    
        Vector3f diff = follow.getPosition().subtract(getPosition());
        Vector3f nextforce = f.force.reflect(diff);
        f.force = nextforce.normalize().multiply(fspeed);
    }
}
