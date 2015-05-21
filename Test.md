Smart Connector Test Workflow
=============================

  1. Run iridescence/webui with `lein clean; lein deps; lein ring server`
  2. Browse to http://127.0.0.1:3000/
  3. Open the Browser's Developer Tools `CTRL+Shift+I` and select the
     Network console
  4. Fill the Workspace name, select output type and optionally write
     some notes
  5. Save the workspace
  6. Check the response for the POST request to be 200, select it in the
     Network Console to inspect the response
  7. Drag the squared shape from the Workspace's Toolbar, click on it
  8. Click on the Finish Adapter (red circle)
  9. Click over the Connector (line) in the Workspace's Toolbar
  10. A connector between the Adapter and the Finish Adapter should be
      drawn
  11. Click on the Adapter and fill in the Properties Pannel
  12. Do the same for the Finish Adapter
  13. Keep an eye in the reponses in the Network Console
  14. Save again
  15. Reset the workspace by reloading the page
  16. From the file system drag and drop in the web browser the YAML 
      representation of the workspace // TODO BROKEN
  17. The Workspace should be loaded in the current workspace // BROKEN
  18. Click over one connector and fill in the Properties Panel
  19. Save the workspace
  20. Run the workspace

Use Case
--------

### Patient Admission


### Lab Test Order for Patient



