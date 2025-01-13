package br.com.alura.screenmatch.exercicios1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cinco {
    public static void main(String[] args){
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println(normalizeEmails(emails));
    }

    public static List<String> normalizeEmails(List<String> emailsList) {
        return emailsList.stream().map(s -> s.trim().toLowerCase()).toList();
    }
}
