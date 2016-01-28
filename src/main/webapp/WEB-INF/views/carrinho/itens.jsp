<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" 
    prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Seu carrinho de compras">
<main>
		<section class="infoSection container">
	<h2 class="infoSection-titulo">Seu carrinho</h2>
	
	<table class="formularioDoCarrinho-tabela">
		<thead class="formularioDoCarrinho-cabecalho">
			<tr>
				<th></th>
				<th class="formularioDoCarrinho-cabecalho-item">Item</th>
				<th class="formularioDoCarrinho-cabecalho-item formularioDoCarrinho-cabecalho-preco">Preço</th>
				<th class="formularioDoCarrinho-cabecalho-item">Qtd</th>
				<th class="formularioDoCarrinho-cabecalho-item">Total</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			
		  <c:forEach items="${carrinhoCompras.itens }" var="item">
			<tr>
				<td class="formularioDoCarrinho-item">
					<a href="/products/livro-javaee">
						<img class="formularioDoCarrinho-item-imagem" src="//cdn.shopify.com/s/files/1/0155/7645/products/G8Odq4Q1TChRM5NMkpWOfJLrIdpMhXpIMt0Qs0vOAJQ_size_mode_2_size_1024x768_small.jpeg?v=1447444684" />
					</a>
				</td>
				<td class="formularioDoCarrinho-item">
					<h2 class="formularioDoCarrinho-item-titulo">${item.produto.titulo }</h2>
				</td>
				<td class="formularioDoCarrinho-item formularioDoCarrinho-item-preco">${item.preco }</td>
				<td class="formularioDoCarrinho-item">
					<input class="formularioDoCarrinho-item-quantidade"
						   type="number"
						   min="0"
						   id="quantidade"
						   name="quantidade"
						   value="${carrinhoCompras.getQuantidade(item) }">
				</td>
				<td class="formularioDoCarrinho-item formularioDoCarrinho-item-precoTotal" title="PreÃ§o unitÃ¡rio: R$39,90">${carrinhoCompras.getTotal(item) }</td>
     			<form:form action="${s:mvcUrl('CCC#remover').arg(0, item.produto.id).arg(1, item.tipoPreco).build() }" method="post">
				<td class="formularioDoCarrinho-item">
					<input type="image" class="formularioDoCarrinho-item-remover-imagem"  src="http:////cdn.shopify.com/s/files/1/0155/7645/t/189/assets/trash.png?1215741171247150012" alt="X" title="Remover">
				</td>
				</form:form>
			</tr>
		</c:forEach>
			
		</tbody>
		<tfoot class="formularioDoCarrinho-rodape">
			<tr>
				<td class="formularioDoCarrinho-rodape-item formularioDoCarrinho-finalizar" colspan="3">
				 <form:form action="${s:mvcUrl('PC#finalizar').build() }" method="post">
					<input class="formularioDoCarrinho-finalizar-botao" type="submit" name="checkout">Finalizar<span class="formularioDoCarrinho-finalizar-botao-texto" role="presentation"> compra</span></button>
				</form:form>
				</td>
				<td class="formularioDoCarrinho-rodape-item">
					<button class="formularioDoCarrinho-atualizar" type="submit" class="update-cart" name="update">Atualizar</button>
				</td>
				<td class="formularioDoCarrinho-rodape-item">
					${carrinhoCompras.total }
				</td>
				<td></td>
			</tr>
		</tfoot>
	</table>

<!--
<form action="/cart" method="post" id="form-cart">
<table id="cart-table">
<colgroup>
<col class="item-col">
<col class="item-price-col">
<col class="item-quantity-col">
<col class="line-price-col">
<col class="delete-col">
</colgroup>
<thead>
<tr>
<th class="cart-img-col"></th>
<th width="65%"></th>
<th width="10%"></th>
<th width="10%"></th>
<th width="10%"></th>
<th width="5%"></th>
</tr>
</thead>
<tbody>

<tr>
<td class="cart-img-col"><img src="//cdn.shopify.com/s/files/1/0155/7645/products/G8Odq4Q1TChRM5NMkpWOfJLrIdpMhXpIMt0Qs0vOAJQ_size_mode_2_size_1024x768_small.jpeg?v=1447444684" /></td><td class="item-title">Java EE: Aproveite toda a plataforma para construir aplicaÃ§Ãµes - E-book (Alberto Souza)</td>
<td class="numeric-cell">R$ 39,90</td>
<td class="quantity-input-cell"><input type="number" min="0" id="updates_10263481793" name="updates[10263481793]" value="1"></td>
<td class="numeric-cell">R$ 39,90</td>
<td class="remove-item"><a href="/cart/change?id=10263481793&quantity=0"><img src="//cdn.shopify.com/s/files/1/0155/7645/t/189/assets/excluir.png?1215741171247150012" alt="" title="" /></a></td>
</tr>

</tbody>
<tfoot>
<tr>
<td colspan="3"><input type="submit" class="checkout" name="checkout" value="" id="checkout"/></td>
<td class="quantity-input-cell"><input type="submit" class="update-cart" name="update" value=""></td>
<td class="numeric-cell">R$ 39,90</td><td></td>
</tr>
</tfoot>
</table>

</form>-->
	
</section>



		<div class="buscaDoRodape container" role="presentation">
	<form role="search"
		  aria-labelledby="rotuloBuscaDoRodape"
		  action="/search"
		  method="GET"
		  class="buscaDoRodape-formulario"
	>
		<label id="rotuloBuscaRodape" class="buscaDoRodape-rotuloEscondido" for="campoBuscaRodape">Busque por autor, tÃ­tulo, conteÃºdo...</label>
		<label class="buscaDoRodape-rotulo" for="campoBuscaRodape">NÃ£o encontrou o seu livro?</label>
		<fieldset class="buscaDoRodape-fieldset">
			<input type="hidden" name="type" value="product">
			<input id="campoBuscaRodape"
				   class="buscaDoRodape-campo"
				   placeholder="O que você procura?"
				   type="search"
				   name="q"
				   required
				   aria-required
			>
			<button class="buscaDoRodape-enviar" type="submit">
				Buscar
			</button>
		</fieldset>
	</form>
</div>
</main>
</tags:pageTemplate>
