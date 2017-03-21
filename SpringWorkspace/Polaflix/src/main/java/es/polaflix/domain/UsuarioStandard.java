package es.polaflix.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("standard")
public class UsuarioStandard extends Usuario{ }
