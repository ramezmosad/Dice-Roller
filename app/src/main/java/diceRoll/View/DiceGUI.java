package diceRoll.View;

import javax.swing.*;

import diceRoll.Model.Dice;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;

public class DiceGUI extends JPanel 
{
    private ArrayList<DiceComponent> diceComponents;
    private ArrayList<DiceClickListener> diceClickListeners = new ArrayList<>();
    private ArrayList<RollAllClickListener> rollAllClickListeners = new ArrayList<>();
    private JButton rollAllButton;

    public DiceGUI() 
    {
        setLayout(new GridLayout(1, 3));
        diceComponents = new ArrayList<>();
        rollAllButton = new JButton("Roll All");
        add(rollAllButton);
        rollAllButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                fireRollAllClick();
            }
        });
        
        for (int i = 0; i < 3; i++) 
        {
            DiceComponent diceComponent = new DiceComponent(1, null);
            diceComponents.add(diceComponent);
            add(diceComponent);
            int index = i;
            diceComponent.addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    fireDiceClick(index);
                }
            });
        }
    }

    public int getDiceIndex(Dice die) 
    {
        for (DiceComponent diceComponent : diceComponents) 
        {
            if (diceComponent.getDice() == die) 
            {
                return diceComponents.indexOf(diceComponent);
            }
        }
        return -1;
    }
    
    public void updateDiceImage(int index, int value) 
    {
        diceComponents.get(index).setValue(value);
    }

    public ArrayList<DiceComponent> getDiceComponents() 
    {
        return diceComponents;
    }

    public void addDiceClickListener(DiceClickListener listener) 
    {
        diceClickListeners.add(listener);
    }

    public void removeDiceClickListener(DiceClickListener listener) 
    {
        diceClickListeners.remove(listener);
    }

    protected void fireDiceClick(int index) 
    {
        for (DiceClickListener listener : diceClickListeners) 
        {
            listener.onDiceClick(index);
        }
    }

    public interface DiceClickListener 
    {
        void onDiceClick(int index);
    }

    public void addRollAllClickListener(RollAllClickListener listener) 
    {
        rollAllClickListeners.add(listener);
    } 

    public void removeRollAllClickListener(RollAllClickListener listener) 
    {
        rollAllClickListeners.remove(listener);
    }

    protected void fireRollAllClick() 
    {
        for (RollAllClickListener listener : rollAllClickListeners) 
        {
            listener.onRollAllClick();
        }
    }

    public interface RollAllClickListener 
    {
        public void onRollAllClick();
    }

}

