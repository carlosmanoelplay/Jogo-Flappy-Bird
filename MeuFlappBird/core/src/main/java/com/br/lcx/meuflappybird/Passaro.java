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

    public Passaro(int posix, int posy){
        corpo = new Circle(posix, posy, pasrad);

        frames = new Texture[6];
        for (int i = 1; i <= 6; i++){
            frames[i - 1] = new Texture("felpudo/felpudoVoa" + i + ".png");
        }

        velocidade = new Vector2(0, 0);
    }

    public void draw(SpriteBatch batch){
        int frameIndex = (int) (auxFrames % 6);  // Calcula o índice do frame atual corretamente
        batch.draw(frames[frameIndex], corpo.x - corpo.radius, corpo.y - corpo.radius, corpo.radius * 2, corpo.radius * 2);
    }

    public void update(float time){
        auxFrames += 6 * time;  // Incrementa o auxiliar para animação

        // Atualiza a posição do pássaro e aplica a gravidade
        corpo.x += velocidade.x * time;
        corpo.y += velocidade.y * time;

        // Aplica gravidade
        velocidade.y -= decVely * time;

        // Limita o pássaro ao topo da tela
        if (corpo.y + corpo.radius >= screeny) {
            corpo.y = screeny - corpo.radius;  // Posiciona o pássaro exatamente no topo
            velocidade.y = Math.min(velocidade.y, 0);  // Permite gravidade, mas impede impulso contínuo no topo
        } else if (corpo.y - corpo.radius <= 0) {
            // Limita o pássaro ao chão da tela
            corpo.y = corpo.radius;
            velocidade.y = 0;
        }
    }

    public void impulso() {
        velocidade.y = impulso;  // Define a velocidade de impulso
    }

    public void dispose(){
        for (int i = 0; i < 6; i++){
            frames[i].dispose();
        }
    }
}
