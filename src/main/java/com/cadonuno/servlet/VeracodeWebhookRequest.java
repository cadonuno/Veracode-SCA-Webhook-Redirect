package com.cadonuno.servlet;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class VeracodeWebhookRequest {
    public static class Issue {
        public int id;
        public String status;
        public String issueUrl;
        public Vuln vuln;
    }

    public static class Organization {
        public int id;
        public String name;
        public String planType;
    }

    public static class Project {
        public int id;
        public String name;
    }

    public static class Root {
        public String event;
        public Organization organization;
        public Workspace workspace;
        public User user;
        public ArrayList<Issue> issues;
        public Scan scan;
        public Project project;
    }

    public static class Scan {
        public int id;
        public String commit;
        public String branch;
        public String tag;
        public String reportLink;
        public int vulnIssuesCount;
        public int outofDateIssuesCount;
        public int licenseIssuesCount;
    }

    public static class User {
        public int id;
        public String name;
    }

    public static class Vuln {
        public int id;
        public String title;
        public double cvssScore;
        public double cvss3Score;
        public String cve;
        public String cveStatus;
        public String stage;
        public String disclosureDate;
        public boolean hasExploits;
        public ArrayList<String> vulnerabilityTypes;
        public String overview;
    }

    public static class Workspace {
        public int id;
        public String name;
    }
}
