import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class ParedeComum {
    private final int PAREDE_COMUM_WIDTH = 22; 
    private final int PAREDE_COMUM_LENGTH = 21;
    private final int x, y;
    BatalhaTanque batalhatanque;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] paredeImagens = null;
    static {
        paredeImagens = new Image[] {toolkit.getImage(ParedeComum.class.getResource("recursos/commonWall.gif"))};
    }
    public ParedeComum(int x, int y, BatalhaTanque batalhatanque) { 
        this.x = x;
        this.y = y;
        this.batalhatanque = batalhatanque;
    }
    public void draw(Graphics g) {
        g.drawImage(paredeImagens[0], x, y, null);
    }
    public Rectangle getRect() {  
        return new Rectangle(x, y, PAREDE_COMUM_WIDTH, PAREDE_COMUM_LENGTH);
    }
}