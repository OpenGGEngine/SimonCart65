/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.viewmodel.Element;
import com.opengg.core.world.components.viewmodel.ForComponent;
import com.opengg.core.world.components.viewmodel.Initializer;
import com.opengg.core.world.components.viewmodel.ViewModel;

/**
 *
 * @author Javier
 */
@ForComponent(FinishLine.class)
public class FinishLineViewModel extends ViewModel{

    @Override
    public void createMainViewModel() {

    }

    @Override
    public Initializer getInitializer(Initializer init) {
        return init;
    }

    @Override
    public Component getFromInitializer(Initializer init) {
        component =  new FinishLine();
        return component;
    }

    @Override
    public void onChange(Element element) {
        
    }

    @Override
    public void updateViews() {
        
    }
    
}
