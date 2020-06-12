import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends Panel implements KeyListener
{
    private int height;
    private int width;

    private final int UP = 0;
    private final int DOWN = 1;
    private final int RIGHT = 2;
    private final int LEFT = 3;
    private int direction = RIGHT;

    private Food apple = new Food();
    private Snake snake = new Snake(10);

    Board(int width, int height)
    {
        this.width = width;
        this.height = height;

        addKeyListener(this);
        setSize(width, height);
        setVisible(true);
        setBackground(Color.white);
        setFocusable(true);
        apple.createApple(width, height);
        snake.createSnake();
    }

    private void doDrawing(Graphics g)
    {
        g.setColor(Color.RED);
        g.drawRect(apple.getX(), apple.getY(), 8,8);
        g.fillRect(apple.getX(), apple.getY(), 8,8);

        for(int i = 0; i < snake.getSnake().length; i++)
        {
            g.setColor(Color.BLACK);
            g.drawRect(snake.getSnake()[i].getX(), snake.getSnake()[i].getY(), 8,8);
            g.fillRect(snake.getSnake()[i].getX(), snake.getSnake()[i].getY(), 8,8);
        }
    }

    public void paint(Graphics g)
    {
        doDrawing(g);
    }

    public boolean checkCollision()
    {
        boolean isColliding = false;

        if(snake.getSnake()[0].getX() < 0 || snake.getSnake()[0].getX() > width)
        {
            isColliding = true;
        }
        if(snake.getSnake()[0].getY() < 0 || snake.getSnake()[0].getY() > height)
        {
            isColliding = true;
        }

        for(int i = 1; i < snake.getSnake().length; i++)
        {
            if(snake.getSnake()[i].getX() == snake.getSnake()[0].getX() && snake.getSnake()[i].getY() == snake.getSnake()[0].getY())
            {
                isColliding = true;
            }
        }
        return isColliding;
    }

    public void checkApple()
    {
        if(snake.getSnake()[0].getX() == apple.getX() && snake.getSnake()[0].getY() == apple.getY())
        {
            apple.createApple(width, height);

            for(int i = 0; i < 5; i++)
            {
                snake.extendSnake();
            }
        }
    }

    public void tick()
    {
        SnakePart[] tempSnake = new SnakePart[snake.getSnake().length];

        for(int i = 0; i < tempSnake.length; i++)
        {
            tempSnake[i] = new SnakePart(snake.getSnake()[i].getX(), snake.getSnake()[i].getY());
        }

        switch(direction)
        {
            case UP:
                snake.getSnake()[0].setY(-10);
                break;
            case DOWN:
                snake.getSnake()[0].setY(10);
                break;
            case LEFT:
                snake.getSnake()[0].setX(-10);
                break;
            case RIGHT:
                snake.getSnake()[0].setX(10);
                break;
        }

        for(int i = 1; i < snake.getSnake().length; i++)
        {
            snake.getSnake()[i] = tempSnake[i-1];
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_UP:
                if(direction != DOWN)
                {
                    direction = UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(direction != UP)
                {
                    direction = DOWN;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(direction != LEFT)
                {
                    direction = RIGHT;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(direction != RIGHT)
                {
                    direction = LEFT;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
