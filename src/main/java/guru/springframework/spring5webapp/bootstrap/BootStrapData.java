package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{

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
        
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        Publisher p = new Publisher("Publishing City", "Work street", "Washington", "NY", "1211212");
        
        publisherRepository.save(p);


        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(p);
        p.getBooks().add(ddd);
        
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(p);

        Author rod = new Author("Rod", "Johnson");
        Book j2EE = new Book("J2EE Develpoment without EJB", "3459343489");
        rod.getBooks().add(j2EE);
        j2EE.getAuthors().add(rod);
        j2EE.setPublisher(p);
        p.getBooks().add(j2EE);
        
        authorRepository.save(rod);
        bookRepository.save(j2EE);
        
        publisherRepository.save(p);
        
        System.out.println("Started in bootstrap...");
        System.out.println("Number of books:" + bookRepository.count());

        System.out.println(p.getBooks().size());
    }
    
}
