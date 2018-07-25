package com.epam.ivko.dao;

import com.epam.ivko.entity.Group;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupDAOTesting {

    @Mock
    private DataSource mockDataSource;
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private Group group;

    public GroupDAOTesting() {
    }

    @Before
    public void setUp() throws Exception {
        assertNotNull(mockDataSource);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd-MM-yyyy");
        Date date = format.parse("16-07-2015");
        group = new Group(1, "aaa", date, "eee!!!");
        group.setStartDate(date);
        java.sql.Date sDate = new java.sql.Date(group.getStartDate().getTime());
        when(mockResultSet.first()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(group.getId());
        when(mockResultSet.getString(2)).thenReturn(group.getName());
        when(mockResultSet.getDate(3)).thenReturn(sDate);
        when(mockResultSet.getString(4)).thenReturn(group.getCurator());
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test(expected = NullPointerException.class)
    public void nullAddThrowsException(){
        new GroupDao().add(null);
    }

    @Test
    public void addGroup() {
        new GroupDao().add(group);
    }

    @Test
    public void createAndUpdateGroup() {
        GroupDao dao = new GroupDao();
        dao.add(group);
        Group g = dao.update(group);
        assertEquals(group.getId(), g.getId());
        assertEquals(group.getName(), g.getName());
        assertEquals(group.getCurator(), g.getCurator());
        assertEquals(group.getStartDate(), g.getStartDate());
    }

    @Test
    public void removeGroupById() {
        GroupDao dao = new GroupDao();
        int id = group.getId();
        dao.remove(group);
        Group g = dao.getById(id);
        assertNull(g);
    }

    @Test
    public void removeAllGroups(){
        GroupDao dao = new GroupDao();
        dao.removeAll();
        List<Group> g = new ArrayList<>();
        dao.getAll();
        assertEquals(g, dao.getAll());
    }
}