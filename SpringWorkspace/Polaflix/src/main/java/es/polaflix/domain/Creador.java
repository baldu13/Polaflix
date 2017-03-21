package es.polaflix.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("creador")
public class Creador extends Artista {}
