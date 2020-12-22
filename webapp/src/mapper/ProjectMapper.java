package mapper;

import model.Project;

public interface ProjectMapper {
    public Project queryProjectByProjectName(String projectName);

    public Project queryProjectByProjectID(int projectID);

    public Project queryProjectByProjectHost(String username);

    public int insertProject(Project project);
}
