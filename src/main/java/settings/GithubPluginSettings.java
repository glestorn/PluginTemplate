package settings;

import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.CredentialAttributesKt;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GithubPluginSettings implements Configurable {

    private final String SERVER_ID = "MY_SERVER";
    private JTextField userInputField;
    private JTextField passwordInputField;
    private String userStartedString;
    private String passwordStartedString;

    private CredentialAttributes createCredentialAttributes(String key) {
        return new CredentialAttributes(CredentialAttributesKt.generateServiceName("MySystem", key));
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return null;
    }

    @Override
    public @Nullable JComponent createComponent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(16, 1));
        JPanel textPanel = new JPanel(new GridLayout(16, 1));
        mainPanel.add(textPanel, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JLabel usernameLabel = new JLabel("Username:", JLabel.LEFT);
        textPanel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password:", JLabel.LEFT);
        textPanel.add(passwordLabel);

        userInputField = new JTextField();

        passwordInputField = new JTextField();

        CredentialAttributes credentialAttributes = createCredentialAttributes(SERVER_ID);
        Credentials credentials = PasswordSafe.getInstance().get(credentialAttributes);
        if (credentials != null) {
            System.out.println("Something found");
            userInputField.setText(credentials.getUserName());
            passwordInputField.setText(credentials.getPasswordAsString());
        }
        userStartedString = userInputField.getText();
        passwordStartedString = passwordInputField.getText();

        passwordInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        inputPanel.add(userInputField);
        inputPanel.add(passwordInputField);

        return mainPanel;
    }

    @Override
    public boolean isModified() {
        if (userStartedString.contentEquals(userInputField.getText()) &&
            passwordStartedString.contentEquals(passwordInputField.getText())) {
            return false;
        }
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        String username = userInputField.getText();
        String password = passwordInputField.getText();
        CredentialAttributes credentialAttributes = createCredentialAttributes(SERVER_ID);
        Credentials credentials = new Credentials(username, password);
        System.out.println("Trying to save credentials");
        PasswordSafe.getInstance().set(credentialAttributes, credentials);
    }
}
