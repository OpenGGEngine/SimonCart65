/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.math.Vector3f;
import com.opengg.core.model.Model;
import com.opengg.core.model.ModelLoader;
import com.opengg.core.render.texture.Texture;
import java.io.IOException;


/**
 *
 * @author Warren
 */
public class Item {
    public Texture t;
    public Model display;
    public Vector3f scale;
    
    public Item(Texture t,String modelpath, Vector3f scale){
        this.t = t;
        try {
            this.display = ModelLoader.loadNewModel(modelpath);
        } catch (IOException ex) {
            
        }
        this.scale = scale;
    }
    
    public  void use(){
    };
}
