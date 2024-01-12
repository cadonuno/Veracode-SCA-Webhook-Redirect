# Veracode-SCA-Webhook-Redirect

This Project exposes a Tomcat server that can redirect Veracode Agent-based SCA web hooks to Teams and Slack.

To configure it:
* Create a file for your endpoints map in the following format (one line per workspace/project pair):
    * **workspace_id** **project_id** **endpoint_url** **endpoint_type (Teams or Slack - defaults to Teams)**
    * Don't forget the spaces between each field
* Open EndpointsMap.java and point the **CONFIGURATION_FILE** constant to your endpoints map configuiration file.
