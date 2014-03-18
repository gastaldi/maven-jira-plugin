/**
 * 
 */
package com.george.plugins.jira;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.atlassian.jira.rest.JiraService;
import com.atlassian.jira.rest.RemoteVersion;

/**
 * JUnit test case for Jira version MOJO
 * 
 * @author george
 * 
 */
public class CreateNewVersionMojoTest {

	private static final RemoteVersion[] VERSIONS = new RemoteVersion[]{
			new RemoteVersion("1", "1.0", false, false),
			new RemoteVersion("2", "2.0", false, false),
			new RemoteVersion("3", "3.0", false, false),
			new RemoteVersion("3", "3.1", false, false)};

	private CreateNewVersionMojo jiraVersionMojo;
	private JiraService jiraStub;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.jiraVersionMojo = new CreateNewVersionMojo();
		jiraVersionMojo.jiraUser = "user";
		jiraVersionMojo.jiraPassword = "password";

		// This removes the locator coupling
		jiraStub = EasyMock.createStrictMock(JiraService.class);
		this.jiraVersionMojo.jiraService = jiraStub;
	}

	/**
	 * Test method for {@link CreateNewVersionMojo#execute()}
	 * 
	 * @throws Exception
	 */
//	@Test
	public void testExecuteWithNewDevVersion() throws Exception {
		jiraVersionMojo.developmentVersion = "5.0";
		// Chama o getVersions
		expect(
				jiraStub.getVersions(
						jiraVersionMojo.jiraProjectKey)).andReturn(VERSIONS)
				.once();

		// Adiciona a nova versao
		expect(
				jiraStub.addVersion(
						jiraVersionMojo.jiraProjectKey, new RemoteVersion(null,
								jiraVersionMojo.developmentVersion, false,
								false))).andReturn(VERSIONS[0]);
		// Habilita o controle para inicio dos testes
		EasyMock.replay(jiraStub);

		jiraVersionMojo.execute();
	}

//	@Test
	public void testExecuteWithNewDevVersionIncludingQualifierAndSnapshot()
			throws Exception {
		jiraVersionMojo.developmentVersion = "5.0-beta-2-SNAPSHOT";
		// Chama o getVersions
		expect(
				jiraStub.getVersions(
						jiraVersionMojo.jiraProjectKey)).andReturn(VERSIONS)
				.once();

		// Adiciona a nova versao
		expect(
				jiraStub.addVersion(
						jiraVersionMojo.jiraProjectKey, new RemoteVersion(null,
								"5.0 Beta 2", false, false)))
				.andReturn(VERSIONS[0]);
		// Habilita o controle para inicio dos testes
		EasyMock.replay(jiraStub);

		jiraVersionMojo.execute();
	}

//	@Test
	public void testExecuteWithNewDevVersionAndUseFinalNameForVersionSetToTrue()
			throws Exception {
		jiraVersionMojo.developmentVersion = "5.0-beta-2-SNAPSHOT";
		jiraVersionMojo.finalNameUsedForVersion = true;
		jiraVersionMojo.finalName = "my-component-5.0-beta-2-SNAPSHOT";
		// Chama o getVersions
		expect(
				jiraStub.getVersions(
						jiraVersionMojo.jiraProjectKey)).andReturn(VERSIONS)
				.once();

		// Adiciona a nova versao
		expect(
				jiraStub.addVersion(
						jiraVersionMojo.jiraProjectKey, new RemoteVersion(null,
								"My Component 5.0 Beta 2", false, false))).andReturn(VERSIONS[0]);
		// Habilita o controle para inicio dos testes
		EasyMock.replay(jiraStub);

		jiraVersionMojo.execute();
	}

	/**
	 * Test method for {@link ReleaseVersionMojo#execute()}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testExecuteWithExistentDevVersion() throws Exception {
		jiraVersionMojo.developmentVersion = "2.0";
		// Chama o getVersions
		expect(
				jiraStub.getVersions(
						jiraVersionMojo.jiraProjectKey)).andReturn(VERSIONS)
				.once();

		// Habilita o controle para inicio dos testes
		replay(jiraStub);

		jiraVersionMojo.execute();
	}

	@After
	public void tearDown() {
		this.jiraVersionMojo = null;
	}
}
