import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Arvore {
    private final int x, y;
    BatalhaTanque batalhatanque ;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] arvoreImagens = null;
    static {arvoreImagens = new Image[] {
        toolkit.getImage(ParedeComum.class.getResource("recursos/tree.gif"))};
    }
    public Arvore(int x, int y, BatalhaTanque batalhatanque) {
        this.x = x;
        this.y = y;
        this.batalhatanque = batalhatanque;
    }
    public void draw(Graphics g) {        
        g.drawImage(arvoreImagens[0], x, y, null);
    }
}