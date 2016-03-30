import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class ConsoleView extends JPanel {
	JTextPane txtpane;
	JScrollBar scrollbar;
	StyledDocument doc;
	
	public ConsoleView() {
		txtpane = new JTextPane();
		
//		txtpane.setEditable(false);
		doc = txtpane.getStyledDocument();
		// scroll to bottom
		DefaultCaret caret = (DefaultCaret)txtpane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setFontSize(style, 15);
		StyleConstants.setForeground(style, Color.GRAY);
		StyleConstants.setBold(style, true);
		
		doc.setParagraphAttributes(0, doc.getLength(), style, true);
		
		Philosopher.setDoc(doc);
		JScrollPane scrollPane = new JScrollPane(txtpane, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scrollPane.setPreferredSize(new Dimension(300, 600));
		add(scrollPane, "Center");
	}
}
