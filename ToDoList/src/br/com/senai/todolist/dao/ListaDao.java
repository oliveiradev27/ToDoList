package br.com.senai.todolist.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.senai.todolist.modelo.ItemLista;
import br.com.senai.todolist.modelo.Lista;

@Repository
public class ListaDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void inserir (Lista lista)
	{
		manager.persist(lista);
	}
	
	public List<Lista> listar()
	{
		TypedQuery<Lista> query = manager.createQuery("select l from Lista l", Lista.class);
		return query.getResultList();
	}
	
	public Lista listar(Long idLista)
	{
		Lista lista = manager.find(Lista.class, idLista);
		return lista;
	}
	
	@Transactional
	public void excluir(Long idLista)
	{
		Lista lista = manager.find(Lista.class, idLista);
		manager.remove(lista);
	}
	
	@Transactional
	public void excluirItem(Long idItem)
	{
		ItemLista item = manager.find(ItemLista.class, idItem);
		Lista lista = item.getLista();
		lista.getItens().remove(item);
		manager.merge(lista);
	}

}
