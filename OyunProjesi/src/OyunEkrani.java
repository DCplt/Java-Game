
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame{

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        OyunEkrani oyunE = new OyunEkrani("Oyun");
        
        oyunE.setResizable(false);
        oyunE.setFocusable(false);
        
        oyunE.setSize(1400,800);
        oyunE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);
        
        oyun.addKeyListener(oyun);
        
        oyunE.add(oyun);
        
        oyunE.setVisible(true) ;
        
    }
}
