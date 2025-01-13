package br.com.alura.screenmatch.exercicios1;

public class Sete {
    public static void main(String[] args) {

    }


}

enum Moeda {
    DOLAR(5.10), EURO(5.50), REAL(1.0);
    private final double taxa;

    Moeda(double taxa) {
        this.taxa = taxa;
    }

    public double converterPara(double valorEmReais) {
        return valorEmReais / taxa;
    }
}
