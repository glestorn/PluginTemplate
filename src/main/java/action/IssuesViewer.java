package action;

import GithubDialog.GithubWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;

public class IssuesViewer extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        DialogWrapper githubWindow = new GithubWindow(ProjectManager.getInstance().getDefaultProject());
        if (githubWindow.getExitCode() != DialogWrapper.OK_EXIT_CODE) {
            githubWindow.getExitCode();
        }
    }
}
