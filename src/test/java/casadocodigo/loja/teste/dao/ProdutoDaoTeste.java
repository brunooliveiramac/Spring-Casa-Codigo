package casadocodigo.loja.teste.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.caelum.loja.conf.JPAConfiguration;
import br.com.caelum.loja.dao.ProdutoDAO;
import br.com.caelum.loja.models.Produto;
import br.com.caelum.loja.models.TipoPreco;
import casadocodigo.loja.teste.builders.ProdutoBuilder;
import casadocodigo.loja.teste.conf.DataSourceConfigurationTest;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDaoTeste {
	
	@Autowired
	private ProdutoDAO produtoDao;

	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	public void deveSomarTodosPrecosPorTipoDeLivro() {
		
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
				.mais(3).buildAll();
		
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
				.mais(3).buildAll();
		
		livrosImpressos.stream().forEach(produtoDao::gravar);
		livrosEbook.stream().forEach(produtoDao::gravar);
		
		BigDecimal valor = produtoDao.somaPrecosPorTipoPreco(TipoPreco.EBOOK);
		Assert.assertEquals(new BigDecimal(40).setScale(2), valor);

	}
}
