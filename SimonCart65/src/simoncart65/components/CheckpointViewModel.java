/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.math.Vector3f;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.viewmodel.Element;
import com.opengg.core.world.components.viewmodel.Initializer;
import com.opengg.core.world.components.viewmodel.ViewModel;

/**
 *
 * @author Javier
 */
public class CheckpointViewModel extends ViewModel{

    @Override
    public void createMainViewModel() {
        Element rad = new Element();
        rad.type = Element.FLOAT;
        rad.internalname = "radius";
        rad.name = "Collider Radius";
        rad.autoupdate = true;
        rad.value = 5f;
        
        Element cid = new Element();
        cid.type = Element.INTEGER;
        cid.internalname = "cid";
        cid.name = "Checkpoint ID";
        cid.autoupdate = true;
        cid.value = 0;
    }

    @Override
    public Initializer getInitializer(Initializer init) {
        return init;
    }

    @Override
    public Component getFromInitializer(Initializer init) {
        component =  new Checkpoint();
        return component;
    }

    @Override
    public void onChange(Element element) {
        if(element.internalname.equalsIgnoreCase("radius"))
            ((Checkpoint)component).zone.getBox().setLWH(new Vector3f((float) element.value));
        else if(element.internalname.equalsIgnoreCase("cid"))
            ((Checkpoint)component).cid = (int) element.value;
    }

    @Override
    public void updateViews() {
        
    }
    
}
