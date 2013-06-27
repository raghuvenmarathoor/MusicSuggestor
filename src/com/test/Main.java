import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Test extends JFrame 
{
   public static void main(String[]args)
   {
      new Test();
   }
   public Test()
   {
      Container cp = getContentPane();
      cp.setLayout(new FlowLayout());
      final JTextField tf = new JTextField("http://java.sun.com", 20);
      JLabel adress = new JLabel("Adress:");
      JLabel label = new JLabel("Just click it!");
      label.setBorder(BorderFactory.createLineBorder(Color.blue));
      label.addMouseListener(new MouseAdapter()
      {
         public void mouseClicked(MouseEvent e)
         {
            try
            {
               Runtime.getRuntime().exec("c:/Program Files/Internet Explorer/iexplore " + tf.getText());
            }
            catch(java.io.IOException exc)
            {
               System.out.println(exc.toString());
            }
         }
      });
      cp.add(adress);
      cp.add(tf);
      cp.add(label);
      pack();
      show();
   }
}