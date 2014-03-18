package com.george.plugins.jira;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.util.StringUtils;

import com.atlassian.jira.rest.JiraService;
import com.atlassian.jira.rest.RemoteVersion;

/**
 * Goal that creates a version in a JIRA project . NOTE: SOAP access must be
 * enabled in your JIRA installation. Check JIRA docs for more info.
 * 
 * @goal release-jira-version
 * @phase deploy
 * 
 * @author George Gastaldi
 */
public class ReleaseVersionMojo extends AbstractJiraMojo {

	/**
	 * Released Version
	 * 
	 * @parameter expression="${releaseVersion}"
	 *            default-value="${project.version}"
	 */
	String releaseVersion;

    /**
     * Auto Discover latest release and release it.
     * 
     * @parameter expression="${autoDiscoverLatestRelease}" default-value="true"
     */
    boolean autoDiscoverLatestRelease;

    /**
     * Archive Version after release.
     * 
     * @parameter expression="${archive}" default-value="false"
     */
    boolean archive;

	/**
	 * Comparator for discovering the latest release
	 * 
	 * @parameter 
	 *            implementation="com.george.plugins.jira.RemoteVersionComparator"
	 */
	Comparator<RemoteVersion> remoteVersionComparator = new RemoteVersionComparator();

	@Override
	public void doExecute(JiraService jiraService)
			throws Exception {
		Log log = getLog();
		RemoteVersion[] versions = jiraService.getVersions(jiraProjectKey);
		String thisReleaseVersion = (autoDiscoverLatestRelease)
				? calculateLatestReleaseVersion(versions)
				: StringUtils.capitaliseAllWords(releaseVersion);
		if (thisReleaseVersion != null) {
			log.info("Releasing Version " + this.releaseVersion);
			markVersionAsReleased(jiraService, versions,
			        thisReleaseVersion);
		}
	}

	/**
	 * Returns the latest unreleased version
	 * 
	 * @param versions
	 * @return
	 */
	String calculateLatestReleaseVersion(RemoteVersion[] versions) {
		Arrays.sort(versions, remoteVersionComparator);

		for (RemoteVersion remoteVersion : versions) {
			if (!remoteVersion.isReleased())
				return remoteVersion.getName();
		}
		return null;
	}

	/**
	 * Check if version is already present on array
	 * 
	 * @param versions
	 * @param newDevVersion
	 * @return
	 */
	boolean isVersionAlreadyPresent(RemoteVersion[] versions,
			String newDevVersion) {
		boolean versionExists = false;
		if (versions != null) {
			// Creating new Version (if not already created)
			for (RemoteVersion remoteVersion : versions) {
				if (remoteVersion.getName().equalsIgnoreCase(newDevVersion)) {
					versionExists = true;
					break;
				}
			}
		}
		// existant
		return versionExists;
	}

	/**
	 * Release Version
	 * 
	 * @param log
	 * @param jiraService
	 * @param loginToken
	 * @throws RemoteException
	 * @throws RemotePermissionException
	 * @throws RemoteAuthenticationException
	 * @throws com.atlassian.jira.rpc.soap.client.RemoteException
	 */
	RemoteVersion markVersionAsReleased(JiraService jiraService,
			RemoteVersion[] versions, String releaseVersion)
			 {
		RemoteVersion ret = null;
		if (versions != null) {
			for (RemoteVersion remoteVersion : versions) {
				if (releaseVersion.equalsIgnoreCase(remoteVersion
						.getName()) && !remoteVersion.isReleased()) {
					// Mark as released
	                RemoteVersion releaseRemoteVersion = new RemoteVersion(remoteVersion);
	                releaseRemoteVersion.setArchived(true);
	                releaseRemoteVersion.setReleased(true);
	                releaseRemoteVersion.setReleaseDate(RemoteVersion.generateNormalFormattedDate());

					jiraService.releaseVersion(jiraProjectKey,
                            releaseRemoteVersion);
                    getLog().info(
                            "Version " + remoteVersion.getName()
                                    + " was released in JIRA. Archive: " + archive);
					ret = remoteVersion;
					break;
				}
			}
		}
		return ret;
	}
}
