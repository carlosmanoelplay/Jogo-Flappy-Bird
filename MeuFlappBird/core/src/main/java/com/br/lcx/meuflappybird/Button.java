package com.br.lcx.meuflappybird;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Button {
        private Rectangle but;
        private boolean high = false;
        private float hghf = 1.1f;
        private Texture texture;

        public Button(Texture texture, int posx, int posy, int size) {
            this.texture = texture;
            but = new Rectangle(posx, posy, size, size);
        }

        public void draw(SpriteBatch batch) {
            if (high) {
                batch.draw(texture, but.x - (but.getWidth() * (hghf - 1)) / 2,
                        but.y - (but.getHeight() * (hghf - 1)) / 2,
                        but.getWidth() * hghf, but.getHeight() * hghf);
            } else {
                batch.draw(texture, but.x, but.y, but.getWidth(), but.getHeight());
            }
        }

        public boolean verificar(int x, int y) {
            high = but.contains(x, y);
            return high;
        }

        public boolean isHigh() {  // Certifique-se de que este método seja público
            return high;
        }

        public void resetHigh() {  // Certifique-se de que este método seja público
            high = false;
        }
    }

