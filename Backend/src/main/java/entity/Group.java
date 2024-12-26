package entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Long id;
    private final String groupName;
    private final List<Student> groupMembers;

    public Group(Long id, String groupName, List<Student> groupMembers) {
        this.id = id;
        this.groupName = groupName;
        this.groupMembers = groupMembers;
    }

    public Group(Long id, String groupName) {
        this(id, groupName, new ArrayList<>());
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getGroupMembers() {
        return groupMembers;
    }

    @Override
    public String toString() {
        StringBuilder members = new StringBuilder();
        for (Student student : groupMembers) {
            members.append("\t").append(student.toString()).append("\n");
        }

        return "Group {\n" +
                "\tgroupName: '" + groupName + "',\n" +
                "\tgroupMembers: [\n" + members + "\t]\n" +
                "}";
    }
}
