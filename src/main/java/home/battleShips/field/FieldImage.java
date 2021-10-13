package home.battleShips.field;

import java.awt.*;
import javax.swing.*;


/**
 *
 * @author User
 */
public class FieldImage implements Icon {

    private final int WIDTH  = 30;
    private final int HEIGHT = 30;

    private final int SIZE = 9 ;

    private Color color = new Color(30, 144, 255) ;
    private boolean hit = false;

    @Override
    public int getIconWidth(){
        return WIDTH;
    }
    @Override
    public int getIconHeight(){
        return HEIGHT;
    }

    @Override
    public void paintIcon(Component c , Graphics g, int w, int h){

        Graphics2D g2d = (Graphics2D) g ;

        g2d.setColor(color);
        g2d.fillRect(0,0,WIDTH,HEIGHT);

        g2d.setColor(Color.WHITE);
        g2d.drawRect(0,0,WIDTH,HEIGHT);

        if(hit)  drawDot(g2d);


    }

    FieldImage(){
    }

    FieldImage( Color color){
        this.color = color;
    }

    FieldImage( Color color, boolean hit){
        this.color = color;
        this.hit = hit ;
    }

    private void drawCenterCircle(Graphics2D g2d){
        g2d.setPaint(Color.WHITE);

        drawDot(g2d);

    }

    private void drawDot(Graphics2D graphics2D){

        graphics2D.setPaint(Color.WHITE);

        int centerX    = WIDTH /2 ;
        int centerY    = HEIGHT/2 ;
        int HS         = SIZE  /2 ;

        graphics2D.fillRect(centerX - HS,centerY - 1 ,SIZE,3);
        graphics2D.fillRect(centerX - 1,centerY - HS ,3,SIZE);
        graphics2D.fillRect(centerX - HS + 1,centerY - HS + 1 ,SIZE-2,SIZE-2);

    }


    public static void main(String[] args) {

        JFrame jFrame = new JFrame("Box1 - Y");
        jFrame.setSize(400, 200);
        jFrame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        jFrame.setLayout(new FlowLayout());

        Icon blue = new FieldImage();
        Icon red  = new FieldImage(Color.RED , true);

        JLabel blueLabel = new JLabel(blue);
        JLabel  redLabel = new JLabel(red);

        jFrame.add(blueLabel);
        jFrame.add( redLabel);

        jFrame.setVisible(true);

    }
}
