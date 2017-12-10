/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.model.ModelManager;
import com.opengg.core.world.components.Component;
import com.opengg.core.world.components.viewmodel.Element;
import com.opengg.core.world.components.viewmodel.Initializer;
import com.opengg.core.world.components.viewmodel.ViewModel;

/**
 *
 * @author Javier
 */
public class CarSpawnerViewModel extends ViewModel{

    @Override
    public void createMainViewModel() {
        Element model = new Element();
        model.type = Element.MODEL;
        model.internalname = "model";
        model.name = "Car Model";
        model.autoupdate = true;
        model.value = ModelManager.getDefaultModel();
        
        Element user = new Element();
        user.type = Element.BOOLEAN;
        user.internalname = "user";
        user.name = "Is user controlled";
        user.autoupdate = true;
        user.value = false;
        
        getElements().add(model);
        getElements().add(user);
    }

    @Override
    public Initializer getInitializer(Initializer init) {
        Element model = new Element();
        model.type = Element.MODEL;
        model.internalname = "model";
        model.name = "Car Model";
        model.autoupdate = true;
        model.value = ModelManager.getDefaultModel();
        init.addElement(model);
        return init;
    }

    @Override
    public Component getFromInitializer(Initializer init) {
        Model m = (Model) init.get("model").value;
        component =  new CarSpawner(m, true);
        return component;
    }

    @Override
    public void onChange(Element element) {
        if(element.internalname.equals("model"))
            ((CarSpawner)component).model = (Model) element.value;
        if(element.internalname.equals("user"))
            ((CarSpawner)component).user =  (boolean) element.value;
    }

    @Override
    public void updateViews() {
        this.getByName("user").value = ((CarSpawner)component).user;
    }
    
}
