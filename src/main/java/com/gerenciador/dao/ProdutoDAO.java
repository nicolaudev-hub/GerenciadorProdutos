package com.gerenciador.dao;

import com.gerenciador.modelo.Produto;
import com.gerenciador.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProdutoDAO {

    public boolean salvar(Produto produto) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.persist(produto);

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;

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

    public boolean atualizar(Produto produto) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.merge(produto);

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    public boolean remover(Long id) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Produto produto = session.get(Produto.class, id);

            if (produto == null) {
                transaction.rollback();
                return false;
            }

            session.remove(produto);
            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
            return false;

        } finally {

            if (session != null) {
                session.close();
            }
        }
    }
}