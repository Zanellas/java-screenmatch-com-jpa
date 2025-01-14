package br.com.alura.screenmatch.execicios2;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public class Exercicio {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void Principal() {
        Categoria categoriaEletronicos = new Categoria(1L, "Eletrônicos");
        Categoria categoriaLivros = new Categoria(2L, "Livros");
        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos);
        Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos);
        Produto produto3 = new Produto("Livro de JAVA", 100.0, categoriaLivros);
        Produto produto4 = new Produto("Livro de Spring boot", 150.0, categoriaLivros);
        produto1.setFornecedor(fornecedorTech);
        produto2.setFornecedor(fornecedorTech);
        produto3.setFornecedor(fornecedorLivros);
        produto4.setFornecedor(fornecedorLivros);
        produtoRepository.saveAll(List.of(produto1, produto2, produto3, produto4));

        Pedido pedido1 = new Pedido(1L, LocalDate.now());
        Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1));
        pedido1.setProdutos(List.of(produto1, produto3));
        pedido2.setProdutos(List.of(produto1, produto2, produto4));
        pedidoRepository.saveAll(List.of(pedido1, pedido2));

        System.out.println("Produtos na categoria Eletrônicos: ");
        categoriaRepository.findById(1L).ifPresent(categoria -> {
            categoria.getProdutos().forEach(produto -> {
                System.out.println(" - " + produto.getNome());
            });
        });

        System.out.println("\nPedidos e seus Produtos: ");
        pedidoRepository.findAll().forEach(pedido -> {
            System.out.println("Pedido " + pedido.getId() + ":");
            pedido.getProdutos().forEach(produto -> {
                System.out.println(" - " + produto.getNome());
            });
        });

        System.out.println("\nProdutos e seus fornecedores: ");
        produtoRepository.findAll().forEach(produto -> {
            System.out.println("Produto: " + produto.getNome() + ", Fornecedor: " + produto.getFornecedor().getNome());
        });


//        System.out.println("Categoria e seus produtos: ");
//        categoriaRepository.findAll().forEach(categoria -> {
//            System.out.println("Categoria: " + categoria.getNome());
//            categoria.getProdutos().forEach(produto -> {
//                System.out.println(" - Produto: " + produto.getNome());
//            });
//        });
    }

//    Produto produto = new Produto("Notebook", 3500.0);
//    Categoria categoria = new Categoria(1L, "Eletronico");
//    Pedido pedido = new Pedido(1L, LocalDate.now());
//
//    produtoRepository.save(produto);
//    categoriaRepository.save(categoria);
//    pedidoRepository.save(pedido);
}

@Entity
class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(name = "valor")
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(String nome, Double preco, Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Produto(String nome, Double preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}

@Entity
class Categoria {
    @Id
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public Categoria() {
    }

    public Categoria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}

@Entity
class Pedido {
    @Id
    private Long id;
    private LocalDate data;
    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;

    public Pedido() {
    }

    public Pedido(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}

@Entity
class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Fornecedor() {

    }

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNome(String nome);
    List<Produto> findByCategoriaNome(String categoriaNome);
    List<Produto> findByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThan(Double preco);
    List<Produto> findByNomeContaining(String termo);
    List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);
    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);
    long countByCategoriaNome(String categoriaNome);
    long countByPrecoGreaterThan(Double preco);
    List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String termo);
    List<Produto> findTop3ByPrecoDesc();
    List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);
}

interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}

interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDataEntregaIsNull();
    List<Pedido> findByDataEntregaIsNotNull();
    List<Pedido> findByDataPedidoAfter(LocalDate data);
    List<Pedido> findByDataPedidoBefore(LocalDate data);
    List<Pedido> findByDataPedidoBetween(LocalDate dataInicio, LocalDate dataFim);
}

interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}