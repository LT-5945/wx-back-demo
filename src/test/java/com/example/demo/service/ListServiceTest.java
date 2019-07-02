package com.example.demo.service;

import com.example.demo.entity.Group;
import com.example.demo.http.GroupResponse;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListServiceTest extends TestCase {
    @Autowired
    private ListService target;

    @Test
    @Transactional
    @Rollback
    public void testGetGroupsForUser() {
        List<GroupResponse> groups = target.getGroupsForUser(1);
        System.out.println(groups);
    }

    @Test
    public void testConvertGroup() {
        Group group = new Group();
        group.setGroup_id(1);
        group.setGroup_name("A");
        group.setGroup_desc("B");
        group.setGroup_type(0);
        assertTrue(target.convertGroup(group, true).isIs_admin());
        assertFalse(target.convertGroup(group, false).isIs_admin());
    }
}
