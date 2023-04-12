package diceRoll.Model;

import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.*;

import diceRoll.Observer;
import diceRoll.Subject;

public class Dice implements Subject
{
    private int currentValue;
    private ArrayList<Observer> observers;

    public Dice() 
    {
        observers = new ArrayList<Observer>();
        roll();
    }

    public void roll() 
    {
        currentValue = (int) (Math.random() * 6) + 1;
        notifyObservers();
    }

    public void rollWithAnimation(int duration) 
    {
        int interval = 300; 
        int iterations = duration / interval;
        Timer timer = new Timer(interval, null);
        timer.addActionListener(new ActionListener() 
        {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                roll();
                count++;
                if (count >= iterations) 
                {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public int getCurrentValue() 
    {
        return currentValue;
    }

    public void register(Observer observer)
    {
        this.observers.add(observer);
    }

    public void unregister(Observer observer)
    {
        this.observers.remove(observer);
    }

    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update(this);
        }
    } 
}
