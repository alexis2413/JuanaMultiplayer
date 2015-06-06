package pe.edu.pucp.game.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.Serializable;

//import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Display implements Serializable{
	
	private JFrame frame;
	private Canvas canvas;
	/*private JPanel panel;
	private JButton button;*/
	
	private String title;
	private int width, height;
	
	public Display(String title,int width, int height){
		this.title=title;
		this.width=width;
		this.height=height;
		createDisplay();
		
	}
	
	public Display(){}
	
	private void createDisplay(){
		frame=new JFrame(title);
		frame.setSize(width,height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		/*button=new JButton("SAVE!");
		button.setBounds(20, 20, 100, 100);*/		
		
		canvas=new Canvas();
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));
		canvas.setFocusable(false);//solo el JFrame puede tener focus
		
		frame.add(canvas);
		frame.pack();
		//frame.add(button);
	}
	
	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
