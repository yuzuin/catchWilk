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
                }
             }else {
            	 timer.stop();
             }
    	  }
      });
   }

   @Override
   /// -------------------------------------- 윌크 이동
   
   /* *** ㅁ,ㄴ,ㅇ,ㅈ 키는 사용자가 마이크로소프트 자판기의 "문자열마무리" 기능을 사용하는지에 따라 먹히지 않을 수 있음. */
   public void keyTyped(KeyEvent e) {
      if(e.getKeyChar()=='w'||e.getKeyChar()=='W'||e.getKeyChar()=='ㅈ') { // 방향키 위쪽을 누르면 이동
    	  int keyCode = 'w';
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyChar()=='s'||e.getKeyChar()=='S'||e.getKeyChar()=='ㄴ') { //방향키 아래을 누르면 이동
    	  int keyCode = 's';
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyChar()=='a'||e.getKeyChar()=='A'||e.getKeyChar()=='ㅁ') { //   왼쪽
    	  int keyCode = 'a';
         pressedKeys.add(keyCode);
         if(!timer.isRunning()) timer.start();
      }else if(e.getKeyChar()=='d'||e.getKeyChar()=='D'||e.getKeyChar()=='ㅇ') {//   오른쪽
    	  int keyCode = 'd';
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
      if(e.getKeyChar()=='w'||e.getKeyChar()=='W'||e.getKeyChar()=='ㅈ') { 
    	  int keyCode = 'w';
         pressedKeys.remove(keyCode);
      }else if(e.getKeyChar()=='s'||e.getKeyChar()=='S'||e.getKeyChar()=='ㄴ') { 
    	  int keyCode = 's';
         pressedKeys.remove(keyCode);
      }else if(e.getKeyChar()=='a'||e.getKeyChar()=='A'||e.getKeyChar()=='ㅁ') {
    	  int keyCode = 'a';
         pressedKeys.remove(keyCode);
      }else if(e.getKeyChar()=='d'||e.getKeyChar()=='D'||e.getKeyChar()=='ㅇ') {
    	  int keyCode = 'd';
         pressedKeys.remove(keyCode);
      }
   }
}