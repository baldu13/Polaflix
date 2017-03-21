package es.polaflix.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("actor")
public class Actor extends Artista {}
