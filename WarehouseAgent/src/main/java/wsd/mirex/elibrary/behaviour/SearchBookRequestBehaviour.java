package wsd.mirex.elibrary.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import org.w3c.dom.Element;
import wsd.mirex.elibrary.dao.BookDao;
import wsd.mirex.elibrary.message.GetSearchBook;
import wsd.mirex.elibrary.model.Book;

public class SearchBookRequestBehaviour extends OneShotBehaviour {
    Element element;
    GetSearchBook getSearchBook;

    public SearchBookRequestBehaviour(Element element) {
        this.element = element;
        try {
            createGetSearchBook();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void createGetSearchBook() throws Exception {
        getSearchBook = new GetSearchBook().deserialize(element);
    }

    private Book findBook()
    {
        BookDao bookDao = new BookDao();
        Book book = bookDao.findByTitle(getSearchBook.getTitle());
        bookDao.closeSession();
        return book;
    }

    @Override
    public void action()
    {
        System.out.println("Found Book Id: " + findBook().getId());
    }
}