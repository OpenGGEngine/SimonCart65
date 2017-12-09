/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.util.GGInputStream;
import com.opengg.core.util.GGOutputStream;
import com.opengg.core.world.components.Component;
import java.io.IOException;

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
        model = ModelLoader.loadModel(in.readString());
        user = in.readBoolean();
    }
    
    public void spawn(){
        if(user){
           PlayerCarComponent c = new PlayerCarComponent(model);
        }else{
            CarComponent c = new CarComponent(model);
        }
    }
}
