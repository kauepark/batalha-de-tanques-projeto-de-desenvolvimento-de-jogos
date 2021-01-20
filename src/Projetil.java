import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Projetil {
    public static final int PROJETIL_WIDTH = 10;
    public static final int PROJETIL_LENGTH = 10;
    private int x, y;
    public static int speedX = 12;
    public static int speedY = 12;
    Controle direcao;
    private boolean good;
    private boolean vivo = true;
    private BatalhaTanque batalhatanque;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] projetilImages = null;
    private static Map<String, Image> projetilMap = new HashMap<String, Image>(); 
    static {
        projetilImages = new Image[] {toolkit.getImage(Projetil.class.getClassLoader().getResource("recursos/bulletL.gif")), toolkit.getImage(Projetil.class.getClassLoader().getResource("recursos/bulletU.gif")), toolkit.getImage(Projetil.class.getClassLoader().getResource("recursos/bulletR.gif")), toolkit.getImage(Projetil.class.getClassLoader().getResource("recursos/bulletD.gif"))};
        projetilMap.put("L", projetilImages[0]); 
        projetilMap.put("U", projetilImages[1]);
        projetilMap.put("R", projetilImages[2]);
        projetilMap.put("D", projetilImages[3]);
    }
    public Projetil(int x, int y, Controle dir) {
        this.x = x;
        this.y = y;
        this.direcao = dir;
    }
    public Projetil(int x, int y, boolean good, Controle dir, BatalhaTanque batalhatanque) {
        this(x, y, dir);
        this.good = good;
        this.batalhatanque = batalhatanque;
    }
    private void move() {
        switch (direcao) {
        case L:
            x -= speedX;
            break;
        case U:
            y -= speedY;
            break;
        case R:
            x += speedX; 
            break;
        case D:
            y += speedY;
            break;
        case STOP:
            break;
        }
        if (x < 0 || y < 0 || x > BatalhaTanque.FRAME_WIDTH || y > BatalhaTanque.FRAME_LENGTH) {
            vivo = false;
        }
    }
    public void draw(Graphics g) {
        if (!vivo) {
            batalhatanque.projeteis.remove(this);
            return;
        }
        switch (direcao) { 
        case L:
            g.drawImage(projetilMap.get("L"), x, y, null);
            break;
        case U:
            g.drawImage(projetilMap.get("U"), x, y, null);
            break;
        case R:
            g.drawImage(projetilMap.get("R"), x, y, null);
            break;
        case D:
            g.drawImage(projetilMap.get("D"), x, y, null);
            break;
        }
        move();
    }
    public boolean isVivo() { 
        return vivo;
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, PROJETIL_WIDTH, PROJETIL_LENGTH);
    }
    public boolean hitTanques(List<Tanque> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            if (hitTanque(tanks.get(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean hitTanque(Tanque t) { 
        if (this.vivo && this.getRect().intersects(t.getRect()) && t.isVivo() && this.good != t.isBom()) {
            BombaTanque e = new BombaTanque(t.getX(), t.getY(), batalhatanque);
            batalhatanque.tanquesBomba.add(e);
            if (t.isBom()) {
                t.setVida(t.getVida() - 50); 
                if (t.getVida() <= 0) {
                    t.setVivo(false);
                } 
            } else {
                t.setVivo(false); 
            }
            this.vivo = false;
            return true;
        }
        return false;
    }
    public boolean hitParede(ParedeComum w) { 
        if (this.vivo && this.getRect().intersects(w.getRect())) {
            this.vivo = false;
            this.batalhatanque.outrasParedes.remove(w); 
            this.batalhatanque.paredesCasa.remove(w);
            return true;
        }
        return false;
    }
    public boolean hitProjetil(Projetil w) {
        if (this.vivo && this.getRect().intersects(w.getRect())) {
            this.vivo=false;
            this.batalhatanque.projeteis.remove(w);
            return true;
        }
        return false;
    }
    public boolean hitParede(ParedeMetal w) { 
        if (this.vivo && this.getRect().intersects(w.getRect())) {
            this.vivo = false;
            return true;
        }
        return false;
    }
    public boolean hitCasa() { 
        if (this.vivo && this.getRect().intersects(batalhatanque.casa.getRect())) {
            this.vivo = false;
            this.batalhatanque.casa.setLive(false); 
            return true;
        }
        return false;
    }
}