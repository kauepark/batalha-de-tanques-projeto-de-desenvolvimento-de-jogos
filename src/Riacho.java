import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Riacho {
    public final int RIVER_WIDTH = 55;
    public final int RIVER_LENGTH = 154;
    private final int x, y;
    BatalhaTanque batalhatanque ;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] riachoImagens = null;
    static {   
        riachoImagens = new Image[] {toolkit.getImage(ParedeComum.class.getResource("recursos/river.jpg"))};
    }
    public Riacho(int x, int y, BatalhaTanque batalhatanque) {   
        this.x = x;
        this.y = y;
        this.batalhatanque = batalhatanque;
    }
    public void draw(Graphics g) {
        g.drawImage(riachoImagens[0], x, y, null);          
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, RIVER_WIDTH, RIVER_LENGTH);
    }
}