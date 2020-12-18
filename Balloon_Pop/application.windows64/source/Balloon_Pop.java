import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Balloon_Pop extends PApplet {

OrangeBalloon[] orangeballoons = new OrangeBalloon[100];
Book[] books = new Book[50];
Tree[] trees = new Tree[100];
Water[] waters = new Water[200];
Apple[] apples = new Apple[6];
Spike[] spikes = new Spike[2];
Paper[] papers = new Paper[4];
Salmon[] salmons = new Salmon[8];
int score = 0;
int score2 = 0;
int score3 = 0;
int score4 = 0;
int spikeScore = 0;
int paperScore = 0;
int appleScore = 0;
int salmonScore = 0;


public void setup(){
  
  
  
  for (int i = 0; i < 100; i++) {
    orangeballoons[i] = new OrangeBalloon(300,300);
  }
  for (int i = 0; i < 50; i++) {
    books[i] = new Book(301,301);
  }
  for (int i = 0; i < 100; i++) {
    trees[i] = new Tree(301,600);
  }
  for (int i = 0; i < 2; i++) {
    spikes[i] = new Spike(300,300); 
  }
  for (int i = 0; i < 4; i++) {
    papers[i] = new Paper(600,600);
  }
  for (int i = 0; i < 6; i++) {
    apples[i] = new Apple(301,600);
  }
  for (int i = 0; i < 8; i++) {
    salmons[i] = new Salmon(600,600);
  }
  for (int i = 0; i < 200; i++) {
    waters[i] = new Water(600,600);
  }
}

public void draw() {
  background(128,128,128);

  
  for (Book book : books) {
    if (!book.alive()) continue;
    book.collideWorldBounds(301,301);
    book.bookVsMouse();
    book.move();
    book.draw();
  }
  for (Tree tree : trees) {
    if (!tree.alive()) continue;
    tree.collideWorldBounds(301,600);
    tree.treeVsMouse();
    tree.move();
    tree.draw();
  } 
  for (Water water : waters) {
    if (!water.alive()) continue;
    water.collideWorldBounds(600,600);
    water.salmonVsMouse();
    water.move();
    water.draw();
  } 
  for (OrangeBalloon orangeballoon : orangeballoons) {
    if (!orangeballoon.alive()) continue;
    orangeballoon.collideWorldBounds(300,300);
    orangeballoon.balloonVsMouse();
    orangeballoon.move();
    orangeballoon.draw();
  }  
  for (Spike spike : spikes) {
    for (OrangeBalloon o : orangeballoons) {
      if (o.alive)
      spike.attack(o);
    }
    spike.collideWorldBounds(300,300);
    spike.move();
    spike.draw();
  } 
  for (Paper paper : papers) {
   for (Book b : books) {
     if (b.alive)
     paper.attack(b);
  }
  paper.collideWorldBounds(600,600);
  paper.move();
  paper.draw();
  }
  for (Apple apple : apples) {
    for (Tree a : trees) {
      if (a.alive)
      apple.attack(a);
  }
    apple.collideWorldBounds(301,600);
    apple.move();
    apple.draw();
  }
  for (Salmon salmon : salmons) {
    for (Water s : waters) {
      if (s.alive)
      salmon.attack(s);
    }
    salmon.collideWorldBounds(600,600);
    salmon.move();
    salmon.draw();
  }
  
  fill(255,255,255);
  text(score,100,20);
  text(score2,400,20);
  text(score3,100,350);
  text(score4,400,350);
  text(spikeScore,200,20);
  text(paperScore,500,20);
  text(appleScore,200,350);
  text(salmonScore,500,350);
}



public class Apple {
  private int x, y, size, vx, vy;
  
  public Apple(int cwidth, int cheight) {
    this.x = (int) random(200,200);
    this.y = (int) random(400,600);
    this.size = 15;
    this.vx = (int) random(-7,7);
    this.vy = (int) random(-7,7);
  }
  
  public void draw() {
    fill(255,0,0);
    rect(x,y,size,size);
  }
  
  public void move() {
    x += vx;
    y += vy;
  }
  
  public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 301 || y > cheight) {
      vy *= -1;
    }
    if (x < 0 || x > 301) {
      vx *= -1;
    }
  }
  
  public void attack(Tree a) {
    if (dist(a.getX(),a.getY(),x,y) <= a.getSize() / 2 + size / 2) {
      appleScore++;
      a.pop();
    }
  }
}
public class Book {
  private int x, y, size, vx ,vy;
  private boolean alive = true;
  
  public Book(int cwidth, int cheight) {
    this.x = (int) random(301,600);
    this.y = (int) random(cheight);
    this.size = 20;
    this.vx = 2;
    this.vy = 2;
  }
public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 0 || y > 300) {
      vy *= -1;
    }
    if (x < 301 || x > cwidth) {
      vx *= -1;
    }
}
  public void draw() {
    fill(153,51,0);
    circle(x,y,size);
  }

  public void move() {
    vx = (int) random(-2,2);
    vy = (int) random(-2,2);
    x += vx;
    y += vy;
  }
  
  public void bookVsMouse() {
    if (dist(x,y,mouseX,mouseY) <=size/2) {
      score2++;
      alive = false;
    }
  }

  public boolean alive() {
    return alive;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getSize() {
    return size;
  }
  
  public void pop() {
    alive = false;
  }
}
public class OrangeBalloon {
  private int x, y, size, vx ,vy;
  private boolean alive = true;
  
