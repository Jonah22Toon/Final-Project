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
