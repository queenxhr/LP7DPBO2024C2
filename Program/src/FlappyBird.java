import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    // Image Attribution
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // Player attributes
    int playerStartPosX = frameWidth / 8;
    int getPlayerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    // Pipes attributes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // Game logic
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    // Score
    int score = 0;
    JLabel scoreLabel;

    // Variable to track whether player is between pipes
    boolean playerBetweenPipes = false;

    // Constructor
    public FlappyBird() {
        setPreferredSize(new Dimension(360, 640));
        setFocusable(true);
        addKeyListener(this);

        // Load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Initialize player and pipes
        player = new Player(playerStartPosX, getPlayerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        // Start timers
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        // Initialize score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Misalnya, menggunakan font Arial, bold, ukuran 20
        add(scoreLabel);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);

        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0)); // Keep player within the frame

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);

            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            // Check collision with player
            if (player.intersects(pipe)) {
                gameOver();
            }

            // Check if player passed the pair of pipes
            if (pipe.getPosX() + pipe.getWidth() < player.getPosX() && !pipe.isPassed()) {
                // If player is between a pair of pipes and has not passed them yet
                if (playerBetweenPipes) {
                    pipe.setPassed(true);
                    score++;
                    scoreLabel.setText("Score: " + score/2);
                    playerBetweenPipes = false; // Set player not between pipes anymore
                } else {
                    playerBetweenPipes = true; // Set player between pipes
                }
            }
        }

        // Check if player hits the bottom of the frame
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver();
        }
    }

    public void gameOver() {
        gameLoop.stop();
        pipesCooldown.stop();

        int option = JOptionPane.showConfirmDialog(this, "Your Score: " + score/2 + "\nDo you want to restart?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION || option == KeyEvent.VK_R) { // Ditambahkan untuk mendeteksi ketika tombol R ditekan
            restartGame();
        } else {
            System.exit(0);
        }
    }



    public void restartGame() {
        player.setPosY(getPlayerStartPosY);
        pipes.clear();
        score = 0;
        scoreLabel.setText("Score: " + score);

        // Restart timers
        gameLoop.start();
        pipesCooldown.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
