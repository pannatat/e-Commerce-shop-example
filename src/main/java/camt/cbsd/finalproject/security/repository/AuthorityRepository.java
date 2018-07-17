package camt.cbsd.finalproject.security.repository;

import camt.cbsd.finalproject.entity.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface AuthorityRepository extends JpaRepository <Authority,Long> {
}
