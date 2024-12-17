package com.yash.ems.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

          private static SessionFactory factory;

          public static SessionFactory getSessionFactory() {

                  if (factory == null) {
                      try {
                          StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                          Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
                          factory = meta.getSessionFactoryBuilder().build();
                  }catch (Throwable e)
                      {
                          System.err.println(("Failed To Create Session Factory-: "+e));
                          e.printStackTrace();
                      }
              }
                  return factory;
          }

}
