package controller;
import java.awt.*;
import javax.swing.*;
public class im2
{
    public static void main( String[] args )
    {
        myJFrame mjf = new myJFrame();
        mjf.loadImageMap("map_small.gif");
        mjf.show();
        try
        {
            Thread.sleep(3000);
        } catch (InterruptedException ie)
        {
        }
        mjf.loadImageMap("map.gif");
        mjf.repaint();
    }
}
class myJFrame extends JFrame
{
    Image im;
    public myJFrame()
    {
        setSize(500,500);
    }
    public void loadImageMap ( String imageFileName )
    {
        im = Toolkit.getDefaultToolkit().getImage( imageFileName );
    }
    public void paint(Graphics g)
    {
        g.drawImage(im, 0, 0, this);
    }
}