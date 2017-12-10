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
public class ItemBoxSpawner extends Component{
    public boolean istaken;
    ModelRenderComponent ms;
    public ItemBoxSpawner(Model m){
        ms = new ModelRenderComponent(m);
        
    }
    @Override
    public void update(float delta){
        ms.setEnabled(false);
    }
}
