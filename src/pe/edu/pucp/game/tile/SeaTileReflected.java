/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.game.tile;

import pe.edu.pucp.game.gfx.Assets;

/**
 *
 * @author alulab14
 */
public class SeaTileReflected extends Tile{
    public SeaTileReflected(int id) {
        super(Assets.sea1Juana, id);
        solid = true;
    }

    public SeaTileReflected() {
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}
