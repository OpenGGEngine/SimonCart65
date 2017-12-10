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
    float speedmult = 10;
    float skill = 10;
    float currentpos = 0;
    
    @Override
    public void update(float delta){
        if(getPosition().subtract(getParent().getPosition()).length()>skill){
            speed += delta*10;
        }else if(getPosition().subtract(getParent().getPosition()).length()<skill){
            speed -= delta*10;
        }
        currentpos += speed*delta*0.01f;
        Vector2f npos = sc65.mg.path.getPoint(currentpos);
        this.setPositionOffset(new Vector3f(npos.x,-30,npos.y));
    }
}