  public OrangeBalloon(int cwidth, int cheight) {
    this.x = (int) random(300);
    this.y = (int) random(300);
    this.size = 27;
    this.vx = 2;
    this.vy = 2;
  }
  
public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 0 || y > 300) {
      vy *= -1;
    }
    if (x < 0 || x > 300) {
      vx *= -1;
    }
}
  public void draw() {
    fill(255,102,0);
    circle(x,y,size);
  }

  public void move() {
    vx = (int) random(-2,2);
    vy = (int) random(-2,2);
    x += vx;
    y += vy;
  }
  
  public void balloonVsMouse() {
    if (dist(x,y,mouseX,mouseY) <=size/2) {
      score++;
      alive = false;
    }
  }

  public boolean alive() {
    return alive;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getSize() {
    return size;
  }
  
  public void pop() {
    alive = false;
  }
}
public class Paper {
  private int x, y, size, vx, vy;
  
  public Paper(int cwidth, int cheight) {
    this.x = (int) random(400,400);
    this.y = (int) random(200,200);
    this.size = 30;
    this.vx = (int) random(-6,6);
    this.vy = (int) random(-6,6);
  }
  
  public void draw() {
    fill(255,255,204);
    rect(x,y,size,size);
  }
  
  public void move() {
    x += vx;
    y += vy;
  }
  
  public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 0 || y > 300) {
      vy *= -1;
    }
    if (x < 301 || x > cwidth) {
      vx *= -1;
    }
  }
  
  public void attack(Book d) {
    if (dist(d.getX(),d.getY(),x,y) <= d.getSize() / 2 + size / 2) {
      paperScore++;
      d.pop();
    }
  }
}
public class Salmon {
  private int x, y, size, vx, vy;
  
  public Salmon(int cwidth, int cheight) {
    this.x = (int) random(400,400);
    this.y = (int) random(400,400);
    this.size = 15;
    this.vx = (int) random(-7,7);
    this.vy = (int) random(-7,7);
  }
  
  public void draw() {
    fill(255,128,128);
    rect(x,y,size,size);
  }
  
  public void move() {
    x += vx;
    y += vy;
  }
  
  public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 301 || y > cheight) {
      vy *= -1;
    }
    if (x < 301 || x > cwidth) {
      vx *= -1;
    }
  }
  
  public void attack(Water k) {
    if (dist(k.getX(),k.getY(),x,y) <= k.getSize() / 2 + size / 2) {
      salmonScore++;
      k.pop();
    }
  }
}
public class Spike {
  private int x, y, size, vx, vy;
  
  public Spike(int cwidth, int cheight) {
    this.x = (int) random(cwidth);
    this.y = (int) random(cheight);
    this.size = 40;
    this.vx = (int) random(-5,5);
    this.vy = (int) random(-5,5);
  }
  
  public void draw() {
    fill(0,0,0);
    rect(x,y,size,size);
  }
  
  public void move() {
    x += vx;
    y += vy;
  }
  
  public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 0 || y > cheight) {
      vy *= -1;
    }
    if (x < 0 || x > cwidth) {
      vx *= -1;
    }
  }
  
  public void attack(OrangeBalloon o) {
    if (dist(o.getX(),o.getY(),x,y) <= o.getSize() / 2 + size / 2) {
      spikeScore++;
      o.pop();
    }
  }
}
public class Tree {
  private int x, y, size, vx ,vy;
  private boolean alive = true;
  
  public Tree(int cwidth, int cheight) {
    this.x = (int) random(cwidth);
    this.y = (int) random(301,600);
    this.size = 15;
    this.vx = 2;
    this.vy = 2;
  }
public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 301 || y > cheight) {
      vy *= -1;
    }
    if (x > 301 || x < cwidth) {
      vx *= -1;
    }
}
  public void draw() {
    fill(0,128,0);
    circle(x,y,size);
  }

  public void move() {
    vx = (int) random(-2,2);
    vy = (int) random(-2,2);
    x += vx;
    y += vy;
  }
  
  public void treeVsMouse() {
    if (dist(x,y,mouseX,mouseY) <=size/2) {
      score3++;
      alive = false;
    }
  }

  public boolean alive() {
    return alive;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getSize() {
    return size;
  }
  
  public void pop() {
    alive = false;
  }
}
public class Water {
  private int x, y, size, vx ,vy;
  private boolean alive = true;
  
  public Water(int cwidth, int cheight) {
    this.x = (int) random(301,600);
    this.y = (int) random(301,600);
    this.size = 15;
    this.vx = 2;
    this.vy = 2;
  }
public void collideWorldBounds(int cwidth, int cheight) {
    if (y < 301 || y > cheight) {
      vy *= -1;
    }
    if (x > 301 || x < cwidth) {
      vx *= -1;
    }
}
  public void draw() {
    fill(0,0,255);
    circle(x,y,size);
  }

  public void move() {
    vx = (int) random(-2,2);
    vy = (int) random(-2,2);
    x += vx;
    y += vy;
  }
  
  public void salmonVsMouse() {
    if (dist(x,y,mouseX,mouseY) <=size/2) {
      score4++;
      alive = false;
    }
  }

  public boolean alive() {
    return alive;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getSize() {
    return size;
  }
  
  public void pop() {
    alive = false;
  }
}
  public void settings() {  size(600,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Balloon_Pop" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
