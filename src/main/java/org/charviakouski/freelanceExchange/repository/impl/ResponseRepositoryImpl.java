package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.criteria.*;
import org.charviakouski.freelanceExchange.model.entity.*;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseRepositoryImpl extends AbstractRepository<Long, Response> implements ResponseRepository {

    @Override
    protected Class<Response> getEntityClass() {
        return Response.class;
    }

    @Override
    public List<Response> getAllResponsesByExecutor(UserInfo userInfo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Response> criteriaQuery = criteriaBuilder.createQuery(Response.class);
        Root<Response> root = criteriaQuery.from(Response.class);
        Fetch<Response, UserInfo> userInfoJoin = root.fetch(Response_.executor, JoinType.LEFT);
        Join<Response, Task> taskJoin = root.join(Response_.task, JoinType.LEFT);
        Join<Response, ResponseStatus> responseStatusJoin = root.join(Response_.responseStatus, JoinType.LEFT);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get(Response_.executor).get(UserInfo_.id), userInfo.getId()));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }
}
