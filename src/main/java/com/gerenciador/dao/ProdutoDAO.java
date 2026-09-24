package com.gerenciador.dao;

import com.gerenciador.modelo.Produto;
import com.gerenciador.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(produto);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    public List<Produto> listar() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session
                    .createQuery("from Produto", Produto.class)
                    .getResultList();

        } catch (Exception e) {

            e.printStackTrace();
            return List.of();
        }
    }

    public void atualizar(Produto produto) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.merge(produto);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    public void remover(Long id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Produto produto = session.get(Produto.class, id);

            if (produto != null) {
                session.remove(produto);
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
}