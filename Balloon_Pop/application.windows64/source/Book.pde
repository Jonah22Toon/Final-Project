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
