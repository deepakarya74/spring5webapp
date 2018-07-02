package deep.springframework.spring5webapp.bootstrap;

import deep.springframework.spring5webapp.model.Author;
import deep.springframework.spring5webapp.model.Book;
import deep.springframework.spring5webapp.model.Publisher;
import deep.springframework.spring5webapp.repositories.AuthorRepository;
import deep.springframework.spring5webapp.repositories.BookRepository;
import deep.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository,BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    private void initData(){

        //Eric

        Publisher newYorkTimes = new Publisher("New York Times", "103, New York Street");


        publisherRepository.save(newYorkTimes);

        Author eric = new Author("Eric", "Evans");

        Book ddd = new Book("1234","Data Driven Development",newYorkTimes);
        eric.getBooks().add(ddd);

        ddd.getAuthors().add(eric);

        //save operation
        authorRepository.save(eric);

        bookRepository.save(ddd);

        //Rod

        Publisher oregonPublish = new Publisher("oregon Publisher", "103, Oregon Street");


        publisherRepository.save(oregonPublish);

        Author rod = new Author("Rod", "Johnson");

        Book noEjab = new Book("2222","Java 10.0",oregonPublish);
        rod.getBooks().add(noEjab);

        noEjab.getAuthors().add(rod);



        //save operation
        authorRepository.save(rod);
        publisherRepository.save(oregonPublish);
        bookRepository.save(noEjab);

    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
