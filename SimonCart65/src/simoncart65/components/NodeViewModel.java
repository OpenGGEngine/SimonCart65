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
@ForComponent(Node.class)
public class NodeViewModel extends ViewModel{

    @Override
    public void createMainViewModel() {
        Element current = new Element();
        current.type = Element.Type.STRING;
        current.internalname = "current";
        current.name = "Current Node";
        current.autoupdate = true;
        current.value = "";
        
        Element next = new Element();
        next.type = Element.Type.STRING;
        next.internalname = "next";
        next.name = "Next Node";
        next.autoupdate = true;
        next.value = "";
        
        getElements().add(current);
        getElements().add(next);
    }

    @Override
    public Initializer getInitializer(Initializer init) {
        return init;
    }

    @Override
    public Component getFromInitializer(Initializer init) {
        component =  new Node();
        return component;
    }

    @Override
    public void onChange(Element element) {
        if(element.internalname.contains("current"))
            ((Node)component).pos = (String) element.value;
        if(element.internalname.contains("next"))
            ((Node)component).next = (String) element.value;
    }

    @Override
    public void updateViews() {
        
    }
    
}
