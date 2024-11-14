package org.systemeReparti.fibonacci;

public class FibonacciMonoProcesseur {
    private int nombre;
    private int resultat;
    public FibonacciMonoProcesseur(int n){
        nombre=n;}
    private int calculElementaire (int n){
        if (n<=1) {
            return n;
        }
        else{
            return calculElementaire(n-1)+calculElementaire(n-2);
        }
    }
    public void compute (){
        resultat= calculElementaire(nombre);
    }
    public int resultat(){
        return resultat;
    }
    public static void main (String [] args){
        long depart = System.currentTimeMillis();
        FibonacciMonoProcesseur f = new FibonacciMonoProcesseur(45);
        f.compute();
        int resultatFinal = f.resultat();
        System.out.println("Résultat final : " + resultatFinal);
        long fin = System.currentTimeMillis();
        System.out.println("Time : "+(fin-depart)+" ms");
    }
}
