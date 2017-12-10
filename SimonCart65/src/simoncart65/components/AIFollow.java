/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.math.Vector2f;
import com.opengg.core.math.Vector3f;
import com.opengg.core.world.components.Component;
import static simoncart65.SimonCart65.sc65;

/**
 *
 * @author Javier
 */
public class AIFollow extends Component{
    float speed = 10;
    float speedmult = 0.3f;
    float skill = 10;
    float currentpos = 0;
    public AICarComponent c;
    
    @Override
    public void update(float delta){
        Vector3f diff = getPosition().subtract(c.getPosition());
        if(diff.length()>skill){
            speed -= delta*speedmult*diff.length();
        }else if(getPosition().subtract(c.getPosition()).length()<skill){
            speed += delta*speedmult*diff.length();
        }
 
       if(currentpos > 1) currentpos  = 0;
        if(speed < 1) speed = 1f;
        currentpos += speed*delta*0.01f*(100/sc65.mg.path.getLength());
        Vector2f npos = sc65.mg.path.getPoint(currentpos);
        this.setPositionOffset(new Vector3f(npos.x,-30,npos.y));
    }
}
