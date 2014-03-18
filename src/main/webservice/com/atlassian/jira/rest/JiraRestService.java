package com.atlassian.jira.rest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class JiraRestService implements JiraService {

    private HttpClientContext context = HttpClientContext.create();
    private HttpHost targetHost;

    private String path;


    public JiraRestService(final URL u, final String jiraUser, final String jiraPassword) {
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        targetHost = new HttpHost(u.getHost(), u.getProtocol().equalsIgnoreCase("http") ? 80 : 443, u.getProtocol());
        path = u.getPath();
        credsProvider.setCredentials(new AuthScope(targetHost.getHostName(), targetHost.getPort()),
                new UsernamePasswordCredentials(jiraUser, jiraPassword));
        AuthCache authCache = new BasicAuthCache();
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);

        context.setCredentialsProvider(credsProvider);
        context.setAuthCache(authCache);
    }

    /*
     * (non-Javadoc)
     * @see com.atlassian.jira.rest.JiraService#getVersions(java.lang.String)
     */
    @Override
    public RemoteVersion[] getVersions(String jiraProjectKey) {
        HttpGet request = new HttpGet(path + "/project/" + jiraProjectKey + "/versions");

        try {
            String responseAsString = execute(request);

            ObjectMapper mapper = new ObjectMapper();
            JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector();
            mapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);

            return mapper.readValue(responseAsString, RemoteVersion[].class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JiraMavenPluginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.atlassian.jira.rest.JiraService#getProject(java.lang.String)
     */
    @Override
    public Project getProject(String jiraProjectKey) {
        HttpGet request = new HttpGet(path + "/project/" + jiraProjectKey);
        try {
            String responseAsString = execute(request);

            ObjectMapper mapper = new ObjectMapper();
            JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector();
            mapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);

            return mapper.readValue(responseAsString, Project.class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JiraMavenPluginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /*
     * (non-Javadoc)
     * @see com.atlassian.jira.rest.JiraService#addVersion(java.lang.String, com.atlassian.jira.rest.RemoteVersion)
     */
    @Override
    public RemoteVersion addVersion(String jiraProjectKey, RemoteVersion newVersion) {

        Project project = getProject(jiraProjectKey);
        HttpPost request = new HttpPost(path + "/version");
        newVersion.setProjectId(project.getId());
        try {
            ObjectMapper mapper = new ObjectMapper();
            JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector();
            mapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);

            String writeValueAsString = mapper.writeValueAsString(newVersion);
            request.setEntity(new StringEntity(writeValueAsString));
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            return mapper.readValue(execute(request), RemoteVersion.class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JiraMavenPluginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /*
     * (non-Javadoc)
     * @see com.atlassian.jira.rest.JiraService#releaseVersion(java.lang.String, com.atlassian.jira.rest.RemoteVersion)
     */
    @Override
    public RemoteVersion releaseVersion(String jiraProjectKey, RemoteVersion remoteReleasedVersion) {
        HttpPut request = new HttpPut(path + "/version/" + remoteReleasedVersion.getId());
        try {
            ObjectMapper mapper = new ObjectMapper();
            JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector();
            mapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);
            
            String writeValueAsString = mapper.writeValueAsString(remoteReleasedVersion);
            request.setEntity(new StringEntity(writeValueAsString));
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            return mapper.readValue(execute(request), RemoteVersion.class);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JiraMavenPluginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

    /*
     * (non-Javadoc)
     * @see com.atlassian.jira.rest.JiraService#getIssuesFromJqlSearch(java.lang.String, int)
     */
    @Override
    public List<Issue> getIssuesFromJqlSearch(String jql, Integer maxIssues) {

        URIBuilder uriBuilder =
                new URIBuilder().setScheme(targetHost.getSchemeName()).setHost(targetHost.getHostName())
                        .setPort(targetHost.getPort()).setPath(path + "/search/");


        // rest/api/2/search?jql&startAt&maxResults&validateQuery&fields&expand
        if (jql != null && !jql.isEmpty()) {
            uriBuilder.addParameter("jql", jql);
        }
        if (maxIssues > 0) {
            uriBuilder.addParameter("maxResults", maxIssues.toString());
        }
        try {
            HttpGet request = new HttpGet(uriBuilder.build());
            String responseAsString = execute(request);

            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector();
            mapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);
            System.out.println(responseAsString);
            RemoteIssue readValue = mapper.readValue(responseAsString, RemoteIssue.class);
            return readValue.getIssues();

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JiraMavenPluginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /*
     * (non-Javadoc)
     * @see com.atlassian.jira.rest.JiraService#archiveVersion(java.lang.String, java.lang.String, boolean)
     */
    @Override
    public RemoteVersion archiveVersion(String jiraProjectKey, String name, boolean archive) {
        for (RemoteVersion version : getVersions(jiraProjectKey)) {
            if (name.equals(version.getName())) {
                RemoteVersion rv = new RemoteVersion(version);
                rv.setArchived(archive);
                HttpPut request = new HttpPut(path + "/version/" + version.getId());
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector();
                    mapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);

                    String writeValueAsString = mapper.writeValueAsString(rv);
                    request.setEntity(new StringEntity(writeValueAsString));
                    request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
                    return mapper.readValue(execute(request), RemoteVersion.class);

                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JiraMavenPluginException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                break;
            }
        }
        return null;
    }

    private String execute(HttpUriRequest request) throws ClientProtocolException, IOException,
            JiraMavenPluginException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = httpclient.execute(targetHost, request, context);
        String responseAsString;
        try {
            if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 201
                    || response.getStatusLine().getStatusCode() == 204) {
                responseAsString = EntityUtils.toString(response.getEntity());
                return responseAsString;
            } else {
                throw new JiraMavenPluginException("HttpClient.execute not successfull! " + response.getStatusLine());
            }
        } finally {
            response.close();
        }
    }

}
