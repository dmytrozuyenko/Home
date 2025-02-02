package com.softserveinc.ita.homeproject.homedata.poll.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AnswerVariantRepository is the interface that is needed
 * for interaction with AnswerVariants in the database.
 *
 * @author Ihor Samoshost
 */
@Repository
public interface AnswerVariantRepository extends JpaRepository<AnswerVariant, Long> {
}
