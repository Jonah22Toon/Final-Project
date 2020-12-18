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
  size(600,600);
  
  
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
