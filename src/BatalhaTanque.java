import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BatalhaTanque extends Frame implements ActionListener {
    private static final long serialVersionUID = 1L;
    public static final int FRAME_WIDTH = 800; 
    public static final int FRAME_LENGTH = 600;
    public static boolean printar = true;
    MenuBar menubar = null;
    Menu menu1 = null, menu2 = null, menu3 = null, menu4 = null, menu5 = null;
    MenuItem menuitem1 = null, menuitem2 = null, menuitem3 = null, menuitem4 = null, menuitem5 = null, menuitem6 = null, menuitem7 = null, menuitem8 = null, menuitem9 = null, menuitem10 = null, menuitem11 = null;
    Image imagemTela = null;
    Tanque tanque1 = new Tanque(300, 560, true, Controle.STOP, this, 1);
    Tanque tanque2 = new Tanque(449, 560, true, Controle.STOP, this, 2);
    Boolean jogador2 = false;
    Sangue sangue = new Sangue();
    Casa casa = new Casa(373, 557, this);
    Boolean vitoria = false, derrota = false;
    List<Riacho> riachos = new ArrayList<>();
    List<Tanque> tanques = new ArrayList<>();
    List<BombaTanque> tanquesBomba = new ArrayList<>();
    List<Projetil> projeteis = new ArrayList<>();
    List<Arvore> arvores = new ArrayList<>();
    List<ParedeComum> paredesCasa = new ArrayList<>();
    List<ParedeComum> outrasParedes = new ArrayList<>();
    List<ParedeMetal> paredesMetal = new ArrayList<>();
    public BatalhaTanque() {
        menubar = new MenuBar();
        menu1 = new Menu("Jogo");
        menu2 = new Menu("Pausar/Continuar");
        menu3 = new Menu("Ajuda");
        menu4 = new Menu("Nível");
        menu5 = new Menu("Adição");
        menuitem1 = new MenuItem("Novo jogo");
        menuitem2 = new MenuItem("Sair");
        menuitem3 = new MenuItem("Pausar");
        menuitem4 = new MenuItem("Continuar");
        menuitem5 = new MenuItem("Ajuda");
        menuitem6 = new MenuItem("Nível 1");
        menuitem7 = new MenuItem("Nível 2");
        menuitem8 = new MenuItem("Nível 3");
        menuitem9 = new MenuItem("Nível 4");
        menuitem10 = new MenuItem("Adicionar jogador 2");
        menuitem11 = new MenuItem("Sobre");
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu2.add(menuitem3);
        menu2.add(menuitem4);
        menu3.add(menuitem5);
        menu3.add(menuitem11);
        menu4.add(menuitem6);
        menu4.add(menuitem7);
        menu4.add(menuitem8);
        menu4.add(menuitem9);
        menu5.add(menuitem10);
        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu4);
        menubar.add(menu5);
        menubar.add(menu3);
        menuitem1.addActionListener(this);
        menuitem1.setActionCommand("novojogo");
        menuitem2.addActionListener(this);
        menuitem2.setActionCommand("sair");
        menuitem3.addActionListener(this);
        menuitem3.setActionCommand("pausar");
        menuitem4.addActionListener(this);
        menuitem4.setActionCommand("continuar");
        menuitem5.addActionListener(this);
        menuitem5.setActionCommand("ajuda");
        menuitem6.addActionListener(this);
        menuitem6.setActionCommand("nivel1");
        menuitem7.addActionListener(this);
        menuitem7.setActionCommand("nivel2");
        menuitem8.addActionListener(this);
        menuitem8.setActionCommand("nivel3");
        menuitem9.addActionListener(this);
        menuitem9.setActionCommand("nivel4");
        menuitem10.addActionListener(this);
        menuitem10.setActionCommand("jogador2");
        menuitem11.addActionListener(this);
        menuitem11.setActionCommand("sobre");
        this.setMenuBar(menubar);
        this.setVisible(true);
        for (int i = 0; i < 10; i++) { 
            if (i < 4) {
                paredesCasa.add(new ParedeComum(350, 580 - 21 * i, this));
            } else if (i < 7) {
                paredesCasa.add(new ParedeComum(372 + 22 * (i - 4), 517, this));
            } else {
                paredesCasa.add(new ParedeComum(416, 538 + (i - 7) * 21, this));
            }
        }
        for (int i = 0; i < 32; i++) {
            if (i < 16) {
                outrasParedes.add(new ParedeComum(200 + 21 * i, 300, this)); 
                outrasParedes.add(new ParedeComum(500 + 21 * i, 180, this));
                outrasParedes.add(new ParedeComum(200, 400 + 21 * i, this));
                outrasParedes.add(new ParedeComum(500, 400 + 21 * i, this));
            } else if (i < 32) {
                outrasParedes.add(new ParedeComum(200 + 21 * (i - 16), 320, this));
                outrasParedes.add(new ParedeComum(500 + 21 * (i - 16), 220, this));
                outrasParedes.add(new ParedeComum(222, 400 + 21 * (i - 16), this));
                outrasParedes.add(new ParedeComum(522, 400 + 21 * (i - 16), this));
            }
        }
        for (int i = 0; i < 20; i++) { 
            if (i < 10) {
                paredesMetal.add(new ParedeMetal(140 + 30 * i, 150, this));
                paredesMetal.add(new ParedeMetal(600, 400 + 20 * (i), this));
            } else if (i < 20) {
                paredesMetal.add(new ParedeMetal(140 + 30 * (i - 10), 180, this));
            }
        }
        for (int i = 0; i < 4; i++) { 
            if (i < 4) {
                arvores.add(new Arvore(0 + 30 * i, 360, this));
                arvores.add(new Arvore(220 + 30 * i, 360, this));
                arvores.add(new Arvore(440 + 30 * i, 360, this));
                arvores.add(new Arvore(660 + 30 * i, 360, this));
            }
        }
        riachos.add(new Riacho(85, 100, this));
        for (int i = 0; i < 20; i++) {
            if (i < 9) {
                tanques.add(new Tanque(150 + 70 * i, 40, false, Controle.D, this,0));
            } else if (i < 15) {
                tanques.add(new Tanque(700, 140 + 50 * (i - 6), false, Controle.D, this,0));
            } else {
                tanques.add(new Tanque(10, 50 * (i - 12), false, Controle.D, this,0));
            }
        }
        this.setSize(FRAME_WIDTH, FRAME_LENGTH);
        this.setLocationRelativeTo(null);
        this.setTitle("Batalha de Tanques");
        this.addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.setVisible(true);
        this.addKeyListener(new KeyMonitor());
        new Thread(new PaintThread()).start();
    }
    public void update(Graphics g) {
        imagemTela = this.createImage(FRAME_WIDTH, FRAME_LENGTH);
        Graphics gps = imagemTela.getGraphics();
        Color c = gps.getColor();
        gps.setColor(new Color(143, 99, 54));
        gps.fillRect(0, 0, FRAME_WIDTH, FRAME_LENGTH);
        gps.setColor(c);
        framePaint(gps);
        g.drawImage(imagemTela, 0, 0, null);
    }
    public void framePaint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.green);
        Font font = g.getFont();
        g.setFont(new Font("Consolas", Font.BOLD, 20));
        if (!jogador2) {
            g.drawString("Tanques restantes no campo: ", 30, 70);
        } else {
            g.drawString("Tanques restantes no campo: ", 30, 70);
        }
        g.setFont(new Font("Consolas", Font.BOLD, 20));
        if (!jogador2) {
            g.drawString(Integer.toString(tanques.size()), 332, 70);
        } else {
            g.drawString(Integer.toString(tanques.size()), 332, 70);
        }
        g.setFont(new Font("Consolas", Font.BOLD, 20));
        if (!jogador2) {
            g.drawString("Vida: ", 430, 70);
        }
        g.setFont(new Font("Consolas", Font.BOLD, 20));
        if (!jogador2) {
            g.drawString(Integer.toString(tanque1.getVida()), 490, 70);
        } else {
            g.drawString("Jogador 1: " + tanque1.getVida() + " Jogador 2: " + tanque2.getVida(), 430, 70);
        }
        g.setFont(font);
        if (!jogador2) {
            if (tanques.isEmpty() && casa.isLive() && tanque1.isVivo() && derrota == false) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 60)); 
                this.outrasParedes.clear();
                g.drawString("Parabéns! ", 300, 300);
                g.setFont(f);
                vitoria = true;
            }
            if (tanque1.isVivo() == false && vitoria == false) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 40));
                tanques.clear();
                projeteis.clear();
                g.drawString("Você perdeu!", 300, 300);
                derrota = true;
                g.setFont(f);
            }
        } else {
            if (tanques.isEmpty() && casa.isLive() && (tanque1.isVivo() || tanque2.isVivo()) && derrota == false) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 60));
                this.outrasParedes.clear();
                g.drawString("Parabéns!", 300, 300);
                g.setFont(f);
                vitoria = true;
            }

            if (tanque1.isVivo() == false && tanque2.isVivo() == false && vitoria == false) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 40));
                tanques.clear();
                projeteis.clear();
                g.drawString("Você perdeu!", 300, 300);
                System.out.println("2");
                g.setFont(f);
                derrota = true;
            }
        }
        g.setColor(c);
        for (int i = 0; i < riachos.size(); i++) {
            Riacho r = riachos.get(i);
            r.draw(g);
        }
        for (int i = 0; i < riachos.size(); i++) {
            Riacho r = riachos.get(i);
            tanque1.colideRiacho(r);
            if (jogador2) {
                tanque2.colideRiacho(r);
            }
            r.draw(g);
        }
        casa.draw(g); 
        tanque1.desenhar(g);
        tanque1.comer(sangue);
        if (jogador2) {
            tanque2.desenhar(g);
            tanque2.comer(sangue);
        }
        for (int i = 0; i < projeteis.size(); i++) { 
            Projetil m = projeteis.get(i);
            m.hitTanques(tanques); 
            m.hitTanque(tanque1);
            m.hitTanque(tanque2);
            m.hitCasa(); 
            for(int j = 0; j < projeteis.size(); j++){
                if (i == j) {
                    continue;
                }
                Projetil bts = projeteis.get(j);
                m.hitProjetil(bts);
            }
            for (int j = 0; j < paredesMetal.size(); j++) { 
                ParedeMetal mw = paredesMetal.get(j);
                m.hitParede(mw);
            }
            for (int j = 0; j < outrasParedes.size(); j++) {
                ParedeComum w = outrasParedes.get(j);
                m.hitParede(w);
            }
            for (int j = 0; j < paredesCasa.size(); j++) {
                ParedeComum cw = paredesCasa.get(j);
                m.hitParede(cw);
            }
            m.draw(g); 
        }
        for (int i = 0; i < tanques.size(); i++) {
            Tanque t = tanques.get(i); 
            for (int j = 0; j < paredesCasa.size(); j++) {
                ParedeComum cw = paredesCasa.get(j);
                t.colideComParede(cw); 
                cw.draw(g);
            }
            for (int j = 0; j < outrasParedes.size(); j++) { 
                ParedeComum cw = outrasParedes.get(j);
                t.colideComParede(cw);
                cw.draw(g);
            }
            for (int j = 0; j < paredesMetal.size(); j++) {
                ParedeMetal mw = paredesMetal.get(j);
                t.colideComParede(mw);
                mw.draw(g);
            }
            for (int j = 0; j < riachos.size(); j++) {
                Riacho r = riachos.get(j);
                t.colideRiacho(r);
                r.draw(g);
            }
            t.colideComTanques(tanques);
            t.colideCasa(casa);
            t.desenhar(g);
        }
        for (int i = 0; i < arvores.size(); i++) { 
            Arvore tr = arvores.get(i);
            tr.draw(g);
        }
        for (int i = 0; i < tanquesBomba.size(); i++) { 
            BombaTanque bt = tanquesBomba.get(i);
            bt.draw(g);
        }
        for (int i = 0; i < outrasParedes.size(); i++) { 
            ParedeComum cw = outrasParedes.get(i);
            cw.draw(g);
        }
        for (int i = 0; i < paredesMetal.size(); i++) { 
            ParedeMetal mw = paredesMetal.get(i);
            mw.draw(g);
        }
        tanque1.colideComTanques(tanques);
        tanque1.colideCasa(casa);
        if (jogador2) {
            tanque2.colideComTanques(tanques);
            tanque2.colideCasa(casa);
        }
        for (int i = 0; i < paredesMetal.size(); i++) {
            ParedeMetal w = paredesMetal.get(i);
            tanque1.colideComParede(w);
            if (jogador2) {
                tanque2.colideComParede(w);
            }
            w.draw(g);
        }
        for (int i = 0; i < outrasParedes.size(); i++) {
            ParedeComum cw = outrasParedes.get(i);
            tanque1.colideComParede(cw);
            if (jogador2) {
                tanque2.colideComParede(cw);
            }
            cw.draw(g);
        }
        for (int i = 0; i < paredesCasa.size(); i++) {
            ParedeComum w = paredesCasa.get(i);
            tanque1.colideComParede(w);
            if (jogador2) {
                tanque2.colideComParede(w);
            }
            w.draw(g);
        }
    }
    private class PaintThread implements Runnable {
        public void run() {
            while (printar) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class KeyMonitor extends KeyAdapter {
        public void keyReleased(KeyEvent e) { 
            tanque1.keyReleased(e);
            tanque2.keyReleased(e);
        }
        public void keyPressed(KeyEvent e) {
            tanque1.keyPressed(e);
            tanque2.keyPressed(e);
        }
    }
    public void actionPerformed(ActionEvent e) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException exc) {
            exc.printStackTrace();
        } catch (InstantiationException exc) {
            exc.printStackTrace();
        } catch (IllegalAccessException exc) {
            exc.printStackTrace();
        } catch (UnsupportedLookAndFeelException exc) {
            exc.printStackTrace();
        }
        if (e.getActionCommand().equals("novojogo")) {
            printar = false;
            Object[] options = {"Confirmar", "Cancelar"};
            int response = JOptionPane.showOptionDialog(this, "Confirme para começar um novo jogo?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                printar = true;
                this.dispose();
                new BatalhaTanque();
            } else {
                printar = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().endsWith("pausar")) {
            printar = false;
        } else if (e.getActionCommand().equals("continuar")) {
            if (!printar) {
                printar = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().equals("sair")) {
            printar = false;
            Object[] options = { "Confirmar", "Cancelar" };
            int response = JOptionPane.showOptionDialog(this, "Confirma para sair?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                System.exit(0);
            } else {
                printar = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().equals("jogador2")) {
            printar = false;
            Object[] options = {"Confirmar", "Cancelar"};
            int response = JOptionPane.showOptionDialog(this, "Confirma para adicionar jogador 2?", "", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                printar = true;
                this.dispose();
                BatalhaTanque Player2add = new BatalhaTanque();
                Player2add.jogador2 = true;
            } else {
                printar = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().equals("ajuda")) {
            printar = false;
            JOptionPane.showMessageDialog(null, "Use as teclas WSAD para controlar a direção do jogador 1, use a tecla F para atirar e pressione R para recomeçar.\nUse as teclas de direção para controlar a direção do jogador 2, use a tecla M para atirar.", "Ajuda", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(true);
            printar = true;
            new Thread(new PaintThread()).start(); 
        } else if (e.getActionCommand().equals("sobre")) {
            printar = false;
            JOptionPane.showMessageDialog(null, "Este jogo é o projeto da disciplina de Desenvolvimento de Jogos\ndo curso técnico de Informática do IFPE-Garanhuns.\n                         Professor: Eugênio de Carvalho Saraiva\n                                                 Créditos\n                         Desenvolvido por: Lucas Kauê da Silva", "Sobre", JOptionPane.PLAIN_MESSAGE);
            this.setVisible(true);
            printar = true;
            new Thread(new PaintThread()).start();;
        } else if (e.getActionCommand().equals("nivel1")) {
            Tanque.count = 12;
            Tanque.speedX = 6;
            Tanque.speedY = 6;
            Projetil.speedX = 10;
            Projetil.speedY = 10;
            this.dispose();
            new BatalhaTanque();
        } else if (e.getActionCommand().equals("nivel2")) {
            Tanque.count = 12;
            Tanque.speedX = 10;
            Tanque.speedY = 10;
            Projetil.speedX = 12;
            Projetil.speedY = 12;
            this.dispose();
            new BatalhaTanque();
        } else if (e.getActionCommand().equals("nivel3")) {
            Tanque.count = 20;
            Tanque.speedX = 14;
            Tanque.speedY = 14;
            Projetil.speedX = 16;
            Projetil.speedY = 16;
            this.dispose();
            new BatalhaTanque();
        } else if (e.getActionCommand().equals("nivel4")) {
            Tanque.count = 20;
            Tanque.speedX = 16;
            Tanque.speedY = 16;
            Projetil.speedX = 18;
            Projetil.speedY = 18;
            this.dispose();
            new BatalhaTanque();
        }
    }
    public static void main(String[] args) {
        new BatalhaTanque(); 
    }
}