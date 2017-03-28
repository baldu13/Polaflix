package es.polaflix.repositories;

import org.springframework.data.repository.Repository;
import es.polaflix.domain.Usuario;

public interface UsersRepository extends Repository<Usuario,Integer>{
	Usuario findByAlias(String alias);
}
