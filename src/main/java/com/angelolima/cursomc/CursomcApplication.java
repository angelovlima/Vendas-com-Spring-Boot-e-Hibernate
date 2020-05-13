package com.angelolima.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.angelolima.cursomc.domain.Categoria;
import com.angelolima.cursomc.domain.Cidade;
import com.angelolima.cursomc.domain.Cliente;
import com.angelolima.cursomc.domain.Endereco;
import com.angelolima.cursomc.domain.Estado;
import com.angelolima.cursomc.domain.Produto;
import com.angelolima.cursomc.domain.enums.TipoCliente;
import com.angelolima.cursomc.repositories.CategoriaRepository;
import com.angelolima.cursomc.repositories.CidadeRepository;
import com.angelolima.cursomc.repositories.ClienteRepository;
import com.angelolima.cursomc.repositories.EnderecoRepository;
import com.angelolima.cursomc.repositories.EstadoRepository;
import com.angelolima.cursomc.repositories.ProdutoRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 400.00);
		Produto p3 = new Produto(null, "Teclado", 120.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2 , p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Caçapava", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Larissa", "Larissa@gmail.com", "40040040040", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("32234004", "988972272"));
		
		Endereco e1 = new Endereco(null, "Rua dos eucaliptos", "123", "Apto 300", "Floresta Taiada", "12260018", cli1, c3);
		Endereco e2 = new Endereco(null, "Rua lar dos morcegos-esquilo", "100", "Sala 300", "Moçota", "12000012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
