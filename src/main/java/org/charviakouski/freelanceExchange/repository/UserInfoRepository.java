package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;

public interface UserInfoRepository extends DefaultRepository<Long, UserInfo> {

    UserInfo getUserInfoByName(String username);
}
