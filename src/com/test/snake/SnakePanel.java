package com.test.snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel implements KeyListener,ActionListener{
	
	//蛇的数据结构
	int[] snackx = new int[750];
	int[] snacky = new int[750];
	int len = 3;
	String direction = "R";//方向“R”为右
	//蛇的随机食物数据结构
	Random r = new Random();
	int foodx = r.nextInt(34)*25+25;//0~33
	int foody = r.nextInt(24)*25+75;
	
	boolean isStarted = false;//游戏是开始还是结束 
	boolean isFaild = false;
	int score = 0;
	Timer timer = new Timer(120, this);
	
	public SnakePanel() {
		this.setFocusable(true);
		initsnake();
		this.addKeyListener(this);//添加键盘监听接口
		timer.start();
	}
	//初始化蛇
	public void initsnake() {
		isStarted = false;//游戏是开始还是结束 
		isFaild = false;
		len = 3;
		snackx[0] = 100;
		snacky[0] = 100;
		snackx[1] = 75;
		snacky[1] = 100;
		snackx[2] = 50;
		snacky[2] = 100;
	}

	ImageIcon up = new ImageIcon("images/up.png");
	ImageIcon down = new ImageIcon("images/down.png");
	ImageIcon left = new ImageIcon("images/left.png");
	ImageIcon right = new ImageIcon("images/right.png");
	ImageIcon food = new ImageIcon("images/food.png");
	ImageIcon body = new ImageIcon("images/body.png");
	ImageIcon background = new ImageIcon("images/background.png");
	ImageIcon title = new ImageIcon("images/title.png");


	public void paint(Graphics g){
		this.setBackground(Color.BLACK);
		g.fillRect(25, 75, 850, 600);
		//设置标题
		title.paintIcon(this, g, 25, 11);
		//画蛇头
		if(direction.equals("R")) {
			right.paintIcon(this, g, snackx[0], snacky[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snackx[0], snacky[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snackx[0], snacky[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snackx[0], snacky[0]);
		}
		//画蛇身
		for(int i=1;i<len;i++){
			body.paintIcon(this, g, snackx[i], snacky[i]);
		}
		
		//画提示语
		if(!isStarted) {
			g.setColor(Color.WHITE);//设置颜色
			g.setFont(new Font("arial",Font.BOLD,30));//设置字体
			g.drawString("press space to start/pause",300,300);//画内容
		}
		if(isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Game over,press space to start",300,300);
		}
		//画食物
		food.paintIcon(this, g, foodx, foody); 
		//画分数和长度
		g.setColor(Color.WHITE);//设置颜色
		g.setFont(new Font("arial",Font.PLAIN,15));//设置字体
		g.drawString("score"+score,750,30);//画内容
		g.drawString("length"+len,750,50);//画内容

	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {  //监听机制
		int keyCode = arg0.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFaild) {
				initsnake();
			}else {
				isStarted = !isStarted;
				}
			//repaint();//重画界面
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")) {
			direction = "R";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")) {
			direction = "L";
		}else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")) {
			direction = "U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")) {
			direction = "D";
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 1.重新定闹钟
	 * 2.蛇移动
	 * 3.重画
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(isStarted && !isFaild) {
			//蛇身的移动
			for(int i=len;i>0;i--) {
				snackx[i] = snackx[i-1];
				snacky[i] = snacky[i-1];
			}
			//蛇头的移动
			if(direction.equals("R")) {
				//横坐标+25
				snackx[0] = snackx[0]+25;
				if(snackx[0] > 850) {
					snackx[0] = 25;
					}
			}else if(direction.equals("L")) {
				//横坐标-25
				snackx[0] = snackx[0]-25;
				if(snackx[0] < 25) {
					snackx[0] = 850;
					}
			}else if(direction.equals("U")) {
				//纵坐标-25
				snacky[0] = snacky[0]-25;
				if(snacky[0] < 75) {
					snacky[0] = 650;
					}
			}else if(direction.equals("D")) {
				//纵坐标+25
				snacky[0] = snacky[0]+25;
				if(snacky[0] > 650) {
					snacky[0] = 75;
					}
			}
			if(snackx[0]==foodx && snacky[0]==foody) {
				len++;
				score++;
				foodx = r.nextInt(34)*25+25;
				foody = r.nextInt(24)*25+75;
			}
			for(int i=1;i<len;i++) {
				if(snackx[0]==snackx[i] && snacky[0]==snacky[i]) {
					isFaild = true;
				}
			}
		}
		repaint();
	}
}
