package br.com.alura.screenmatch.exercicios1;

public class Quatro {
    public static void main(String[] args) {
        System.out.println(ehPalindromo("socorro me subi no onibus em morrocos")); // Saída: true
        System.out.println(ehPalindromo("Java"));
    }

    public static boolean ehPalindromo(String palavra) {
        String semEspacos = palavra.replace(" ", "").toUpperCase();
        return new StringBuilder(semEspacos).reverse().toString().equalsIgnoreCase(semEspacos);
    }
}
