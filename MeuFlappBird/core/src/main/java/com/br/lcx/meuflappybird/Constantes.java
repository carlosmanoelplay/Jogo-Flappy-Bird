package com.br.lcx.meuflappybird;

import com.badlogic.gdx.Gdx;

public class Constantes {

    public static  int screenx = Gdx.graphics.getWidth();
    public static int screeny = Gdx.graphics.getHeight();

    public static  float canovelx = -0.3f*screenx;

    public static int pasrad = (int)(0.06f*screenx);

    public static int pasInix = (int)(0.2f*screenx);

    public static float decVely = screeny/1.5f;
    public  static float impulso = screeny/5f;
    public static int canow = (int)(0.04f*screenx);

    public static float canosTimer = 3f;

    public static int posMax = (int)(0.7f*screeny);

    public static int gap = (int)(0.2f*screeny);
}
