package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainClass extends ApplicationAdapter {

    private SpriteBatch batch;
    private Fundo fundo;
    private Passaro passaro;
    private List<Cano> canos;
    private List<ObjPontos> objPontos;

    private float canoTimer;
    private int estado = 0;  // 1 para jogo rodando, 0 para jogo pausado, 2 para game over, 3 para reiniciar
    private int pontos = 0;
    private boolean marcou = false;
    private BitmapFont font;
    private GlyphLayout glyphLayout = new GlyphLayout();


    @Override
    public void create() {
        batch = new SpriteBatch();
        fundo = new Fundo();
        passaro = new Passaro(pasInix, screeny / 2);
        canos = new ArrayList<>();
        objPontos = new ArrayList<ObjPontos>();
        canoTimer = canosTimer; // Timer para controlar a criação de canos

        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size =(int)(0.2f*screenx);
        parameter.color = new Color(1, 1, 1, 1);
        font = generator.generateFont(parameter);
        generator.dispose();
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
        font.draw(batch, String.valueOf(pontos),
                (screenx - getTamx(font, String.valueOf(pontos)))/2,
                0.98f*screeny);
    }

    private void update(float time) {
     if (estado == 1) {
         fundo.update(time);

         // Atualizar a posição dos canos e remover os que saíram da tela
         for (int i = 0; i < canos.size(); i++) {
             if (canos.get(i).update(time) == 1) {
                 canos.remove(i);
                 i--;
             }
         }
         for (int i = 0; i < objPontos.size(); i++) {
             if (objPontos.get(i).update(time) == 1) {
                 objPontos.remove(i);
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
             objPontos.add(new ObjPontos(screenx + canow + 2*pasrad, screeny/2 + pos - gap/2));
             canoTimer = canosTimer;
         }


         for (Cano c : canos) {
             if (Intersector.overlaps(passaro.corpo, c.getCorpo())) {
                 passaro.perdeu();
                 estado = 2;
             }
         }
     }
     boolean inter = false;
     for (ObjPontos op : objPontos) {
         if (Intersector.overlaps(passaro.corpo, op.corpo)) {
            if (!marcou) {
                pontos++;
                Gdx.app.log("Pontos", String.valueOf(pontos));
                marcou = true;
            }
            inter = true;
         }
         if (!inter) marcou = false;
     }
     if (estado == 1 || estado == 2) {
         passaro.update(time);
         if (passaro.update(time) == 1) {
             estado = 3;
         }
     }

    }

    private void input() {
        if (Gdx.input.justTouched()) {
         if (estado == 0){
             estado = 1;
         }
         if (estado == 1){
             passaro.impulso();
         } else if (estado == 3 ) {
             estado = 1;
             passaro.reiniciar(pasInix, screeny / 2);
             canos.clear();
             canoTimer = canosTimer;
             pontos = 0;
             marcou = false;
             objPontos.clear();
         }
        }
    }
    private float getTamx(BitmapFont font , String texto){
        glyphLayout.reset();
        glyphLayout.setText(font, texto);
        return glyphLayout.width;
    }

    @Override
    public void dispose() {
        fundo.dispose();
        passaro.dispose();
        for (Cano c : canos) {
            c.dispose();
        }
        batch.dispose();
        font.dispose();
    }
}
