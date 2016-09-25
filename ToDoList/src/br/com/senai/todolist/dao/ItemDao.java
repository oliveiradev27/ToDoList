package br.com.senai.todolist.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.senai.todolist.modelo.ItemLista;
import br.com.senai.todolist.modelo.Lista;

@Repository
public class ItemDao {
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void marcarFeito(Long idItem, boolean valor)
	{
		ItemLista item = manager.find(ItemLista.class, idItem);
		item.setFeito(valor);
		manager.merge(item);
	}
	
	@Transactional
	public void inserir(Long idLista, ItemLista item)
	{
		item.setLista(manager.find(Lista.class, idLista));
		manager.persist(item);
	}
	
	public ItemLista listar(Long idItem)
	{
		return manager.find(ItemLista.class, idItem);
	}
}
