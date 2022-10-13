package main;

public class Help {

    public Help() {
    }


    public void printB(byte[] array){
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(" "+array[i]);
            System.out.println();
        }

    }
}
