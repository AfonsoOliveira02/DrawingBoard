package io.codeforall.fanstatics;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Cursor implements KeyboardHandler {
    private Rectangle rectangle;
    private Keyboard keyboard;
    private Grid grid = new Grid(10, 10, this);
    private boolean isPainting = false;

    public Cursor() {
        rectangle = new Rectangle(grid.getX(), grid.getY(), grid.getcellSize(), grid.getcellSize());
        rectangle.draw();
        rectangle.setColor(Color.GREEN);
        rectangle.fill();
        initKeyboard();

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isPainting() {
        return isPainting = !isPainting;
    }

    private void initKeyboard() {
        this.keyboard = new Keyboard(this);
        KeyboardEvent moveRight = new KeyboardEvent();
        moveRight.setKey(KeyboardEvent.KEY_RIGHT);
        moveRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveRight);
        KeyboardEvent moveLeft = new KeyboardEvent();
        moveLeft.setKey(KeyboardEvent.KEY_LEFT);
        moveLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveLeft);
        KeyboardEvent moveUp = new KeyboardEvent();
        moveUp.setKey(KeyboardEvent.KEY_UP);
        moveUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveUp);
        KeyboardEvent moveDown = new KeyboardEvent();
        moveDown.setKey(KeyboardEvent.KEY_DOWN);
        moveDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(moveDown);
        KeyboardEvent paint = new KeyboardEvent();
        paint.setKey(KeyboardEvent.KEY_SPACE);
        paint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(paint);
        KeyboardEvent unpaint = new KeyboardEvent();
        unpaint.setKey(KeyboardEvent.KEY_SPACE);
        unpaint.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(unpaint);
        KeyboardEvent save = new KeyboardEvent();
        save.setKey(KeyboardEvent.KEY_S);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(save);
        KeyboardEvent load = new KeyboardEvent();
        load.setKey(KeyboardEvent.KEY_L);
        load    .setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(load);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_RIGHT:
                if (rectangle.getX() < (grid.getWidth() - grid.getcellSize())) {
                    rectangle.translate(grid.getcellSize(), 0);
                    if(isPainting) {
                        grid.updatePaint();
                    }
                }
                System.out.println(grid.getWidth());
                System.out.println(rectangle.getX());

                break;
            case KeyboardEvent.KEY_LEFT:
                if (rectangle.getX() != grid.getPADDING()) {
                    rectangle.translate(-grid.getcellSize(), 0);
                }
                if(isPainting){
                    grid.updatePaint();
                }
                break;
            case KeyboardEvent.KEY_UP:
                if (rectangle.getY() != grid.getPADDING()){
                    rectangle.translate(0, -grid.getcellSize());}
                if(isPainting){
                    grid.updatePaint();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (rectangle.getY() < (grid.getHeight() - grid.getcellSize())){
                    rectangle.translate(0, grid.getcellSize());}
                if(isPainting){
                    grid.updatePaint();
                }

                break;
            case KeyboardEvent.KEY_SPACE:
                isPainting = !isPainting;
                break;
            case KeyboardEvent.KEY_S: // Save the grid
                grid.saveToFile("rsc/grid.txt");
                break;
            case KeyboardEvent.KEY_L: // Load the grid
                grid.loadFromFile("rsc/grid.txt");
                break;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_SPACE:
                if (isPainting) {
                    isPainting = false;
                }

                System.out.println(isPainting);

        }

    }

}

