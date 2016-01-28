package br.com.caelum.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.loja.models.Produto;import br.com.caelum.loja.models.TipoPreco;

@Repository	
@Transactional //Import do spring
public class ProdutoDAO {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void gravar(Produto  produto){
		entityManager.persist(produto);
	}

	public List<Produto> listar() {
		return entityManager.createQuery("select p from Produto p", Produto.class)
				.getResultList();
	}
	
	public Produto find(Integer id) {
        return entityManager.createQuery("select distinct(p) from Produto p join fetch p.precos precos where p.id = :id", Produto.class).setParameter("id", id).getSingleResult();
    }
	
	public BigDecimal somaPrecosPorTipoPreco(TipoPreco preco){
		TypedQuery<BigDecimal> query = entityManager.createQuery("select sum(preco.valor) from Produto p "
				+ " join p.precos preco where preco.tipoPreco =: tipoPreco", BigDecimal.class);
		query.setParameter("tipoPreco", preco);
		return query.getSingleResult();
		
	}

}
