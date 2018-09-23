/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.Resource;
import com.opengg.core.model.Model;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.util.GGInputStream;
import com.opengg.core.util.GGOutputStream;
import com.opengg.core.world.WorldEngine;
import com.opengg.core.world.components.Component;
import java.io.IOException;
import static simoncart65.SimonCart65.sc65;

/**
 *
 * @author Javier
 */
public class CarSpawner extends Component{
    Model model;
    boolean user = false;
    
    public CarSpawner(){
        
    }
    
    public CarSpawner(Model m, boolean user){
        this.model = m;
        this.user = user;
    }
    
    @Override
    public void serialize(GGOutputStream out) throws IOException{
        super.serialize(out);
        out.write(model.getName());
        out.write(user);
    }
    
    
    @Override
    public void deserialize(GGInputStream in) throws IOException{
        super.deserialize(in);
        model = Resource.getModel(in.readString());
        user = in.readBoolean();
    }
    
    public void spawn(){
        AICarComponent c = new AICarComponent(model);
        c.charge = 10;
        c.setPositionOffset(this.getPosition());
        RaceManagerComponent.racers.add(c);
        WorldEngine.getCurrent().attach(c);
    }
    
    public void spawnOther()
    {
        PlayerCarComponent c = new PlayerCarComponent(model);
           RaceManagerComponent.racers.add(c);
           c.setPositionOffset(this.getPosition());
           WorldEngine.getCurrent().attach(c);
           RaceManagerComponent.p = c;
    }
}
