package Entity.gui;

import java.awt.*;

public class GString {
    private static void drawString(Graphics2D g2d, String text, int x, int y) {
        for (String line : text.split("\n"))
            g2d.drawString(line, x, y += g2d.getFontMetrics().getHeight());
    }

    public static void printString(Graphics2D g2d, String content, int x, int y, int style) {
        switch (style) {
            case 0:
                drawString(g2d, content, x, y);
                break;

            case 1:
                System.out.println();
                break;

            default:
                break;
        }
    }
}
