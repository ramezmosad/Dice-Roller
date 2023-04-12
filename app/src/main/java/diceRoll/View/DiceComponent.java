package diceRoll.View;

import diceRoll.Model.*;
import javax.swing.*;
import java.awt.*;

public class DiceComponent extends JComponent 
{
    private int value;
    private Dice dice;

    public DiceComponent(int value, Dice dice) 
    {
        this.value = value;
        this.dice = dice;
        setPreferredSize(new Dimension(60, 80));
    }

    public int getValue() 
    {
        return value;
    }

    public Dice getDice() 
    {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }    

    public void setValue(int value) 
    {
        this.value = value;
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) 
    {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawRect(0, 0, getWidth(), getHeight());

        int dotSize = 13;
        int halfDotSize = dotSize / 2;
        int padding = 10;
        int centerX = getWidth() / 2 - halfDotSize;
        int centerY = getHeight() / 2 - halfDotSize;

        if (value % 2 == 1) 
        {
            drawDot(graphics, centerX, centerY, dotSize);
        }

        if (value > 1) 
        {
            drawDot(graphics, padding, padding, dotSize);
            drawDot(graphics, getWidth() - padding - dotSize, getHeight() - padding - dotSize, dotSize);
        } 

        if (value > 3) 
        {
            drawDot(graphics, padding, getHeight() - padding - dotSize, dotSize);
            drawDot(graphics, getWidth() - padding - dotSize, padding, dotSize);
        }

        if (value == 6) 
        {
            drawDot(graphics, padding, centerY, dotSize);
            drawDot(graphics, getWidth() - padding - dotSize, centerY, dotSize);
        }
    }

    public void drawDot(Graphics graphics, int x, int y, int size) 
    {
        graphics.fillOval(x, y, size, size);
    }

}

