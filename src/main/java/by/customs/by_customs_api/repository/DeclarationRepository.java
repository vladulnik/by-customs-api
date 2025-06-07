package by.customs.by_customs_api.repository;

import by.customs.by_customs_api.entity.DeclarationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationRepository extends JpaRepository<DeclarationEntity, Long> {
}
