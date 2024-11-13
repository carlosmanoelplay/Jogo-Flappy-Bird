package com.br.lcx.meuflappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Fundo fundo;

    @Override
    public void create() {
        batch = new SpriteBatch();
        fundo = new Fundo();

    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        draw(batch);

        batch.end();

    }
    private void draw(SpriteBatch batch){
        fundo.draw(batch);

    }
    private void update (float time){
        fundo.update(time);
    }


    @Override
    public void dispose() {
        fundo.dispose();

    }
}
