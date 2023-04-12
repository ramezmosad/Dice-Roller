package diceRoll.View;

import diceRoll.Observer;
import diceRoll.Subject;
import diceRoll.Model.Dice;

import java.util.ArrayList;

public class DiceObserver implements Observer 
{
    private DiceGUI gui;

    public DiceObserver(DiceGUI gui) 
    {
        this.gui = gui;
    }

    @Override
    public void update(Subject subject) 
    {
        if (subject instanceof Dice) 
        {
            Dice die = (Dice) subject;
            updateView(die);
        }
    }

    public void updateView(ArrayList<Dice> dice) 
    {
        for (int i = 0; i < dice.size(); i++) 
        {
            Dice die = dice.get(i);
            int value = die.getCurrentValue();
            gui.updateDiceImage(i, value);
        }
    }

    public void updateView(Dice die) 
    {
        int index = gui.getDiceIndex(die);
        if (index != -1) 
        {
            int value = die.getCurrentValue();
            gui.updateDiceImage(index, value);
        }
    }
}
