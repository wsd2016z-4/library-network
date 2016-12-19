package wsd.mirex.elibrary.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import org.w3c.dom.Element;
import wsd.mirex.elibrary.dao.BookDao;
import wsd.mirex.elibrary.message.GetReleaseBook;
import wsd.mirex.elibrary.model.Book;

public class ReleaseBookBehaviour extends OneShotBehaviour
{
    Element element;
    GetReleaseBook getReleaseBook;

    public ReleaseBookBehaviour(Element element)
    {
        this.element = element;
        try
        {
            createGetReleaseBookClass();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    private void createGetReleaseBookClass() throws Exception
    {
        getReleaseBook = new GetReleaseBook().deserialize(element);
    }

    private void releaseBook()
    {
        BookDao bookDao = new BookDao();
        Book book = bookDao.findByIdBook(getReleaseBook.getId());
        book.setBookStatus("available");
        bookDao.updateBook(book);
        bookDao.closeSession();
    }

    @Override
    public void action()
    {
        releaseBook();
        System.out.println(getReleaseBook.getId());
    }
}