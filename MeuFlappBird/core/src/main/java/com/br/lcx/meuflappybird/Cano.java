package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Cano {
    private static Texture tex;
    private Rectangle corpo;
    private boolean cima;

    public Cano(float posx, float posy, boolean cima) {
        this.cima = cima;

        if (tex == null) {  // Carrega a textura uma vez para todos os canos
            tex = new Texture("cano.png");
        }

        if (cima) {
            corpo = new Rectangle(posx, posy, canow, screeny);
        } else {
            corpo = new Rectangle(posx, posy - screeny, canow, screeny);
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(tex, corpo.x, corpo.y, corpo.width, corpo.height,
                0, 0, tex.getWidth(), tex.getHeight(), false, cima);
    }

    public int update(float time) {
        corpo.x += canovelx * time;

        if (corpo.x + corpo.width <= 0) {
            return 1;  // Sinaliza que o cano saiu da tela
        }
        return 0;
    }

    public void dispose() {
        if (tex != null) {
            tex.dispose();
            tex = null;  // Libera a textura uma vez quando todos os canos não forem mais necessários
        }
    }
}