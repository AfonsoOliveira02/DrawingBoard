package io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.*;

public class Grid {
    private int col;
    private int row;
    private int cellsize = 40;
    private int PADDING = 5;
    private int x = PADDING;
    private int y = PADDING;
    private Cursor cursor;
    private Rectangle[][] rectangles;
    private boolean[][] paintedCells;





    public Grid(int col, int row,Cursor cursor) {
        this.cursor = cursor;
        this.col = col;
        this.row = row;
        rectangles = new Rectangle[col][row];
        paintedCells = new boolean[col][row];
        Rectangle rectangle = new Rectangle(x, y, getWidth(), getHeight());
        rectangle.setColor(Color.WHITE);
        rectangle.draw();
        createRectangles();

    }
     public Rectangle[][] createRectangles(){
         for (int i = 0; i < rectangles.length; i++) {
             for (int j = 0; j < rectangles[i].length; j++) {
                 rectangles[i][j] = new Rectangle(PADDING +(i * cellsize),PADDING + (j * cellsize),cellsize,cellsize);
                  rectangles[i][j].draw();
                  paintedCells[i][j] = false;
             }

         }
         return rectangles;
     }
    public void updatePaint() {
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                if (cursor.getRectangle().getX() == rectangles[i][j].getX() &&
                        cursor.getRectangle().getY() == rectangles[i][j].getY()) {
                    if (!paintedCells[i][j]) {
                        rectangles[i][j].setColor(Color.BLACK);
                        rectangles[i][j].fill();
                        paintedCells[i][j] = true;
                    } else {
                        rectangles[i][j].draw();
                        paintedCells[i][j] = false;
                    }
         }
         }
        }
    }
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < paintedCells.length; i++) {
                for (int j = 0; j < paintedCells[i].length; j++) {
                    writer.write(paintedCells[i][j] ? "1" : "0");
                }
                writer.newLine();
            }
            System.out.println("Grid saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            for (int i = 0; i < paintedCells.length; i++) {
                String line = reader.readLine();
                for (int j = 0; j < line.length(); j++) {
                    paintedCells[i][j] = line.charAt(j) == '1';
                    if (paintedCells[i][j]) {
                        rectangles[i][j].setColor(Color.BLACK);
                        rectangles[i][j].fill();
                    } else {
                        rectangles[i][j].draw();
                    }
                }
            }
            System.out.println("Grid loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getcellSize() {
        return cellsize;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getWidth() {
        return (getCol() * getcellSize());
    }

    public int getHeight() {
        return (getRow() * getcellSize());
    }

    public int rowToY(int row) {
        return y = (row * cellsize);
    }

    public int colToX(int col) {
        return x = (col * cellsize);
    }

    public int getPADDING() {
        return PADDING;
    }
}
