package Game;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.Timer;
import java.util.TimerTask;

public class Key extends Thread implements KeyListener{
   private GameRoom gr = null;
   private gameGUI gg = null;
   private printPlayers pp = null;
   
   //   키 지연 현상을 막기 위함
   private HashSet<Integer> pressedKeys = new HashSet<>(); //키의 지연 현상을 막기 위한 해시셋
   private Iterator<Integer> i = null;
   private Timer timer;
   
   public Key(GameRoom gr, gameGUI gg, printPlayers pp){
      this.gr=gr;
      this.gg=gg;
      this.pp=pp;
      
      /* KeyPreesed()에서 발생한 키코드를 HasSet에 저장하여 n초 간격으로 키코드를 확인하여 화면을 갱신한다.*/
      timer = new Timer(30,new ActionListener() {
    	  @Override
    	  public void actionPerformed(ActionEvent arg0) {
    		  if(!pressedKeys.isEmpty()) {
                i = pressedKeys.iterator();
                int n = 0;
                while(i.hasNext()) {
                   n=i.next();
                   if (n==39) gr.p1.goRight();
                   else if (n==37) gr.p1.goLeft();
                   else if (n==38) gr.p1.goUp();
                   else if (n==40) gr.p1.goDown();
                   else if (n=='w') gr.p2.goUp();
                   else if (n=='a') gr.p2.goLeft();
                   else if (n=='s') gr.p2.goDown();
                   else if (n=='d') gr.p2.goRight();
//                   pp.repaint();
                }
             }else {
            	 timer.stop();
             }
    	  }
      });
   }

   @Override
   /// -------------------------------------- 윌크 이동
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      if(e.getKeyChar()=='w') { // 방향키 위쪽을 누르면 이동
         int keyCode = e.getKeyChar();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyChar()=='s') { //방향키 아래을 누르면 이동
         int keyCode = e.getKeyChar();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyChar()=='a') { //   왼쪽
         int keyCode = e.getKeyChar();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyChar()=='d') {//   오른쪽
         int keyCode = e.getKeyChar();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }
   }

   @Override
   /// -------------------------------------- 브레드 이동
   public void keyPressed(KeyEvent e) {
      
      if(e.getKeyCode()==38) { // 방향키 위쪽을 누르면 이동
         int keyCode = e.getKeyCode();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyCode()==40) { //방향키 아래을 누르면 이동
         int keyCode = e.getKeyCode();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyCode()==37) { //   왼쪽
         int keyCode = e.getKeyCode();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyCode()==39) {//   오른쪽
         int keyCode = e.getKeyCode();
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyCode()==27) {
    	  System.exit(0);
      }
   }

   @Override
   /// -------------------------------------- 브레드 멈춤
   public void keyReleased(KeyEvent e) {
      if(e.getKeyCode()==38) { 
         int keyCode = e.getKeyCode();
         pressedKeys.remove(keyCode);
      }else if(e.getKeyCode()==40) { 
         int keyCode = e.getKeyCode();
         pressedKeys.remove(keyCode);
      }else if(e.getKeyCode()==37) { 
         int keyCode = e.getKeyCode();
         pressedKeys.remove(keyCode);
      }else if(e.getKeyCode()==39) {
         int keyCode = e.getKeyCode();
         pressedKeys.remove(keyCode);
      }
      
      /// -------------------------------------- 윌크 멈춤
      if(e.getKeyChar()=='w') { 
         int keyCode = e.getKeyChar();
         
         pressedKeys.remove(keyCode);
      }else if(e.getKeyChar()=='s') { 
         int keyCode = e.getKeyChar();
         pressedKeys.remove(keyCode);
      }else if(e.getKeyChar()=='a') {
         int keyCode = e.getKeyChar();
         pressedKeys.remove(keyCode);
      }else if(e.getKeyChar()=='d') {
         int keyCode = e.getKeyChar();
         pressedKeys.remove(keyCode);
      }
   }

}