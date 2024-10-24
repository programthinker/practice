package com.example.elasticsearch.config;

import com.example.elasticsearch.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,Long> {



}
