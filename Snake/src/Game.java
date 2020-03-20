import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game
{
    public static void main(String[] args)
    {
        Game game = new Game();
        game.run();
    }

    private final int HEIGHT = 600;
    private final int WIDTH = 800;

    private Board board;
    private Food apple;
    private Snake snake;
    private Frame window;

    private boolean isRunning = true;
    private Button startButton = new Button();
    private Button playAgainButton = new Button();

    Game()
    {
        apple = new Food();
        snake = new Snake(10);
        board = new Board(WIDTH, HEIGHT);

        initUI();
    }

    private void initUI()
    {
        window = new Frame();
        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);
        window.setVisible(true);
        window.setTitle("Snake");

        startButton.setLabel("Start");
        playAgainButton.setLabel("Play Again");
    }

    public void run()
    {
        displayMenu();
    }

    private void runGame()
    {
        window.removeAll();
        window.add(board);
        window.repaint();
        window.revalidate();

        while (isRunning)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            board.tick();
            board.checkApple();
            window.repaint();
            board.repaint();

            if(board.checkCollision())
            {
                isRunning = false;
            }
        }

        window.remove(board);
        displayPlayAgain();
    }

    private void displayMenu()
    {
        window.add(startButton);
        window.repaint();
        window.revalidate();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.remove(startButton);
                runGame();
            }
        });
    }

    private void displayPlayAgain()
    {
        window.add(playAgainButton);
        window.repaint();
        window.revalidate();

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.remove(playAgainButton);
                runGame();
            }
        });
    }
}
