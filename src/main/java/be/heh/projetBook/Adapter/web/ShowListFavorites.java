package be.heh.projetBook.Adapter.web;

import be.heh.projetBook.application.domain.model.BookInfo;
import be.heh.projetBook.application.port.out.AddingTheBookToFavoritesList;
import be.heh.projetBook.application.domain.model.Favorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class ShowListFavorites {

    private final Favorites favList;
    private final AddingTheBookToFavoritesList addToFavoritesList;

    @Autowired
    public ShowListFavorites(Favorites favList, AddingTheBookToFavoritesList addToFavoritesList) {
        this.favList = favList;
        this.addToFavoritesList = addToFavoritesList;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBookToFavorites(@RequestBody BookInfo book) {
        try {
            addToFavoritesList.addFavoriteBook(book);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Le livre a été ajouté à la liste de favoris avec succès.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'ajout du livre à la liste de favoris : " + e.getMessage());
        }
    }
}