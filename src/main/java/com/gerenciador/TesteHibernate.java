package com.gerenciador;

import com.gerenciador.util.HibernateUtil;

public class TesteHibernate {

    public static void main(String[] args) {

        String senha = System.getenv("DB_PASSWORD");

        System.setProperty("DB_PASSWORD", senha);

        System.out.println("DB_PASSWORD configurada!");

        HibernateUtil.getSessionFactory();

        System.out.println("Hibernate conectado com sucesso!");

        HibernateUtil.getSessionFactory().close();
    }
}