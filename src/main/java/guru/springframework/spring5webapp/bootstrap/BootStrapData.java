package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Author morse = new Author("R", "Morse");
        Book  mbbs = new Book("I Believe In You", "2026");
        Publisher publisher = new Publisher();

        publisher.setName("RKG Publishers");
        publisher.setCity("Prospect City");
        publisher.setState("FL");

        publisherRepository.save(publisher);


        morse.getBooks().add(mbbs);
        mbbs.getAuthors().add(morse);
        mbbs.setPublisher(publisher);
        publisher.getBooks().add(mbbs);

        authorRepository.save(morse);
        bookRepository.save(mbbs);
        publisherRepository.save(publisher);


        Author rod = new Author("Rod", "Johnson");
        Book  noEJB = new Book("J2EE Development without EJB", "173783673");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Number of Publishers = " + publisherRepository.count());
        System.out.println("Number of Books = " + bookRepository.count());
        System.out.println("Publisher Number of Books = " + publisher.getBooks().size());

    }
}
