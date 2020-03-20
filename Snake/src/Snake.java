public class Snake
{
    private SnakePart[] snake;

    Snake(int length)
    {
        snake = new SnakePart[length];
    }

    public SnakePart[] getSnake() {
        return snake;
    }

    public void extendSnake()
    {
        SnakePart[] newSnake = new SnakePart[snake.length+1];

        for(int i = 0; i < snake.length; i++)
        {
            newSnake[i] = snake[i];
        }

        newSnake[newSnake.length-1] = new SnakePart(snake[snake.length-1].getX(), snake[snake.length-1].getY());
        snake = newSnake;
    }

    public void createSnake()
    {
        int multiplier = 0;
        for(int i = snake.length-1; i >= 0; i--)
        {
            snake[i] = new SnakePart(20 + (multiplier * 10), 100);
            multiplier++;
        }
    }
}
