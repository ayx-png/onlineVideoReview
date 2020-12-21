package mapper;

import model.Project;

public interface ProjectMapper {
    public Project queryProjectByProjectName(String projectName);

    public Project queryProjectByProjectID(int projectID);

    public int insertProject(Project project);
}
