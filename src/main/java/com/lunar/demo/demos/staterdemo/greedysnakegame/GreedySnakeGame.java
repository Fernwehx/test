package com.lunar.demo.demos.staterdemo.greedysnakegame;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

/**
 * game: GreedySnake
 */
public class GreedySnakeGame extends JFrame implements ActionListener {
    private Timer timer;
    private int delay = 50;
    private int score = 0;
    private int size = 10;
    private int[] x = new int[100];
    private int[] y = new int[100];
    private int appleX, appleY;
    private boolean gameover = false;
    private Direction direction = Direction.RIGHT;

    public GreedySnakeGame() {
        setTitle("贪吃蛇游戏");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Panel panel = new Panel();
        add(panel);

        timer = new Timer(delay, this);
        timer.start();
        generateApple();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });

        setFocusable(true);
    }


    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (direction != Direction.DOWN) {
                    direction = Direction.UP;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
                break;
            case KeyEvent.VK_R:
                restartGame();
                break;
        }
    }

    private void restartGame() {
        // 重新初始化游戏状态
        score = 0;
        gameover = false;
        direction = Direction.RIGHT;
        generateApple();
        initSnake();
    }

    private void initSnake() {
        // 初始化蛇的起始位置
        int startX = getWidth() / 2;
        int startY = getHeight() / 2;
        for (int i = 0; i < x.length; i++) {
            x[i] = startX - i * size;
            y[i] = startY;
        }
    }

    private void generateApple() {
        appleX = (int) (Math.random() * (getWidth() / size)) * size;
        appleY = (int) (Math.random() * (getHeight() / size)) * size;
    }

    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            score++;
            generateApple();
            extendSnake();
        }
    }

    private void extendSnake() {
        // 在蛇的尾部添加一个新的块
        x = Arrays.copyOf(x, x.length + 1);
        y = Arrays.copyOf(y, y.length + 1);
    }

    private void moveSnake() {
        for (int i = x.length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case UP:
                y[0] -= size;
                break;
            case DOWN:
                y[0] += size;
                break;
            case LEFT:
                x[0] -= size;
                break;
            case RIGHT:
                x[0] += size;
                break;
        }

        checkCollision();
    }

    private void checkCollision() {
        for (int i = 1; i < x.length; i++) {
            if (x[i] == x[0] && y[i] == y[0]) {
                gameover = true;
            }
        }

        if (x[0] < 0 || x[0] >= getWidth() || y[0] < 0 || y[0] >= getHeight()) {
            gameover = true;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameover) {
            moveSnake();
            checkApple();
            repaint();
        }
    }

    private class Panel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBackground(g);
            drawSnake(g);
            drawApple(g);
            drawScore(g);
            if (gameover) {
                drawGameOver(g);
            }
        }

        private void drawBackground(Graphics g) {
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        private void drawSnake(Graphics g) {
            for (int i = 0; i < x.length; i++) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], size, size);
            }
        }

        private void drawApple(Graphics g) {
            g.setColor(Color.red);
            g.fillRect(appleX, appleY, size, size);
        }

        private void drawScore(Graphics g) {
            g.setColor(Color.white);
            g.setFont(new Font("Serif", Font.BOLD, 16));
            g.drawString("Score: " + score, 10, 20);
        }

        private void drawGameOver(Graphics g) {
            g.setColor(Color.black);
            g.setFont(new Font("Serif", Font.BOLD, 36));
            g.drawString("Game Over", getWidth() / 2 - 70, getHeight() / 2);
            g.setColor(Color.white);
            g.drawString("Press R to Restart", getWidth() / 2 - 50, getHeight() / 2 + 50);
        }
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GreedySnakeGame greedySnakeGame = new GreedySnakeGame();
            greedySnakeGame.setVisible(true);
        });
    }
}
