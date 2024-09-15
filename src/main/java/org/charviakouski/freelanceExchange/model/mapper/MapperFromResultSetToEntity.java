package org.charviakouski.freelanceExchange.model.mapper;

import java.sql.ResultSet;
import java.util.Optional;

public interface MapperFromResultSetToEntity<E> {
    Optional<E> map(ResultSet resultSet);
}
