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
