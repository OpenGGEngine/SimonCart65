/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.ModelRenderComponent;

/**
 *
 * @author Warren
 */
public class CarComponent extends Component{
    
    public ModelRenderComponent mc;
    
    public CarComponent(Model m){
        mc = new ModelRenderComponent(m);
        this.attach(mc);
    }
}
