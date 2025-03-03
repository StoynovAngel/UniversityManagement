package com.angel.uni.management.services;
import com.angel.uni.management.dto.simple.SimpleSubjectDTO;
import com.angel.uni.management.dto.update.UpdateSubjectDTO;
import com.angel.uni.management.entity.Subject;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.Optional;

public class SubjectService implements Service<Subject, UpdateSubjectDTO, SimpleSubjectDTO> {
    private final QueryManager queryManager;

    public SubjectService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    public void create(SimpleSubjectDTO dto) {
        queryManager.insertQuery().insertSubject(dto.name(), dto.hours_per_week(), dto.teacherName(), dto.description());
    }

    @Override
    public Optional<Subject> read(Long id) {
        return queryManager.selectQuery().getSubjectById(id, Mappers.getSubjectMapper());
    }

    @Override
    public Optional<Subject> read(String name) {
        return queryManager.selectQuery().getSubjectByName(name, Mappers.getSubjectMapper());
    }

    @Override
    public void update(UpdateSubjectDTO dto) {
        queryManager.updateQuery().updateSubjectDescription(dto.description(), dto.name());
    }

    @Override
    public void delete(Long id) {
        queryManager.deleteQuery().deleteSubjectById(id);
    }
}
