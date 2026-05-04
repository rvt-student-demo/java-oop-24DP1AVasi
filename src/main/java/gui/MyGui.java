package gui;

import javax.swing.JFrame;

public class MyGui{
    public static void main(String[] args){
        JFrame frame = new JFrame("House");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        House house = new House();
        frame.add(house);

        frame.setSize(1000, 600);
        frame.setVisible(true);
        
    }
}