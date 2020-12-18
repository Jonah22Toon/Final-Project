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
