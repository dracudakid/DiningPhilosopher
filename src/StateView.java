import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Logger;

import javax.swing.JPanel;

public class StateView extends JPanel {
	Philosopher philosopher;
	
	public StateView(Philosopher philosopher) {
		this.philosopher = philosopher;
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Font f = new Font("Serif", Font.PLAIN, 20);
		g.setFont(f);
		g.setColor(Color.BLACK);
		if(philosopher.getState() == Philosopher.EATING){
			g.drawString("Eating", 0, 0);
			g.drawString("HHHHHHH", 0, 0);
		}
		else {
			g.drawString("Thinking", 0, 0);
		}
		
	}
}
