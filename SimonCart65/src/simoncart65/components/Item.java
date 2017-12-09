/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.model.Model;
import com.opengg.core.render.texture.Texture;

/**
 *
 * @author Warren
 */
public class Item {
    public Texture t;
    public Model display;
    
    public Item(Texture t,Model display){
        this.t = t;
        this.display = display;
    }
}
