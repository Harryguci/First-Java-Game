package Entity;

import Game.GamePanel;
import Game.KeyInput;
import util.ImageReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    private final BufferedImage[] _walkingImages = new BufferedImage[18];
    private final BufferedImage[] _standingImage = new BufferedImage[12];
    private final BufferedImage[] _attackingImage = new BufferedImage[12];
    private final BufferedImage[] _dyingImage = new BufferedImage[15];
    private boolean isMove = false, isAttack = false, isDied = false;
    private int _countDelay = 0;
    private int hurtTime = 0;
    private int HP = 500;

    Direction direction = Direction.RIGHT;

    public Player(GamePanel gamePanel, KeyInput keyInput) {
        super(gamePanel, keyInput);
        setImage();

        setCollision(true);
        // setCollisionArea(new Rectangle());
        setSize(new Dimension((int) (100 * 1.47), 100));
    }

    public void setImage() {
        try {
            _standingImage[0] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_000.png");
            _standingImage[1] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_001.png");
            _standingImage[2] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_002.png");
            _standingImage[3] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_003.png");
            _standingImage[4] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_004.png");
            _standingImage[5] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_005.png");
            _standingImage[6] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_006.png");
            _standingImage[7] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_007.png");
            _standingImage[8] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_008.png");
            _standingImage[9] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_009.png");
            _standingImage[10] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_010.png");
            _standingImage[11] = ImageReader.Read("Player1/Idle/Minotaur_02_Idle_011.png");

            _walkingImages[0] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_000.png");
            _walkingImages[1] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_001.png");
            _walkingImages[2] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_002.png");
            _walkingImages[3] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_003.png");
            _walkingImages[4] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_004.png");
            _walkingImages[5] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_005.png");
            _walkingImages[6] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_006.png");
            _walkingImages[7] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_007.png");
            _walkingImages[8] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_008.png");
            _walkingImages[9] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_009.png");
            _walkingImages[10] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_010.png");
            _walkingImages[11] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_011.png");
            _walkingImages[12] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_012.png");
            _walkingImages[13] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_013.png");
            _walkingImages[14] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_014.png");
            _walkingImages[15] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_015.png");
            _walkingImages[16] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_016.png");
            _walkingImages[17] = ImageReader.Read("Player1/Walking/Minotaur_02_Walking_017.png");

            _attackingImage[0] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_000.png");
            _attackingImage[1] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_001.png");
            _attackingImage[2] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_002.png");
            _attackingImage[3] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_003.png");
            _attackingImage[4] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_004.png");
            _attackingImage[5] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_005.png");
            _attackingImage[6] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_006.png");
            _attackingImage[7] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_007.png");
            _attackingImage[8] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_008.png");
            _attackingImage[9] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_009.png");
            _attackingImage[10] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_010.png");
            _attackingImage[11] = ImageReader.Read("Player1/Attacking/Minotaur_02_Attacking_011.png");

            _dyingImage[0] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_000.png");
            _dyingImage[1] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_001.png");
            _dyingImage[2] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_002.png");
            _dyingImage[3] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_003.png");
            _dyingImage[4] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_004.png");
            _dyingImage[5] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_005.png");
            _dyingImage[6] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_006.png");
            _dyingImage[7] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_007.png");
            _dyingImage[8] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_008.png");
            _dyingImage[9] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_009.png");
            _dyingImage[10] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_010.png");
            _dyingImage[11] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_011.png");
            _dyingImage[12] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_012.png");
            _dyingImage[13] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_013.png");
            _dyingImage[14] = ImageReader.Read("Player1/Dying/Minotaur_02_Dying_014.png");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Setter & Getter
    public int getHP() {
        return this.HP;
    }

    public void setLocation(int x, int y) {
        _x = x;
        _y = y;
    }

    public boolean isDied() {
        return isDied;
    }

    @Override
    public void update() {

        if (HP <= 0) {
            isDied = true;
            return;
        }
        if (keyInput.isUp()) {
            move(_x, _y - _speed);
            isMove = true;
        } else if (keyInput.isDown()) {
            move(_x, _y + _speed);
            isMove = true;
        } else if (keyInput.isLeft()) {
            move(_x - _speed, _y);
            isMove = true;
            direction = Direction.LEFT;
        } else if (keyInput.isRight()) {
            move(_x + _speed, _y);
            isMove = true;
            direction = Direction.RIGHT;
        } else
            isMove = false;

        isAttack = keyInput.isSpace();

        if (isAttack) {
            for (int i = 0; i < 5; i++) {
                if (gamePanel.zombies[i].isDied()) continue;

                int dx = Math.abs(_x + _width / 2 - gamePanel.zombies[i]._x - gamePanel.zombies[i]._width / 2);
                int dy = Math.abs(_y + _height / 2 - gamePanel.zombies[i]._y - gamePanel.zombies[i]._height / 2);

                if (dx < 30 && dy < 20) {
                    gamePanel.zombies[i].kill();
                    // Once time can only attack a Zombie. So we will return below.
                    return;
                }
            }
        }
    }


    @Override
    public void draw(Graphics2D g2d) {

        if (_standingImage != null) {

            // Shadow:
            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillOval(_x + 40, _y + _height - 20, _width - 80, 20);

            if (direction == Direction.RIGHT) {
                // Player was died
                if (isDied) {
                    // _dyingImage
                    if (_countDelay / 2 >= _dyingImage.length) _countDelay = _dyingImage.length * 2 - 2;
                    g2d.drawImage(_dyingImage[_countDelay++ / 2], _x, _y, _width, _height, this);

                    return;
                }

                if (isAttack) {
                    if (_countDelay / 2 >= _attackingImage.length)
                        _countDelay = 0;
                    g2d.drawImage(_attackingImage[_countDelay++ / 2], _x, _y, _width, _height, this);
                } else {
                    if (isMove) {
                        if (_countDelay / 2 >= _walkingImages.length) _countDelay = _dyingImage.length - 1;
                        g2d.drawImage(_walkingImages[_countDelay++ / 2], _x, _y, _width, _height, this);
                    } else {
                        if (_countDelay >= _standingImage.length) _countDelay = 0;
                        g2d.drawImage(_standingImage[_countDelay++ / 2], _x, _y, _width, _height, this);
                    }
                }
            } else if (direction == Direction.LEFT) {
                // Player was died
                if (isDied) {
                    // _dyingImage
                    if (_countDelay / 2 >= _dyingImage.length) _countDelay = _dyingImage.length * 2 - 2;
                    g2d.drawImage(_dyingImage[_countDelay++ / 2], _x + _width, _y, -_width, _height, this);

                    return;
                }

                if (isAttack) {
                    if (_countDelay / 2 >= _attackingImage.length)
                        _countDelay = 0;
                    g2d.drawImage(_attackingImage[_countDelay++ / 2], _x + _width, _y, -_width, _height, this);
                } else {
                    if (isMove) {
                        if (_countDelay / 2 >= _walkingImages.length) _countDelay = 0;
                        g2d.drawImage(_walkingImages[_countDelay++ / 2], _x + _width, _y, -_width, _height, this);
                    } else {
                        if (_countDelay / 2 >= _standingImage.length) _countDelay = 0;
                        g2d.drawImage(_standingImage[_countDelay++ / 2], _x + _width, _y, -_width, _height, this);
                    }
                }
            }

        } else {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(_x, _y, _width, _height);
        }

        if (hurtTime > 0) {
            Color color1 = new Color(255, 0, 0, 150);
            Color color2 = new Color(200, 50, 100, 150);
            g2d.setColor(hurtTime % 10 < 5 ? color1 : color2);
            g2d.fillRect(_x + 40, _y + 10, _width - 80, _height - 10);
            hurtTime--;
        }

        g2d.setColor(Color.RED);

        // Draw HP bar
        for (int i = 0; i <= 10; i++) {
            if (HP >= i * 10)
                g2d.fillRect(_x + _width / 10 * i, _y, _width / 10 - 5, 5);
        }
    }

    public void hurt(int d) {
        HP -= d;
        hurtTime = 50;

        if (HP < 0) {
            HP = 0;
            _countDelay = 0;
        }
    }

    public boolean canMove(int x, int y) {

        int row = y / gamePanel.titleSize;
        int col = x / gamePanel.titleSize;

        return !gamePanel.walls[row + 1][col + 1];
    }

    public void move(int x, int y) {
        if (canMove(x, y)) {
            _x = x;
            _y = y;
            if (_x < -_width / 2) {
                _x = gamePanel.screenWidth - _width;
                gamePanel.numMap--;
                gamePanel.numBackground--;

                if (gamePanel.numBackground < 0) gamePanel.numBackground = gamePanel.pathBackground.length - 1;

                if (gamePanel.numMap < 0) gamePanel.numMap = gamePanel.mapName.length - 1;

                gamePanel.updateBackground(gamePanel.numBackground);
                gamePanel.setMap(gamePanel.mapName[gamePanel.numMap]);
            } else if (_x + _width - 50 >= gamePanel.screenWidth) {
                _x = -(_width / 2);
                gamePanel.numMap++;
                gamePanel.numBackground++;

                if (gamePanel.numBackground >= gamePanel.pathBackground.length) gamePanel.numBackground = 0;

                if (gamePanel.numMap >= gamePanel.mapName.length) gamePanel.numMap = 0;

                gamePanel.updateBackground(gamePanel.numBackground);
                gamePanel.setMap(gamePanel.mapName[gamePanel.numMap]);
            }
            if (_y <= 0) _y = 0;
            else if (_y >= gamePanel.screenHeight) _y = 0;
        }
    }

}
