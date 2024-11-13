package com.br.lcx.meuflappybird;

import static com.br.lcx.meuflappybird.Constantes.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Passaro {
    public Circle corpo;
    private Texture[] frames;
    private float auxFrames;
    private Passaro passaro;

    public Passaro(int posx, int posy){
        corpo = new Circle(posx, posy,pasrad);

        frames = new Texture[6];
        for (int i =1; i<=6; i++){
            frames[i-1] = new Texture("felpudo/felpudovoa"+ i + ".png");
        }
   }
    public void draw(SpriteBatch batch){
        batch.draw(frames[(int) auxFrames%6], corpo.x - corpo.radius, corpo.y - corpo.radius, corpo.radius*2,corpo.radius*2);
    }
    public void update(float time){
        auxFrames += 6*time;


    }

    public void dispose(){
        for (int i=0; i<5; i++){
            frames[i].dispose();
        }
    }

}
