package com.george.plugins.jira;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.settings.Server;
import org.apache.maven.settings.Settings;

import com.atlassian.jira.rest.JiraRestService;
import com.atlassian.jira.rest.JiraService;

/**
 * This class allows the use of {@link JiraSoapService} in JIRA Actions
 * 
 * @author george
 * 
 */
public abstract class AbstractJiraMojo extends AbstractMojo {

    /**
     * This is the JIRA REST Suffix for accessing the webservice
     */
    protected static final String JIRA_REST_SUFFIX = "/rest/api/2";

	/**
	 * @parameter expression="${settings}"
	 */
	Settings settings;

	/**
	 * Server's id in settings.xml to look up username and password.
	 * 
	 * @parameter expression="${settingsKey}"
	 */
	private String settingsKey;

	/**
	 * JIRA Installation URL. If not informed, it will use the
	 * project.issueManagement.url info.
	 * 
	 * @parameter expression="${jiraURL}"
	 *            default-value="${project.issueManagement.url}"
	 * @required
	 */
	protected String jiraURL;

	/**
	 * JIRA Authentication User.
	 * 
	 * @parameter expression="${jiraUser}" default-value="${scmUsername}"
	 */
	protected String jiraUser;

	/**
	 * JIRA Authentication Password.
	 * 
	 * @parameter expression="${jiraPassword}" default-value="${scmPassword}"
	 */
	protected String jiraPassword;

	/**
	 * JIRA Project Key.
	 * 
	 * @parameter expression="${jiraProjectKey}"
	 */
	protected String jiraProjectKey;

    transient JiraService jiraService;

	/**
	 * Returns if this plugin is enabled for this context
	 * 
	 * @parameter expression="${skip}"
	 */
	protected boolean skip;

    /**
     * Returns the JiraRestService
     * @param jiraPassword2 
     * @param jiraUser2 
     * 
     * @return
     * @throws MalformedURLException
     */
    protected JiraService getJiraService() throws MalformedURLException{
        if (jiraService == null) {
            
            String url = discoverJiraRestURL();
            if (url == null)
                throw new MalformedURLException(
                        "JIRA URL cound not be found. Check your pom.xml configuration.");
            URL u = new URL(url);
            jiraService = new JiraRestService(u, jiraUser, jiraPassword);
        }
        return jiraService;
    }

    /**
     * Returns the formatted JIRA RestService URL
     * 
     * @return JIRA Web Service URL
     */
	protected String discoverJiraRestURL() {
        String url;
        if (jiraURL == null) {
            return null;
        }
        if (jiraURL.endsWith(JIRA_REST_SUFFIX)) {
            url = jiraURL;
        } else {
            int projectIdx = jiraURL.indexOf("/browse");
            if (projectIdx > -1) {
                int lastPath = jiraURL.indexOf("/", projectIdx + 8);
                if (lastPath == -1) {
                    lastPath = jiraURL.length();
                }
                jiraProjectKey = jiraURL.substring(projectIdx + 8, lastPath);
                url = jiraURL.substring(0, projectIdx) + JIRA_REST_SUFFIX;
            } else {
                url = jiraURL + JIRA_REST_SUFFIX;
            }
        }
        return url;
    }

	/**
	 * Load username password from settings if user has not set them in JVM
	 * properties
	 */
	void loadUserInfoFromSettings() {
		if (settingsKey == null) {
			settingsKey = jiraURL;
		}
		if ((jiraUser == null || jiraPassword == null) && (settings != null)) {
			Server server = settings.getServer(this.settingsKey);

			if (server != null) {
				if (jiraUser == null) {
					jiraUser = server.getUsername();
				}

				if (jiraPassword == null) {
					jiraPassword = server.getPassword();
				}
			}
		}
	}

	@Override
	public final void execute() throws MojoExecutionException,
			MojoFailureException {
		Log log = getLog();
		if (isSkip()) {
			log.info("Skipping Plugin execution.");
			return;
		}
		try {
//            JiraSoapService jiraService = getJiraSoapService();
			loadUserInfoFromSettings();
			JiraService jiraRestService = getJiraService();
//			log.debug("Logging in JIRA");
//			String loginToken = jiraService.login(jiraUser, jiraPassword);
//			log.debug("Logged in JIRA");
			try {
				doExecute(jiraRestService);
			} finally {
//				log.debug("Logging out from JIRA");
//				jiraService.logout(loginToken);
//				log.debug("Logged out from JIRA");
			}
		} catch (Exception e) {
			log.error("Error when executing mojo", e);
			// XXX: Por enquanto nao faz nada.
		}
	}

    public abstract void doExecute(JiraService jiraService) throws Exception;

//    public abstract void doExecute(JiraSoapService jiraService,
//            String loginToken) throws Exception;

	public boolean isSkip() {
		return skip;
	}

	public void setJiraProjectKey(String jiraProjectKey) {
		this.jiraProjectKey = jiraProjectKey;
	}

	public void setJiraPassword(String jiraPassword) {
		this.jiraPassword = jiraPassword;
	}

	public void setJiraURL(String jiraURL) {
		this.jiraURL = jiraURL;
	}

	public void setJiraUser(String jiraUser) {
		this.jiraUser = jiraUser;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public void setSettingsKey(String settingsKey) {
		this.settingsKey = settingsKey;
	}

}