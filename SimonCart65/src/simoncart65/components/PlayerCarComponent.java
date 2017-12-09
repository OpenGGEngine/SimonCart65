/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.engine.BindController;
import com.opengg.core.engine.WorldEngine;
import com.opengg.core.model.Model;
import com.opengg.core.world.Action;
import com.opengg.core.world.ActionType;
import com.opengg.core.world.Actionable;
import com.opengg.core.world.components.ModelRenderComponent;
import com.opengg.core.world.components.UserControlComponent;

/**
 *
 * @author Warren
 */
public class PlayerCarComponent extends CarComponent implements Actionable{
    
    UserControlComponent uc = new UserControlComponent();
    
    public PlayerCarComponent(Model m) {
        super(m);
        this.attach(uc);
        BindController.addController(uc);
        
    }

    @Override
    public void onAction(Action action) {
        if(action.type == ActionType.PRESS){
            switch(action.name){
                case "useitem":
                    WorldEngine.getCurrent().attach(new ModelRenderComponent(this.currentitem.display));
                    break;
            }
        }
    }
    
}
