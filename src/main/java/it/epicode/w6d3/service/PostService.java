package it.epicode.w6d3.service;

import it.epicode.w6d3.exception.NotFoundException;
import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.model.Post;
import it.epicode.w6d3.repository.PostRepository;
import it.epicode.w6d3.request.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    AutoreService autoreService;

    public Page<Post> cercaTuttiPost(Pageable pageable){

        return postRepository.findAll(pageable);
    }

    public Post cercaPostPerId(int id) throws NotFoundException {
        return postRepository.findById(id).orElseThrow(()->new NotFoundException("Post con id= " + id + " non trovato"));
    }

    public Post salvaPost(PostRequest postRequest) throws NotFoundException{

        Autore a = autoreService.cercaAutorePerId(postRequest.getAutoreId());
        Post p = new Post();
        p.setAutore(a);
        p.setTitolo(postRequest.getTitolo());
        p.setCategoria(postRequest.getCategoria());
        p.setContenuto(postRequest.getContenuto());

        return postRepository.save(p);
    }

    public Post aggiornaPost(int id, PostRequest postRequest) throws NotFoundException{
        Post p = cercaPostPerId(id);

        Autore a =  autoreService.cercaAutorePerId(postRequest.getAutoreId());
        p.setAutore(a);
        p.setTitolo(postRequest.getTitolo());
        p.setCategoria(postRequest.getCategoria());
        p.setContenuto(postRequest.getContenuto());

        return postRepository.save(p);
    }

    public void eliminaPost(int id) throws NotFoundException{
        Post p = cercaPostPerId(id);
        postRepository.delete(p);
    }
}
