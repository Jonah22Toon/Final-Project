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
