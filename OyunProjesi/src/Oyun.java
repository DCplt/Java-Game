
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class ateşler {
    
    private int x;
    private int y;

    public ateşler(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
} 

public class Oyun extends JPanel implements KeyListener,ActionListener{
    
     Timer timer = new Timer(5, this);
     private int harcananSüre = 0;
     private int ateşSayısı = 0;
     
     private BufferedImage dostGemi;
     private BufferedImage Düşman;
     
     private ArrayList<ateşler> ateşler = new ArrayList<ateşler>();
     
     private int ateşYonu = 5;
     private int DüşmanX = 0;
     
     private int DüşmanYönüX = 7;
     private int UzayGemisi = 0;
     private int UzayGemisiYön = 20;
     
    public boolean KontrolEt(){
        
        for(ateşler ates : ateşler){
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(DüşmanX,3,50,70))){
                return true;
            }
        }
        return false;
    }
     
    public Oyun() {
         try {
             Düşman = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\WhileAtmProgramı\\dusman.png")));
         } catch (IOException ex) {
             JOptionPane.showMessageDialog(this, "hata dosya bulunamadı");
             System.exit(0);
         }
         try {
             dostGemi = ImageIO.read(new FileImageInputStream(new File("C:\\Users\\user\\Documents\\NetBeansProjects\\WhileAtmProgramı\\Dost.png")));
         } catch (IOException ex) {
             JOptionPane.showMessageDialog(this, "hata dosya bulunamadı");
             System.exit(0);
         }
         setBackground(Color.black);
         timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        harcananSüre += 5;
        g.drawImage(Düşman, DüşmanX, 3,Düşman.getWidth()/10,Düşman.getHeight()/8, this);
        g.drawImage(dostGemi, UzayGemisi, 700,Düşman.getWidth()/10,Düşman.getHeight()/8, this);
        
        for(ateşler ates : ateşler){
            if(ates.getY() < 0){
                ateşler.remove(ates);
            }
        }
        g.setColor(Color.blue);
        for(ateşler ates : ateşler){
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        if (KontrolEt()) {
            timer.stop();
            String message = "Kazandınız!! süre: "+harcananSüre/100+" Ateş sayısı: "+ ateşSayısı;
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); 
    }
     
     
     
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int c = e.getKeyCode();
       
        if (c == KeyEvent.VK_LEFT) {
            if (UzayGemisi <= 0) {
                UzayGemisi = 0;
            }else{
                UzayGemisi -= UzayGemisiYön;
            }
        }
        if (c == KeyEvent.VK_RIGHT) {
            if (UzayGemisi >= 1320) {
                UzayGemisi = 1320;
            }else{
                UzayGemisi += UzayGemisiYön;
            }
        }
        if (c == KeyEvent.VK_CONTROL) {
            ateşler.add(new ateşler(UzayGemisi+25,700));
            ateşSayısı++;
                   
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(ateşler ateş : ateşler){
            ateş.setY(ateş.getY()-ateşYonu);
        }
        DüşmanX += DüşmanYönüX;
        
        if (DüşmanX >= 1320) {
            DüşmanYönüX = -DüşmanYönüX;
        }
        if (DüşmanX <= 0) {
            DüşmanYönüX = -DüşmanYönüX;
        }
        repaint();
    }
    
}
