package com.softserveinc.ita.homeproject.homedata.repository;

import com.softserveinc.ita.homeproject.homedata.entity.User;
import com.softserveinc.ita.homeproject.homedata.entity.Vote;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * VoteRepository is the interface that is needed
 * for interaction with Votes in the database.
 *
 * @author Ihor Samoshost
 */
public interface VoteRepository extends PagingAndSortingRepository<Vote, Long> {
    Vote findByPollIdAndUser(Long pollId, User user);
}
