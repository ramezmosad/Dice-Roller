package diceRoll.Controller;

import java.util.ArrayList;

import diceRoll.Model.*;
import diceRoll.View.*;
import diceRoll.ControllerInterface;

public class DiceController implements ControllerInterface, DiceGUI.DiceClickListener, DiceGUI.RollAllClickListener
{
    private ArrayList<Dice> dice;
    private DiceGUI gui;
    private DiceObserver diceObserver;

    public DiceController(DiceGUI gui) 
    {
        this.gui = gui;
        dice = new ArrayList<>();
        diceObserver = new DiceObserver(gui);
        gui.addDiceClickListener(this);
        gui.addRollAllClickListener(this);
        for (int i = 0; i < 3; i++) 
        {
            Dice die = new Dice();
            die.register(diceObserver);
            dice.add(die);
            gui.getDiceComponents().get(i).setDice(die);
        }
        diceObserver.updateView(dice);
    }
    

    public void rollDice(int index) 
    {
        dice.get(index).rollWithAnimation(1500);
    }

    @Override
    public void onDiceClick(int index) 
    {
        dice.get(index).rollWithAnimation(1500);
    }

    @Override
    public void onRollAllClick() 
    {
        for (Dice die : dice) 
        {
            die.rollWithAnimation(1500);
        }
    }
}
