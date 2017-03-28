package es.polaflix.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Standard")
public class UsuarioStandard extends Usuario{ }
