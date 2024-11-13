package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findUserInfoByCredential_Email(String email);

    Page<UserInfo> findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameIn(String userName, List<String> roles, Pageable pageable);

}
