package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Fundo fundo;
    private Passaro passaro;
    private List<Cano> canos;

    private float canoTimer;


    @Override
    public void create() {
        batch = new SpriteBatch();
        fundo = new Fundo();
        passaro = new Passaro(pasInix, screeny / 2);
        canos = new ArrayList<>();
        canoTimer = canosTimer; // Timer para controlar a criação de canos

    }

    @Override
    public void render() {
        input();
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        draw(batch);
        batch.end();
    }

    private void draw(SpriteBatch batch) {
        fundo.draw(batch);
        for (Cano c : canos) {
            c.draw(batch);
        }
        passaro.draw(batch);
    }

    private void update(float time) {
        fundo.update(time);

        // Atualizar a posição dos canos e remover os que saíram da tela
        for (int i = 0; i < canos.size(); i++) {
            if (canos.get(i).update(time) == 1) {
                canos.remove(i);
                i--;
            }
        }

        // Adicionar um novo cano a cada 2 segundos
        canoTimer -= time;
        if (canoTimer <= 0) {
            Random random = new Random();
            int pos = random.nextInt(posMax);
            pos -= posMax/2;
            canos.add(new Cano(screenx, screeny/2 + pos + gap/2, true));
            canos.add(new Cano(screenx, screeny/2 + pos - gap/2, false));
            canoTimer = canosTimer;
        }

        passaro.update(time);
    }

    private void input() {
        if (Gdx.input.justTouched()) {
            passaro.impulso();
        }
    }

    @Override
    public void dispose() {
        fundo.dispose();
        passaro.dispose();
        for (Cano c : canos) {
            c.dispose();
        }
        batch.dispose();
    }
}
