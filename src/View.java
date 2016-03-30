import java.awt.BorderLayout;

import javax.swing.JFrame;

public class View extends JFrame{
	Philosopher[] philosophers;
	TableView table;
	StateView state;
	public View(Philosopher[] phis) {
		this.philosophers = phis;
		setTitle("Bài toán 5 triết gia ăn tối | tandat2209@gmail.com");
		setSize(1000, 600);
		table = new TableView(this.philosophers);
		add(table, "Center");
		
		new Thread(table).start();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}

