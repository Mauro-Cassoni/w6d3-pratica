package it.epicode.w6d3.request;

import it.epicode.w6d3.model.Autore;
import it.epicode.w6d3.model.Categoria;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class PostRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Categoria categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private int autoreId;

}
