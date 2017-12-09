/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simoncart65.components;

import com.opengg.core.util.GGInputStream;
import com.opengg.core.util.GGOutputStream;
import com.opengg.core.world.components.Component;
import java.io.IOException;

/**
 *
 * @author Javier
 */
public class Node extends Component{
    String pos = "";
    String next = "";
    
    public Node(){
        
    }
    
    public Node(String pos, String next){
        this.pos = pos;
        this.next = next;
    }
    
    @Override
    public void serialize(GGOutputStream out) throws IOException{
        super.serialize(out);
        out.write(pos);
        out.write(next);
    }
    
    
    @Override
    public void deserialize(GGInputStream in) throws IOException{
        super.deserialize(in);
        pos = in.readString();
        next = in.readString();
    }
}
