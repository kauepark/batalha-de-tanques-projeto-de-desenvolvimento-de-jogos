import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.util.List;
import java.util.Random;

public class Tanque {
    public static  int speedX = 6, speedY = 6; 
    public static int count = 0;
    public static final int WIDTH = 35, LENGTH = 35;
    private Controle direcao = Controle.STOP;
    private Controle kdirecao = Controle.U; 
    BatalhaTanque batalhatanque;
    private int player = 0;
    private boolean bom;
    private int x, y;
    private int oldX, oldY;
    private boolean vivo = true;
    private int vida = 200;
    private int taxa = 1;
    private static Random r = new Random();
    private int passo = r.nextInt(10) + 5 ; 
    private boolean bL = false, bU = false, bR = false, bD = false;
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image[] tanqueImagens = null; 
    static {
        tanqueImagens = new Image[] {
            toolkit.getImage(BombaTanque.class.getResource("recursos/tankD.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/tankU.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/tankL.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/tankR.gif")), 
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankD.gif")), 
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankU.gif")), 
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankL.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankR.gif")), 
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankD2.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankU2.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankL2.gif")),
            toolkit.getImage(BombaTanque.class.getResource("recursos/HtankR2.gif")),
        };
    }
    public Tanque(int x, int y, boolean good) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.bom = good;
    }
    public Tanque(int x, int y, boolean good, Controle dir, BatalhaTanque batalhatanque, int player) {
        this(x, y, good);
        this.direcao = dir;
        this.batalhatanque = batalhatanque;
        this.player = player;
    }
    public void desenhar(Graphics g) {
        if (!vivo) {
            if (!bom) {
                batalhatanque.tanques.remove(this);
            }
            return;
        }
        switch (kdirecao) {
        case D:
            if (player == 1) {
                g.drawImage(tanqueImagens[4], x, y, null);
            }
            else if (batalhatanque.jogador2 && player == 2) {
                g.drawImage(tanqueImagens[8], x, y, null);
            } else {
                g.drawImage(tanqueImagens[0], x, y, null);
            }
            break;
        case U:
            if (player == 1) {
                g.drawImage(tanqueImagens[5], x, y, null);
            } else if (batalhatanque.jogador2 && player == 2) {
                g.drawImage(tanqueImagens[9], x, y, null);
            } else {
                g.drawImage(tanqueImagens[1], x, y, null);
            }
            break;
        case L:
            if (player == 1) {
                g.drawImage(tanqueImagens[6], x, y, null);
            } else if (batalhatanque.jogador2 && player == 2) {
                g.drawImage(tanqueImagens[10], x, y, null);
            } else {
                g.drawImage(tanqueImagens[2], x, y, null);
            }
            break;
        case R:
            if (player == 1) {
                g.drawImage(tanqueImagens[7], x, y, null);
            } else if (batalhatanque.jogador2 && player == 2) {
                g.drawImage(tanqueImagens[11], x, y, null);
            } else {
                g.drawImage(tanqueImagens[3], x, y, null);}
            break;
        }
        move();   
    }
    void move() {
        this.oldX = x;
        this.oldY = y;
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
        if (this.direcao != Controle.STOP) {
            this.kdirecao = this.direcao;
        }
        if (x < 0) {
            x = 0;
        }
        if (y < 40) {
            y = 40;
        }
        if (x + Tanque.WIDTH > BatalhaTanque.FRAME_WIDTH) {
            x = BatalhaTanque.FRAME_WIDTH - Tanque.WIDTH;
        }
        if (y + Tanque.LENGTH > BatalhaTanque.FRAME_LENGTH) {
            y = BatalhaTanque.FRAME_LENGTH - Tanque.LENGTH;
        }
        if (!bom) {
            Controle[] directions = Controle.values();
            if (passo == 0) {                  
                passo = r.nextInt(12) + 3;  
                int mod = r.nextInt(9);
                if (redorTanqueJogador()) {
                    if (x == batalhatanque.tanque1.x) {
                        if (y > batalhatanque.tanque1.y) {
                            direcao = directions[1];
                        } else if (y < batalhatanque.tanque1.y) {
                            direcao = directions[3];
                        } else if (y == batalhatanque.tanque1.y) {
                            if(x > batalhatanque.tanque1.x) {
                                direcao = directions[0];
                            }
                        } else if (x < batalhatanque.tanque1.x) {
                            direcao = directions[2];
                        }
                    } else {
                        int rn = r.nextInt(directions.length);
                        direcao = directions[rn];
                    }
                    taxa = 2;
                } else if (mod == 1) {
                    taxa = 1;
                } else if (1 < mod && mod <= 3) {
                    taxa = 1;
                } else {
                    int rn = r.nextInt(directions.length);
                    direcao = directions[rn];
                    taxa = 1;
                }
            }
            passo--;
            if (taxa == 2) {
                if (r.nextInt(40) > 35) {
                    this.fire();
                }
            } else if (r.nextInt(40) > 38) {
                this.fire();
            }
        }
    }
    public boolean redorTanqueJogador() {
        int rx = x - 15, ry = y - 15;
        if (x - 15 < 0) {
            rx = 0;
        }
        if (y - 15 < 0) {
            ry = 0;
        }
        Rectangle a = new Rectangle(rx, ry, 60, 60);
        if (this.vivo && a.intersects(batalhatanque.tanque1.getRect())) {
            return true;	
        }
        return false;	
    }
    public int getZone(int x, int y){
        int tempx = x;
        int tempy = y;
        if (tempx < 85 && tempy < 300) {
            return 11;
        } else if (tempx > 85 && tempx < 140 && tempy > 0 && tempy < 100) {
            return 9;
        } else if (tempx > 85 && tempx < 140 && tempy > 254 && tempy < 300) {
            return 10;
        } else if (tempx > 0 && tempx < 200 && tempy > 300 && tempy < 715) {
            return 12;
        } else if (tempx > 140 && tempx < 400 && tempy > 0 && tempy < 150) {
            return 7;
        } else if (tempx > 140 && tempx < 400 && tempy > 210 && tempy < 300) {
            return 8;
        } else if (tempx > 400 && tempx < 500 && tempy > 0 && tempy < 300) {
            return 6;
        } else if (tempx > 500 && tempy > 0 && tempy < 180) {
            return 5;
        } else if (tempx > 500 && tempy > 180 && tempy < 300) {
            return 4;
        } else if (tempx > 520 && tempx < 600 && tempy > 3000 && tempy < 715) {
            return 2;
        } else if (tempx > 600 && tempy > 300 && tempy < 715) {
            return 3;
        }
        return 1;
    }
    private void mudarParaAntigaDirecao() {  
        x = oldX;
        y = oldY;
    }
    public void keyPressed(KeyEvent e) {  
        int key = e.getKeyCode();
        if (player == 1) {
            switch (key) {
                case KeyEvent.VK_R:
                    batalhatanque.tanques.clear(); 
                    batalhatanque.projeteis.clear();
                    batalhatanque.arvores.clear();
                    batalhatanque.outrasParedes.clear();
                    batalhatanque.paredesCasa.clear();
                    batalhatanque.paredesMetal.clear();
                    batalhatanque.tanque1.setVivo(false);
                    if (batalhatanque.tanques.isEmpty()) {        
                        for (int i = 0; i < 20; i++) {
                            if (i < 9) {
                                batalhatanque.tanques.add(new Tanque(150 + 70 * i, 40, false, Controle.R, batalhatanque, 0));
                            } else if (i < 15) {
                                batalhatanque.tanques.add(new Tanque(700, 140 + 50 * (i -6), false, Controle.D, batalhatanque, 0));
                            } else {
                                batalhatanque.tanques.add(new Tanque(10,  50 * (i - 12), false, Controle.L, batalhatanque, 0));
                            }
                        }
                    }
                    batalhatanque.tanque1 = new Tanque(300, 560, true, Controle.STOP, batalhatanque, 0);
                    if (!batalhatanque.casa.isLive()) {
                        batalhatanque.casa.setLive(true);
                    }
                    BatalhaTanque abc = new BatalhaTanque();
                    if (batalhatanque.jogador2) {
                        abc.jogador2 = true;
                    }
                    break;
                case KeyEvent.VK_D:
                    bR = true;
                    break;
                case KeyEvent.VK_A:
                    bL = true;
                    break;
                case KeyEvent.VK_W:  
                    bU = true;
                    break;
                case KeyEvent.VK_S:
                    bD = true;
                    break;
            }
        }
        if (player == 2) {
            switch(key) {
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP: 
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }
        }
        decidirDirecao();
    }
    void decidirDirecao() {
        if (!bL && !bU && bR && !bD) {
            direcao = Controle.R;
        } else if (bL && !bU && !bR && !bD) {
            direcao = Controle.L;
        } else if (!bL && bU && !bR && !bD) {
            direcao = Controle.U;
        } else if (!bL && !bU && !bR && bD) {
            direcao = Controle.D;
        } else if (!bL && !bU && !bR && !bD) {
            direcao = Controle.STOP;
        }
    }
    public void keyReleased(KeyEvent e) {  
        int key = e.getKeyCode();
        if (player == 1) {
            switch (key) {
                case KeyEvent.VK_F:
                    fire();
                    break;
                case KeyEvent.VK_D:
                    bR = false;
                    break;
                case KeyEvent.VK_A:
                    bL = false;
                    break;
                case KeyEvent.VK_W:
                    bU = false;
                    break;
                case KeyEvent.VK_S:
                    bD = false;
                    break;
            }
        }
        if (player == 2) {
            switch (key) {
                case KeyEvent.VK_M:
                    fire();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
            }
        }
        decidirDirecao(); 
    }
    public Projetil fire() { 
        if (!vivo) {
            return null;
        }
        int x = this.x + Tanque.WIDTH / 2 - Projetil.PROJETIL_WIDTH / 2; 
        int y = this.y + Tanque.LENGTH / 2 - Projetil.PROJETIL_LENGTH / 2;
        Projetil m = new Projetil(x, y + 2, bom, kdirecao, this.batalhatanque); 
        batalhatanque.projeteis.add(m);                                                
        return m;
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, LENGTH);
    }

    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    public boolean isBom() {
        return bom;
    }
    public boolean colideComParede(ParedeComum w) {
        if (this.vivo && this.getRect().intersects(w.getRect())) {
            this.mudarParaAntigaDirecao();    
            return true;
        }
        return false;
    }
    public boolean colideComParede(ParedeMetal w) {
        if (this.vivo && this.getRect().intersects(w.getRect())) {
            this.mudarParaAntigaDirecao();     
            return true;
        }
        return false;
    }
    public boolean colideRiacho(Riacho r) {
        if (this.vivo && this.getRect().intersects(r.getRect())) {
            this.mudarParaAntigaDirecao();
            return true;
        }
        return false;
    }
    public boolean colideCasa(Casa h) {
        if (this.vivo && this.getRect().intersects(h.getRect())) {
            this.mudarParaAntigaDirecao();
            return true;
        }
        return false;
    }
    public boolean colideComTanques(List<Tanque> tanques) {
        for (int i = 0; i < tanques.size(); i++) {
            Tanque t = tanques.get(i);
            if (this != t) {
                if (this.vivo && t.isVivo() && this.getRect().intersects(t.getRect())) {
                    this.mudarParaAntigaDirecao();
                    t.mudarParaAntigaDirecao();
                    return true;
                }
            }
        }
        return false;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int life) {
        this.vida = life;
    }
    private class DrawBloodbBar {
        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.drawRect(375, 585, WIDTH, 10);
            int w = WIDTH * vida / 200;
            g.fillRect(375, 585, w, 10);
            g.setColor(c);
        }
    }
    public boolean comer(Sangue b) {
        if (this.vivo && b.isVivo() && this.getRect().intersects(b.getRect())) {
            if (this.vida <= 100) {
                this.vida = this.vida + 100;
            } else {
                this.vida = 200;
            }
            b.setVivo(false);
            return true;
        }
        return false;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}