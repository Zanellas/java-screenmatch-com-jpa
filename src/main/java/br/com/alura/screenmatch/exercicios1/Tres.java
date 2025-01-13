package br.com.alura.screenmatch.exercicios1;

public class Tres {
    public static void main(String[] args) {
        System.out.println(obterPrimeiroEUltimoNome(("  João Carlos Silva   ")));
        System.out.println(obterPrimeiroEUltimoNome(("Mmaria    ")));

    }

    public static String obterPrimeiroEUltimoNome(String nomeCompleto){
        String[] nomes = nomeCompleto.trim().split("\\s+");
        if(nomes.length == 1) {
            return nomes[0];
        }

        return nomes [0] + " " + nomes[nomes.length - 1];
    }
}
