import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BombaTanque {
    private final int x, y;
    private boolean live = true; 
    private BatalhaTanque batalhatanque;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] bombaTanqueImagens = {
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/1.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/2.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/3.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/4.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/5.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/6.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/7.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/8.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/9.gif")),
        toolkit.getImage(BombaTanque.class.getClassLoader().getResource("recursos/10.gif"))
    };
    int step = 0;
    public BombaTanque(int x, int y, BatalhaTanque batalhatanque) {
        this.x = x;
        this.y = y;
        this.batalhatanque = batalhatanque;
    }
    public void draw(Graphics g) { 
        if (!live) { 
            batalhatanque.tanquesBomba.remove(this);
            return;
        }
        if (step == bombaTanqueImagens.length) {
            live = false;
            step = 0;
            return;
        }
        g.drawImage(bombaTanqueImagens[step], x, y, null);
        step++;
    }
}