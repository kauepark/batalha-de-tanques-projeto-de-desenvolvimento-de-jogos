import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Casa {
    private final int CASA_WIDTH = 43, CASA_LENGTH = 43;
    private final int x, y;
    private BatalhaTanque batalhatanque;
    private boolean live = true;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit(); 
    private static Image[] casaImagens = null;
    static {
        casaImagens = new Image[] {toolkit.getImage(ParedeComum.class.getResource("recursos/home.jpg"))};
    }
    public Casa(int x, int y, BatalhaTanque batalhatanque) {
        this.x = x;
        this.y = y;
        this.batalhatanque = batalhatanque; 
    }
    public void gameOver(Graphics g) {
        batalhatanque.tanques.clear();
        batalhatanque.paredesMetal.clear();
        batalhatanque.outrasParedes.clear();
        batalhatanque.tanquesBomba.clear();
        batalhatanque.riachos.clear();
        batalhatanque.arvores.clear();
        batalhatanque.projeteis.clear();
        batalhatanque.tanque1.setVivo(false);
        Color c = g.getColor(); 
        g.setColor(Color.green);
        Font f = g.getFont();
        g.setFont(new Font(" ", Font.PLAIN, 40));
        g.setFont(f);
        g.setColor(c);
    }
    public void draw(Graphics g) {
        if (live) { 
            g.drawImage(casaImagens[0], x, y, null);
            for (int i = 0; i < batalhatanque.paredesCasa.size(); i++) {
                ParedeComum w = batalhatanque.paredesCasa.get(i);
                w.draw(g);
            }
        } else {
            gameOver(g);
        }
    }
    public boolean isLive() { 
        return live;
    }
    public void setLive(boolean live) { 
        this.live = live;
    }
    public Rectangle getRect() { 
        return new Rectangle(x, y, CASA_WIDTH, CASA_LENGTH);
    }
}