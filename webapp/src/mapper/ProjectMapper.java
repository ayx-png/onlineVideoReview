package mapper;

import model.Project;

public interface ProjectMapper {
    public Project queryProjectByProjectName(String projectName);

    public int insertProject(Project project);
}
