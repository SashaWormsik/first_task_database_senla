package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.entity.Response_;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.entity.UserInfo_;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ResponseRepositoryImpl extends AbstractRepository<Long, Response> implements ResponseRepository {

    @Override
    protected Class<Response> getEntityClass() {
        return Response.class;
    }

    @Override
    public List<Response> getAllResponsesByExecutor(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Response> criteriaQuery = criteriaBuilder.createQuery(Response.class);
        Root<Response> root = criteriaQuery.from(Response.class);
        root.fetch(Response_.executor, JoinType.LEFT);
        root.fetch(Response_.task, JoinType.LEFT);
        root.fetch(Response_.responseStatus, JoinType.LEFT);
        Join<Response, UserInfo> userInfoJoin = root.join(Response_.executor, JoinType.LEFT);
        criteriaQuery.select(root).where(criteriaBuilder.equal(userInfoJoin.get(UserInfo_.id), id));
        return entityManager.createQuery(criteriaQuery).getResultList();

    }
}
