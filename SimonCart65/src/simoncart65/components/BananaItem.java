/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.math.Vector3f;
import com.opengg.core.render.texture.Texture;

/**
 *
 * @author Warren
 */
public class BananaItem extends Item{
    
    public BananaItem(Texture t, String modelpath) {
        super(t, modelpath, new Vector3f(0.26f));
    }
    
}