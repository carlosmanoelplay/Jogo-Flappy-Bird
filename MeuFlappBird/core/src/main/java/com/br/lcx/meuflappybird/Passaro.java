package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Passaro {
    public Circle corpo;
    private final Texture[] frames;
    private float auxFrames;
    private Vector2 velocidade;

    public Passaro(int posix, int posy) {
        corpo = new Circle(posix, posy, pasrad);
        frames = new Texture[6];
        for (int i = 1; i <= 6; i++) {
            frames[i - 1] = new Texture("felpudo/felpudoVoa" + i + ".png");
        }
        velocidade = new Vector2(0, 0);
    }

    public void draw(SpriteBatch batch) {
        int frameIndex = (int) (auxFrames % 6);
        batch.draw(frames[frameIndex], corpo.x - corpo.radius, corpo.y - corpo.radius, corpo.radius * 2, corpo.radius * 2);
    }

    public void update(float time) {
        auxFrames += 6 * time;
        velocidade.y -= decVely * time;
        corpo.y += velocidade.y * time;

        // Limites da tela
        if (corpo.y + corpo.radius >= screeny) {
            corpo.y = screeny - corpo.radius;
            velocidade.y = 0;
        }
        if (corpo.y - corpo.radius <= 0) {
            corpo.y = corpo.radius;
            velocidade.y = 0;
        }
    }

    public void impulso() {
        velocidade.y = impulso;
    }

    public void dispose() {
        for (Texture frame : frames) {
            frame.dispose();
        }
    }
}
