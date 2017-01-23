package com.wsd.warehouseagent.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import com.wsd.warehouseagent.db.SessionFactoryProvider;
import com.wsd.warehouseagent.model.Book;

import java.util.List;

/**
 * Warstwa DAO dostepu do bazy danych z ksiazkami
 */
public class BookDao
{
    private Session session;

    /** Zainicjowanie polaczenia z baza danych */
    public BookDao()
    {
        session = SessionFactoryProvider.getSessionFactory().openSession();
    }

    private Session getSession()
    {
        return session;
    }

    /** Sprawdza zajetosc bazy danych */
    public Integer getBooksCount()
    {
        Criteria cr = getSession().createCriteria(Book.class);
        return  ((Number)cr.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    /** Wyszukanie ksiazki po ID w bazie danych */
    public Book findById(Integer id)
    {
        Criteria cr = getSession().createCriteria(Book.class);
        cr.add(Restrictions.eq("id", id));
        return (Book) cr.uniqueResult();
    }

    /** Wyszukanie ksiazki po unikalnym ID w obrebie calego systemu */
    public Book findByIdBook(String idBook)
    {
        Criteria cr = getSession().createCriteria(Book.class);
        cr.add(Restrictions.eq("idBook", idBook));
        return (Book) cr.uniqueResult();
    }

    /** Wyszukanie ksiazki po tytule */
    public List<Book> findByTitle(String title)
    {
        Criteria cr = getSession().createCriteria(Book.class);
        cr.add(Restrictions.eq("title", title));
        return cr.list();
    }

    /** Wyszukanie wszystkich ksiazek danego autora */
    public List<Book> findByAuthor(String author)
    {
        Criteria cr = getSession().createCriteria(Book.class);
        cr.add(Restrictions.eq("author", author));
        return cr.list();
    }

    /** Wyszukanie wszystkich ksiazek w bazie danych agenta */
    public List<Book> findAll()
    {
        Criteria cr = getSession().createCriteria(Book.class);
        return cr.list();
    }

    /** Dodanie ksiazki do bazy danych */
    public void addBook(Book book)
    {
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
    }

    /** Zaktualizowanie danych ksiazki w bazie danych */
    public void updateBook(Book book)
    {
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
    }

    /** Usuniecie ksiazki z bazy danych */
    public void deleteBook(Book book)
    {
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
    }

    /** Usuniecie ksiazki z bazy danych wg wskazanego globalnego ID ksiazki */
    public void deleteBookByIdBook(Integer idBook)
    {
        Criteria cr = getSession().createCriteria(Book.class);
        cr.add(Restrictions.eq("idBook", idBook));
        Book book = (Book) cr.uniqueResult();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
    }

    /** Usuniecie wszystkich ksiazek znajdujacych sie w bazie danych agenta */
    public void deleteAll()
    {
        List<Book> books= findAll();

        session.beginTransaction();

        for (Book book : books)
            session.delete(book);

        session.getTransaction().commit();
    }

    /** Zakonczenie polaczenia z baza danych */
    public void closeSession()
    {
        session.close();
    }
}