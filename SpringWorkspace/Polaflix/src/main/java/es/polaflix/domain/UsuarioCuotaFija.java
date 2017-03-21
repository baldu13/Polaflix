package es.polaflix.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CuotaFija")
public class UsuarioCuotaFija extends Usuario{
	@Transient
	public static final double CUOTA_MENSUAL = 20.0;
}
