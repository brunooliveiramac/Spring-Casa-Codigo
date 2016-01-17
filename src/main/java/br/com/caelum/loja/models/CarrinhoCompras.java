package br.com.caelum.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component                                        //Spring resolve as dependencias do scope
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens  = new LinkedHashMap<CarrinhoItem, Integer>();//Chave Valor
	
	public void add(CarrinhoItem carrinhoItem) {
			itens.put(carrinhoItem, getQuantidade(carrinhoItem) + 1);
		
	}

	//Verificar se nos itens já contem a chave..Item Repitido
	public Integer getQuantidade(CarrinhoItem carrinhoItem) {
		if(!itens.containsKey(carrinhoItem)){
			//Se não contem a chave, n tem nenhum item desse tipo, tem 0
			itens.put(carrinhoItem, 0);
		}
			//Se chave existe
		return itens.get(carrinhoItem);
	}
	 
	
	public int getQuantidade(){
		return itens.values().stream().reduce(0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();//Apenas a Chave
	}

	
	public BigDecimal getTotal(CarrinhoItem   item){
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = BigDecimal.ZERO;
		
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
			
		}
		return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
			Produto produto = new Produto();
			produto.setId(produtoId);
			itens.remove(new CarrinhoItem(produto, tipoPreco));
			
	}
}

