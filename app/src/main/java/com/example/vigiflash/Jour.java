package com.example.vigiflash;

public class Jour {

    public int value;

    Jour Jour1 = new Jour();
    Jour Jour2 = new Jour();
    Jour Jour3 = new Jour();
    Jour Jour4 = new Jour();

    public void SetJours() {
        int[] dvalues = MainActivity.getDvalues();
        Jour1.value = dvalues[0];
        Jour2.value = dvalues[1];
        Jour3.value = dvalues[2];
        Jour4.value = dvalues[3];
        System.out.println("The value of Jour1 is : " + Jour1.value);
    }





}
