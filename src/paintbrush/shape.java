
package paintbrush;

import java.awt.Color;
 import java.awt.Graphics;

public interface shape {
    public void draw(Graphics g);
}
//...................................................
 class Oval implements shape{
   int x1 , y1 , x2 , y2 ;
    Color color;
    boolean Filled;
   
  public Oval(Color color, int x1, int y1, int x2, int y2, boolean Filled) {
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.Filled = Filled;
    }
   
    @Override
    public void draw(Graphics g) {
       g.setColor(color);
       int width = x2 - x1 ;
      int hight  = y2 - y1 ;
      if( Filled){
          g.fillOval(x1, y1, width, hight);
      }
      else{
      g.drawOval(x1, y1, width, hight);
      }
       
    }
 }
//........................................................
  class Rectangle implements shape{
   int x1 , y1 , x2 , y2 ;
    Color color;
    boolean Filled;
   
  public Rectangle(Color color, int x1, int y1, int x2, int y2,boolean Filled) {
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.Filled = Filled ;
    }
   
    @Override
    public void draw(Graphics g) {
       g.setColor(color);
       int width = x2 - x1 ;
      int hight  = y2 - y1 ;
       if( Filled){
          g.fillRect(x1, y1, width, hight);
      }
      else{
       g.drawRect(x1, y1, width, hight);
       }
    }
 }
//........................................................    
class Line implements shape{
   int x1 , y1 , x2 , y2 ;
    Color color;
   
   public Line(Color color, int x1, int y1, int x2, int y2) {
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
   
    @Override
    public void draw(Graphics g) {
       g.setColor(color);
      g.drawLine(x1, y1, x2, y2);
    }
 }
//.........................................................
   
    
   
