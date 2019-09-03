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
	
	//�ߵ����ݽṹ
	int[] snackx = new int[750];
	int[] snacky = new int[750];
	int len = 3;
	String direction = "R";//����R��Ϊ��
	//�ߵ����ʳ�����ݽṹ
	Random r = new Random();
	int foodx = r.nextInt(34)*25+25;//0~33
	int foody = r.nextInt(24)*25+75;
	
	boolean isStarted = false;//��Ϸ�ǿ�ʼ���ǽ��� 
	boolean isFaild = false;
	int score = 0;
	Timer timer = new Timer(120, this);
	
	public SnakePanel() {
		this.setFocusable(true);
		initsnake();
		this.addKeyListener(this);//��Ӽ��̼����ӿ�
		timer.start();
	}
	//��ʼ����
	public void initsnake() {
		isStarted = false;//��Ϸ�ǿ�ʼ���ǽ��� 
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
		//���ñ���
		title.paintIcon(this, g, 25, 11);
		//����ͷ
		if(direction.equals("R")) {
			right.paintIcon(this, g, snackx[0], snacky[0]);
		}else if(direction.equals("L")){
			left.paintIcon(this, g, snackx[0], snacky[0]);
		}else if(direction.equals("U")){
			up.paintIcon(this, g, snackx[0], snacky[0]);
		}else if(direction.equals("D")){
			down.paintIcon(this, g, snackx[0], snacky[0]);
		}
		//������
		for(int i=1;i<len;i++){
			body.paintIcon(this, g, snackx[i], snacky[i]);
		}
		
		//����ʾ��
		if(!isStarted) {
			g.setColor(Color.WHITE);//������ɫ
			g.setFont(new Font("arial",Font.BOLD,30));//��������
			g.drawString("press space to start/pause",300,300);//������
		}
		if(isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Game over,press space to start",300,300);
		}
		//��ʳ��
		food.paintIcon(this, g, foodx, foody); 
		//�������ͳ���
		g.setColor(Color.WHITE);//������ɫ
		g.setFont(new Font("arial",Font.PLAIN,15));//��������
		g.drawString("score"+score,750,30);//������
		g.drawString("length"+len,750,50);//������

	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {  //��������
		int keyCode = arg0.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFaild) {
				initsnake();
			}else {
				isStarted = !isStarted;
				}
			//repaint();//�ػ�����
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
	 * 1.���¶�����
	 * 2.���ƶ�
	 * 3.�ػ�
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(isStarted && !isFaild) {
			//������ƶ�
			for(int i=len;i>0;i--) {
				snackx[i] = snackx[i-1];
				snacky[i] = snacky[i-1];
			}
			//��ͷ���ƶ�
			if(direction.equals("R")) {
				//������+25
				snackx[0] = snackx[0]+25;
				if(snackx[0] > 850) {
					snackx[0] = 25;
					}
			}else if(direction.equals("L")) {
				//������-25
				snackx[0] = snackx[0]-25;
				if(snackx[0] < 25) {
					snackx[0] = 850;
					}
			}else if(direction.equals("U")) {
				//������-25
				snacky[0] = snacky[0]-25;
				if(snacky[0] < 75) {
					snacky[0] = 650;
					}
			}else if(direction.equals("D")) {
				//������+25
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
