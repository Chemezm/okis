package com.example;

import java.util.Arrays;

import static java.lang.Math.*;


public class Arithmetic {
    public static void main(String[] argv){
        double[] dataOne= new double[3];
        dataOne[0]=9; dataOne[1]=6; dataOne[2]=20;
        double x=-1;
        int[] dataTwo=new int[5];
        dataTwo[0]=5; dataTwo[1]=1; dataTwo[2]=10; dataTwo[3]=2; dataTwo[4]=3;

        System.out.println(Arrays.toString(corners(dataOne)));
        System.out.println(Arrays.toString(divides(x)));
        System.out.println(Arrays.toString(sort(dataTwo)));
    }

    //Нахождение углов по высотам и периметру
    public static double[] corners(double[] dataOne) {

        double[] arr = new double[2];
        if (dataOne[0] + dataOne[1] < dataOne[2]) {
            arr[0] = (dataOne[0] + dataOne[1]) / dataOne[2];
            arr[0] = asin(arr[0]);
            arr[1] = PI - arr[0];
        }
        return arr;
    }

    // определения функций с точками разрыва. Лаба 3/номер 7

    public static double[] divides(double x){

        double[] arr = new double[2];
        // для g
        if (x<=0){
            arr[0]=(2*pow(x,2))+(((3*x)+pow(sin(2*x),2*exp(1)))/(10+pow(tan(x),3)));
        }
        else if (x>0) {
            arr[0]=(2*exp(1))+sqrt(1+(2*sin(x)));
        }


        // для z

        if (x<0){
            arr[1]=sqrt(cos((2*x)+1)+((pow(exp(1),pow(x,2)))/(exp(1)+pow(x,2))));
        }
        else if (x>=0 && x<=1) {
            arr[1]=2*pow(cos(pow(sin(x),3)),2);
        }
        else if (x>1) {
            arr[1]=sqrt((1/(x-2))+abs(2*sin(pow(3*x,1.0/4.0))));
        }

        return arr;
    }

    //Сортировка массива Лаба 5/номер 2/вар 7


    public static int[] sort(int[] array){
        int temp;
        boolean sorted=true;
        while (sorted){
            sorted=false;
            for (int i=0;i< array.length-1;i++){
                if (array[i] > array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted=true;
                }
            }
        }
        return array;
    }

    // Метод для вычисления среднего арифметического массива чисел.
    public static double average(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    // Метод для вычисления факториала целого числа.
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}
