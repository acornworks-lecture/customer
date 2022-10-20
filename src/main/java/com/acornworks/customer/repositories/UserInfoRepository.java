package com.acornworks.customer.repositories;

import com.acornworks.customer.domains.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
    UserInfo findByUserName(String userName);
}
