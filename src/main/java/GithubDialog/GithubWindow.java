package GithubDialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GithubWindow extends DialogWrapper {

    private final String WINDOW_NAME = "Github issues";

    public GithubWindow(@Nullable Project project) {
        super(project);
        init();
        setTitle(WINDOW_NAME);
        show();
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {
        return null;
    }
}
