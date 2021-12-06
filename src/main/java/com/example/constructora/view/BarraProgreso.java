package com.example.constructora.view;

import javax.swing.*;

public class BarraProgreso extends JFrame  implements Runnable {
    JProgressBar progressBar;

    private void init() {
        for (int i = 0; i < 1000; i++) {
            progressBar.setValue(i);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        progressBar = new JProgressBar(0,2000);
        progressBar.setBounds(40,40,160,30);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        add(progressBar);
        setSize(250, 150);
        setLayout(null);
        setVisible(true);
        init();
    }
}
