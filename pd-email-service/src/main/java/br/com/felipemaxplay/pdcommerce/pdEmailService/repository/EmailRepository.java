package br.com.felipemaxplay.pdcommerce.pdEmailService.repository;

import br.com.felipemaxplay.pdcommerce.pdEmailService.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
}
