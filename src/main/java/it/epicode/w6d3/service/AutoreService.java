package it.epicode.w6d3.service;

import it.epicode.w6d3.exception.NotFoundException;
import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    public Page<Autore> cercaTuttiAutori(Pageable pageable){

        return autoreRepository.findAll(pageable);
    }

    public Autore cercaAutorePerId(int id) throws NotFoundException {
        return autoreRepository.findById(id).orElseThrow(()->new NotFoundException("Autore con id= " + id + " non trovato"));
    }

    public Autore salvaAutore(Autore autore){

        autore.setAvatar("https://ui-avatars.com/api/?name=" + autore.getNome() + "+" + autore.getCognome());

        return autoreRepository.save(autore);
    }

    public Autore aggiornaAutore(int id, Autore autore) throws NotFoundException{
        Autore a = cercaAutorePerId(id);

        a.setNome(autore.getNome());
        a.setCognome(autore.getCognome());
        a.setEmail(autore.getEmail());
        a.setDataNascita(autore.getDataNascita());
        a.setAvatar(autore.getAvatar());

        return autoreRepository.save(a);
    }

    public void eliminaAutore(int id) throws NotFoundException{
        Autore a = cercaAutorePerId(id);
        autoreRepository.delete(a);
    }



}
