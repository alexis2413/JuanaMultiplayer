/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.tile;

import java.io.Serializable;
import pe.edu.pucp.game.gfx.Assets;
/**
 *
 * @author alulab14
 */
@SuppressWarnings("serial")
public class SeaTile extends Tile implements Serializable{

    public SeaTile(int id) {
        super(Assets.sea1, id);
        solid = false;
    }

    public SeaTile() {
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}
