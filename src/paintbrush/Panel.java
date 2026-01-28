
package paintbrush;

import java.awt.Button;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;


public class Panel extends JPanel {
    
    int x1, y1, x2, y2;
    ArrayList<shape> shapes = new ArrayList<>();
    Color currentColor = Color.BLACK;
    String selectedShape = " ";
    boolean isFilled = false;
    ArrayList<Point> point = new ArrayList();
    ArrayList<Color> color = new ArrayList();
    String Type = "free";
    BufferedImage loadedImage = null;
    boolean imageAction = false;

    public Panel() {

     

        JButton colorBtn = new JButton("Choose Color");
        JButton lineBtn = new JButton("Line");
        JButton ovalBtn = new JButton("Oval");
        JButton rectBtn = new JButton("Rectangle");
        JCheckBox fillBox = new JCheckBox("Filled");

        colorBtn.addActionListener(e -> {
            Color selected = JColorChooser.showDialog(null, "Choose Color", currentColor);
            if (selected != null) {
                currentColor = selected;
            }
        });

        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = "Line";
                Type =""; 
             }
        });
        ovalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = "Oval";
                Type =""; 
             }
        });
        rectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = "Rectangle";
                Type =""; 
             }
        });
        fillBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            isFilled = fillBox.isSelected();
            Type =""; 
            }
        });

        add(colorBtn);
        add(lineBtn);
        add(ovalBtn);
        add(rectBtn);
        add(fillBox);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();

                switch (selectedShape) {
                    case "Line":
                        shapes.add(new Line(currentColor, x1, y1, x2, y2));
                        imageAction = false;
                        break;
                    case "Oval":
                        shapes.add(new Oval(currentColor, x1, y1, x2, y2, isFilled));
                        imageAction = false;
                        break;
                    case "Rectangle":
                        shapes.add(new Rectangle(currentColor, x1, y1, x2, y2, isFilled));
                        imageAction = false;
                        break;
                }
                repaint();
            }
        });

         Button freeBtn =new Button("Free");
        add(freeBtn);
        Button eraserBtn =new Button("Eraser");
        add(eraserBtn);
        
         freeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Type = "free";
              selectedShape = "";
            }
        });
        
        eraserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Type = "eraser";
              selectedShape = "";
            }
        });
        
         addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if(Type.equals("free") || Type.equals("eraser") && selectedShape.equals("") ){
                Point P = e.getPoint();
                point.add(P);
                if (Type.equals("free")) {
                    color.add(currentColor);
                } else if(Type.equals("eraser")) {
                    color.add(Color.WHITE);
                }
                imageAction = false;
                repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e){ }
        }); 
        
         JButton undoBtn = new JButton("Delete");
        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( imageAction ){
                loadedImage = null ;
                 imageAction = false;
            }
                else if (!shapes.isEmpty()) {
                    shapes.remove(shapes.size() - 1);
                }
                else if (!point.isEmpty() && !color.isEmpty()) {
                 point.remove(point.size() - 1);
                    color.remove(color.size() - 1);
                }
                repaint();
            }
        });
        add(undoBtn);
        
         JButton clearbtn = new JButton("Clear All");
        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                point.clear();
                color.clear();
                loadedImage = null;
               imageAction = false;
                repaint();
            }

        });
        add(clearbtn);
        
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE,Color.YELLOW, Color.ORANGE, Color.PINK,Color.BLACK};
            
      
       
         for (int i = 0; i < colors.length; i++) {
            Color c = colors[i];
            JButton btn = new JButton();
            btn.setBackground(c);
            btn.setPreferredSize(new Dimension(30, 30));
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentColor = c;
                }
            });
            this.add(btn);
    }
   
         JButton openBtn = new JButton("Open");
         openBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedImage img = ImageIO.read(file);
                loadedImage = img;
                imageAction = true;
                repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
});
         add(openBtn);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (loadedImage != null) {
        g.drawImage(loadedImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
     
        for (shape s : shapes) {
            s.draw(g);
        }

          for(int i=0 ; i<point.size() ;i++){
            Point r =point.get(i);
            g.setColor(color.get(i));
            g.fillRect(r.x, r.y, 6, 6);
        } 
    }
}