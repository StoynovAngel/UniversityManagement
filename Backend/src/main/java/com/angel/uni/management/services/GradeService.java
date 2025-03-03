package com.angel.uni.management.services;

import com.angel.uni.management.dto.simple.SimpleGradeDTO;
import com.angel.uni.management.dto.update.UpdateGradeDTO;
import com.angel.uni.management.entity.Grade;
import com.angel.uni.management.interfaces.MultipleObjectService;
import com.angel.uni.management.interfaces.QueryManager;
import com.angel.uni.management.interfaces.Service;
import com.angel.uni.management.utils.mappers.Mappers;

import java.util.List;
import java.util.Optional;

public class GradeService implements Service<Grade, UpdateGradeDTO, SimpleGradeDTO>, MultipleObjectService<Grade> {
    private final QueryManager queryManager;

    public GradeService(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

      @Override
    public void create(SimpleGradeDTO dto) {
        queryManager.insertQuery().insertGrade(dto.name(), dto.mark(), dto.teacherName(), dto.studentUsername(), dto.gradeType());
    }

    @Override
    public Optional<Grade> read(Long id) {
        return queryManager.selectQuery().getGradeById(id, Mappers.getGradeMapper());
    }

    @Override
    public Optional<Grade> read(String name) {
        return queryManager.selectQuery().getGradeByName(name, Mappers.getGradeMapper());
    }

    @Override
    public void update(UpdateGradeDTO dto) {
        queryManager.updateQuery().updateGradeMark(dto.mark(), dto.id());
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public Optional<List<Grade>> readMultiple(String name) {
        return queryManager.selectQuery().getGradesByStudentName(name, Mappers.getGradeMapper());
    }
}
