package utils.mappers;

import dto.GroupDTO;
import entity.Group;
import interfaces.CustomRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper extends Mappers implements CustomRowMapper<GroupDTO, Group> {

    @Override
    public Group mapToEntity(GroupDTO groupDTO) {
        return entityForm(groupDTO);
    }

    @Override
    public GroupDTO mapToDTO(Group group) {
        return dtoForm(group);
    }

    @Override
    public Group mapRow(ResultSet resultSet) throws SQLException {
        return mapForm(resultSet);
    }

    private Group entityForm(GroupDTO groupDTO) {
        Group group = new Group();
        group.setGroupName(groupDTO.groupName());
        return group;
    }

    private GroupDTO dtoForm(Group group) {
        return new GroupDTO(
                group.getGroupName()
        );
    }

    private Group mapForm(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getLong("id"));
        group.setGroupName(resultSet.getString("name"));
        return group;
    }
}
