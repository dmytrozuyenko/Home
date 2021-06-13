package com.softserveinc.ita.homeproject.api.tests.polls;

import com.softserveinc.ita.homeproject.ApiException;
import com.softserveinc.ita.homeproject.api.tests.query.CooperationPollQuery;
import com.softserveinc.ita.homeproject.model.PollStatus;
import com.softserveinc.ita.homeproject.model.PollType;
import com.softserveinc.ita.homeproject.model.ReadPoll;

import java.time.LocalDateTime;
import java.util.List;

public class QueryCooperationPollIT implements IQueryPoll {

    @Override
    public List<ReadPoll> buildQueryPollWithCooperationId(Long cooperationId) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(cooperationId)
                .build().perform();
    }

    @Override
    public List<ReadPoll> buildQueryPollWithSort(String sort) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(CooperationPollApiIT.COOPERATION_ID)
                .sort(sort)
                .build().perform();
    }

    @Override
    public List<ReadPoll> buildQueryPollWithFilter(String filter) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(CooperationPollApiIT.COOPERATION_ID)
                .filter(filter)
                .build().perform();
    }

    @Override
    public List<ReadPoll> buildQueryPollWithPollIdAndCooperationId(Long pollId, Long cooperationId) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(cooperationId)
                .id(pollId)
                .build().perform();
    }

    @Override
    public List<ReadPoll> buildQueryPollWithType(PollType type) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(CooperationPollApiIT.COOPERATION_ID)
                .pageNumber(1)
                .pageSize(10)
                .sort("id,asc")
                .type(type)
                .build().perform();
    }

    @Override
    public List<ReadPoll> buildQueryPollWithStatus(PollStatus status) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(CooperationPollApiIT.COOPERATION_ID)
                .pageNumber(1)
                .pageSize(10)
                .sort("id,asc")
                .status(status)
                .build().perform();
    }

    @Override
    public List<ReadPoll> buildQueryPollWithCompletionDate(LocalDateTime completionDate) throws ApiException {
        return new CooperationPollQuery.Builder(COOPERATION_POLL_API)
                .cooperationId(CooperationPollApiIT.COOPERATION_ID)
                .completionDate(completionDate)
                .build().perform();
    }
}
