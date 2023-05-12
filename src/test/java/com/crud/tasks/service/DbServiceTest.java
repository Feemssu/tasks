package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DbServiceTest {

    @Autowired
    private DbService dbService;
    @Autowired
    private AdminConfig adminConfig;

    @Test
    void testGetAllTasks() {
        adminConfig.getAdminMail();
    }
}
