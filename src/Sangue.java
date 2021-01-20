import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Sangue {
    public final int SANGUE_WIDTH = 34;
    public final int SANGUE_LENGTH = 30;
    private int x, y;
    BatalhaTanque batalhatanque;
    private static Random r = new Random();
    int passo = 0; 
    private boolean vivo = false;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] sangueImagens = null;
    static {
        sangueImagens = new Image[] {toolkit.getImage(ParedeComum.class.getResource("recursos/hp.png"))};
    }
    private int[][] posicao = {{700, 196}, {500, 58}, {80, 300}, {600,321}, {345, 456}, {123, 321}, {258, 413}};
    public void draw(Graphics g) {
        if (r.nextInt(100) > 98) {
            this.vivo = true;
            move();
        }
        if (!vivo) {
            return;
        }
        g.drawImage(sangueImagens[0], x, y, null);
    }
    private void move() {
        passo++;
        if (passo == posicao.length) {
            passo = 0;
        }
        x = posicao[passo][0];
        y = posicao[passo][1];
    }
    public Rectangle getRect() { 
        return new Rectangle(x, y, SANGUE_WIDTH, SANGUE_LENGTH);
    }
    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {  
        this.vivo = vivo;
    }
}