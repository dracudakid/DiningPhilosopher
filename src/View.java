import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame implements ActionListener{
	Philosopher[] philosophers;
	TableView table;
	ConsoleView console;
	Thread thread;
	Thread[] philThreads = new Thread[5];
	public View(Philosopher[] phis) {
		this.philosophers = phis;
		setTitle("Bài toán 5 triết gia ăn tối | tandat2209@gmail.com");
		setLayout(null);
		setSize(1200, 650);
		table = new TableView(this.philosophers);
		table.setBounds(0, 0, 900, 600);
		add(table, "Center");
		console = new ConsoleView();
		console.setBounds(900,0, 300, 600);
		add(console, "East");
		JPanel pnControl = new JPanel();
		JButton btStart = new JButton("Start");
		btStart.addActionListener(this);
		pnControl.add(btStart);
		pnControl.setBackground(Color.WHITE);
		pnControl.setBounds(0, 600, 1200, 50);
		add(pnControl, "South");
		
		thread = new Thread(table);
		for(int i=0; i<philosophers.length; i++){
			philThreads[i]= new Thread(philosophers[i]);
		}
		setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "Start":
			((JButton)e.getSource()).setText("Pause");
			thread.start();
			for(int i=0; i<philThreads.length; i++){
				philThreads[i].start();
			}
			break;
		case "Pause":
			((JButton)e.getSource()).setText("Continue");
			for(int i=0; i<philThreads.length; i++){
				philThreads[i].suspend();
			}
			thread.suspend();;
			break;
		case "Continue":
			((JButton)e.getSource()).setText("Pause");
			thread.resume();
			for(int i=0; i<philThreads.length; i++){
				philThreads[i].resume();
			}
			break;
		}
	}
	
}

