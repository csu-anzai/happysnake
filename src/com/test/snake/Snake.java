package com.test.snake;
import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(400, 200, 900, 720);  //�����С
		frame.setResizable(false);//���ô˴����Ƿ���Ե�����С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnakePanel panel = new SnakePanel();  //�½�����
		frame.add(panel);
		frame.setVisible(true);
	}

}
