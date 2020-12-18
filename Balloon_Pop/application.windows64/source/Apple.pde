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
