package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.*;

import com.badlogic.gdx.math.Rectangle;


public class ObjPontos {

    public Rectangle corpo;

    public ObjPontos(float posix, float posy) {
        corpo = new Rectangle((int) posix, (int) posy, 10 , gap);
    }

    public int update(float time) {
        corpo.x += canovelx * time;
        if (corpo.x + corpo.width <= 0) {
            return 1;  // Sinaliza que o ponto saiu da tela
    }
        return 0;
    }
}
