package br.com.alura.screenmatch.exercicios1;

public class Oito {
}

enum CodigoErro {
    NOT_FOUND(404, "Recurso não encontrado"),
    BAD_REQUEST(400, "Requisição inválida"),
    INTERNAL_SERVER_ERROR(500, "Erro interno do servidor");

    private final int codigo;
    private final String descricao;

    CodigoErro(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}