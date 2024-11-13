package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.pasInix;
import static com.br.lcx.meuflappybird.Constantes.screeny;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Fundo fundo;

    private Passaro passaro;

    @Override
    public void create() {
        batch = new SpriteBatch();
        fundo = new Fundo();

        passaro = new Passaro(pasInix, screeny/2);

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
    private void draw(SpriteBatch batch){
        fundo.draw(batch);
        passaro.draw(batch);

    }
    private void update (float time){
        fundo.update(time);
        passaro.update(time);
    }
    private void input(){
        if (Gdx.input.justTouched()){
            passaro.impulso();
        }


    }


    @Override
    public void dispose() {
        fundo.dispose();
        passaro.dispose();
        batch.dispose();

    }
}
