//Dickon Kudzai Kachasu
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RandomRectangleGUI{
	JFrame frame;
	RandomRectDrawPanel drawPanel=new RandomRectDrawPanel();
	JButton colorButton;
	JButton sizeButton;
        Color color;
        int height = 50;
        int width = 80;
        
	public static void main (String[] args){
		RandomRectangleGUI gui = new RandomRectangleGUI();
		gui.go();
	}

	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		colorButton = new JButton("Click me for a random colour");
		sizeButton = new JButton("Click me for a random size");
                
                RandomColorListener randomizeColor = new RandomColorListener();
                colorButton.addActionListener(randomizeColor);
                
                SizeListener randomizeSize = new SizeListener();
                sizeButton.addActionListener(randomizeSize);

		frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
		frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

        class RandomColorListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
              RandomRectDrawPanel newColor = new RandomRectDrawPanel();
              color = newColor.randomColor();
              frame.repaint();
            }
        }
        
        class SizeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
              RandomRectDrawPanel newSize = new RandomRectDrawPanel();
                int result[] = newSize.randomSize();
                width = result[0];
                height = result[1];
                frame.repaint();
            }
        }

	class RandomRectDrawPanel extends JPanel{
                int x = 50;
                int y = 50;
                
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			g.setColor(color);
			g.fillRect(x,y,width,height);
		}
		
		public Color randomColor(){
                        int r = (int)(Math.random()*255);
                        int gr = (int)(Math.random()*255);
                        int b = (int)(Math.random()*255);
                        color = new Color(r,gr,b);
                        return color;
                }
                
		public int[] randomSize(){
			int displace = 5;
			height = (int)(Math.random()*400);//removing getHeight() and getWidth() because they kept returning 0, using 400 to show size change.
			width = (int)(Math.random()*400);

			int temp;
                        
			if ((y + height) > 400){  // this to keep all of the height of the rectangle inside the draw panel
				temp = 400 - (y + height);
				height = height + temp - displace;  // temp is a negative number
			}
			if (height < 5) height = 5;//minimum height

			if ((x + width) > 400){  // this to keep all of the width of the rectangle inside the draw panel
				temp = 400 - (x + width);
				width = width + temp - displace;  // temp is a negative number
			}
			if (width < 5) width = 5; //minimum width
                        
                        return new int[]{width, height};
		}
	}         
}
