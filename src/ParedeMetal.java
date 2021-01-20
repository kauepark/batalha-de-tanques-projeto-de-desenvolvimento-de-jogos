import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class ParedeMetal {
    public final int PAREDE_METAL_WIDTH = 36; 
    public final int PAREDE_METAL_LENGTH = 37;
    private final int x, y;
    BatalhaTanque batalhatanque;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] paredeImagens = null;
    static {
        paredeImagens = new Image[] {toolkit.getImage(ParedeComum.class.getResource("recursos/metalWall.gif"))};
    }
    public ParedeMetal(int x, int y, BatalhaTanque batalhatanque) {
        this.x = x;
        this.y = y;
        this.batalhatanque = batalhatanque;
    }
    public void draw(Graphics g) { 
        g.drawImage(paredeImagens[0], x, y, null);
    }
    public Rectangle getRect() { 
        return new Rectangle(x, y, PAREDE_METAL_WIDTH, PAREDE_METAL_LENGTH);
    }
}