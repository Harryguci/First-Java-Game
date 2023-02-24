package Entity;

import Game.GamePanel;
import Game.KeyInput;
import util.ImageReader;
import util.RandomLocation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Zombie extends Entity {

    private BufferedImage[] _idleImage = new BufferedImage[12];
    private BufferedImage[] _walkingImage = new BufferedImage[18];
    private BufferedImage[] _attackingImage = new BufferedImage[12];
    private BufferedImage[] _dyingImage = new BufferedImage[15];
    private Direction direction = Direction.RIGHT;

    private boolean isMove = true, isAttack = false, isDied = false;
    private int _animatedCounter = 0;

    private int HP = 30;

    private int _attackingAnimation[] = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11};

    public Zombie(GamePanel gamePanel, KeyInput keyInput) {
        super(gamePanel, keyInput);
        setImage();
        setSize(new Dimension((int) (100 * 1.47), 100));

        int d = (new RandomLocation(0, 100).rand() % 2 == 0) ? -100 : gamePanel.screenWidth + 50;

        setLocation(d, new RandomLocation(0, gamePanel.screenHeight).rand());

        if (_x > gamePanel.player._x) direction = Direction.LEFT;
    }

    public boolean isDied() {
        return isDied;
    }

    public void setImage() {
        try {
            _idleImage[0] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_000.png");
            _idleImage[1] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_001.png");
            _idleImage[2] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_002.png");
            _idleImage[3] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_003.png");
            _idleImage[4] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_004.png");
            _idleImage[5] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_005.png");
            _idleImage[6] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_006.png");
            _idleImage[7] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_007.png");
            _idleImage[8] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_008.png");
            _idleImage[9] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_009.png");
            _idleImage[10] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_010.png");
            _idleImage[11] = ImageReader.Read("zombie/Idle/Minotaur_01_Idle_011.png");

            _walkingImage[0] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_000.png");
            _walkingImage[1] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_001.png");
            _walkingImage[2] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_002.png");
            _walkingImage[3] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_003.png");
            _walkingImage[4] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_004.png");
            _walkingImage[5] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_005.png");
            _walkingImage[6] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_006.png");
            _walkingImage[7] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_007.png");
            _walkingImage[8] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_008.png");
            _walkingImage[9] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_009.png");
            _walkingImage[10] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_010.png");
            _walkingImage[11] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_011.png");
            _walkingImage[12] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_012.png");
            _walkingImage[13] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_013.png");
            _walkingImage[14] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_014.png");
            _walkingImage[15] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_015.png");
            _walkingImage[16] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_016.png");
            _walkingImage[17] = ImageReader.Read("zombie/Walking/Minotaur_01_Walking_017.png");

            _attackingImage[0] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_000.png");
            _attackingImage[1] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_001.png");
            _attackingImage[2] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_002.png");
            _attackingImage[3] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_003.png");
            _attackingImage[4] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_004.png");
            _attackingImage[5] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_005.png");
            _attackingImage[6] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_006.png");
            _attackingImage[7] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_007.png");
            _attackingImage[8] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_008.png");
            _attackingImage[9] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_009.png");
            _attackingImage[10] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_010.png");
            _attackingImage[11] = ImageReader.Read("zombie/Attacking/Minotaur_01_Attacking_011.png");

            _dyingImage[0] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_000.png");
            _dyingImage[1] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_001.png");
            _dyingImage[2] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_002.png");
            _dyingImage[3] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_003.png");
            _dyingImage[4] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_004.png");
            _dyingImage[5] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_005.png");
            _dyingImage[6] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_006.png");
            _dyingImage[7] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_007.png");
            _dyingImage[8] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_008.png");
            _dyingImage[9] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_009.png");
            _dyingImage[10] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_010.png");
            _dyingImage[11] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_011.png");
            _dyingImage[12] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_012.png");
            _dyingImage[13] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_013.png");
            _dyingImage[14] = ImageReader.Read("zombie/Dying/Minotaur_01_Dying_014.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {
        if (isDied) return;

        int xPlayer = gamePanel.player._x;
        int yPlayer = gamePanel.player._y;

        int dx = _x - xPlayer;
        int dy = _y - yPlayer;

        if (Math.abs(_x + _width / 2 - gamePanel.player._x - gamePanel.player._width / 2) >= 25 && dx < 0) _x++;
        else
            _x--;

        if (dy < 0) _y++;
        else
            _y--;

        if (_x > gamePanel.player._x) direction = Direction.LEFT;
        else
            direction = Direction.RIGHT;

        if (Math.abs(_x + _width / 2 - gamePanel.player._x - gamePanel.player._width / 2) < 30) {
            isAttack = true;
        } else
            isAttack = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (isDied) {
            if (_animatedCounter >= _dyingImage.length) {
                isDied = false;
                int d = (new RandomLocation(0, 100).rand()) % 2 == 0 ? -100 : gamePanel.screenWidth + 50;

                setLocation(d, new RandomLocation(0, gamePanel.screenHeight).rand());
                HP = 30;
                return;
            }

            if (direction == Direction.RIGHT) {
                g2d.drawImage(_dyingImage[_animatedCounter++], _x, _y, _width, _height, this);

            } else {
                g2d.drawImage(_dyingImage[_animatedCounter++], _x + _width, _y, -_width, _height, this);
            }

        } else if (isAttack) {
            if (_animatedCounter >= _attackingAnimation.length)
                _animatedCounter = 0;

            if (direction == Direction.RIGHT) {
                g2d.drawImage(_attackingImage[_attackingAnimation[_animatedCounter++]], _x, _y, _width, _height, this);

            } else {
                g2d.drawImage(_attackingImage[_attackingAnimation[_animatedCounter++]], _x + _width, _y, -_width, _height, this);
            }
        } else if (isMove) {
            if (_animatedCounter >= _walkingImage.length) _animatedCounter = 0;

            if (direction == Direction.RIGHT)
                g2d.drawImage(_walkingImage[_animatedCounter++], _x, _y, _width, _height, this);
            else {
                g2d.drawImage(_walkingImage[_animatedCounter++], _x + _width, _y, -_width, _height, this);
            }
        }

        g2d.setColor(Color.RED);

        // Draw HP bar
        for (int i = 0; i <= 3; i++) {
            if (HP >= i * 10)
                g2d.fillRect(_x + _width / 3 * i, _y, _width / 3 - 5, 5);
            else
                g2d.drawRect(_x + _width / 3 * i, _y, _width / 3 - 5, 5);
        }
    }

    public void kill() {

        HP--;

        if (HP == 0) {

            _animatedCounter = 0;
            isDied = true;

            gamePanel.incScore();
        }

    }
}
