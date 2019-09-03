package com.test.snake;
import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(400, 200, 900, 720);  //窗体大小
		frame.setResizable(false);//设置此窗体是否可以调整大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnakePanel panel = new SnakePanel();  //新建画布
		frame.add(panel);
		frame.setVisible(true);
	}

}
