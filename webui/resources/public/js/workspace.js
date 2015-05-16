/**
 *   Drag & Drop workspace
 *   Copyright (C) 2015 eCaresoft Inc
 *   Ernesto Angel Celis de la Fuente <developer@celisdelafuente.net>
 *
 *   This file is part of Iridescence Smart Connector
 *
 *   Iridescence Smart Connector is free software: you can redistribute it
 *   under the terms of the GNU Affero General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or (at
 *   your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
var util = new Util();      // Utilities such as guid generator and crypto
var tbX = 4,
  tbY = 4,
  tbW = 40,
  tbH = 300,
  paperW = 768,
  paperH = 500;
var paper = Raphael("work-canvas",
                    paperW, paperH);  // Creates canvas 320×200@10,50
////////////////////// ICONS ///////////////////////
 /**
 * Workspace Metadata
 */
var work_meta = {'type': null,      // Workspace metadata
  'guid': null,
  'name': null,
  'comments': null,
  'draft': true
};
var property = {            // TODO Get rid of it
  //this.data("props");    // Artifact's properties
  'name': null,
  'type': null,
  'url': null
};
// Global settings
var connections = [];       // Connections between adapters
var connect = [];           // Temporary queue for connections
var adapters = paper.set();           // Create a default adapters
var work_guid = util.guid();          // Generate adapters GUID
var toolbar = paper.rect(tbX, tbY,
                         tbW,
                         tbH);        // Placeholder for the tools
// We'll create adapters based on it
var generic_adapter = paper
  .rect(tbX + 5, tbY + 5, 30, 20)
  .attr({"fill": "#CCC",
        "fill-opacity": 0,
        "stroke-width": 3,
        cursor: "move"});
  
// Same as generic_adapter its a basic connector, derive other from it
var connector = paper
  .path("M9 35L40 60")
  .attr({"stroke-width": 3,
        cursor: "move"});
// TODO Moar adapters
// Start and End adapters, there should be one of each by default
// in every adapters
/**
* start does nothing, only serves as a visual aid for the flow
*/
var start = paper.circle(tbX + 80, tbY + 75, 15)
  .attr({"fill": "#7AC200",
        "fill-opacity": 100,
        "stroke-width": 3,
        "stroke": "#298500",
        cursor: "move"})
  .data("props", {"type":"START", "name":"Start", "url":null});
adapters.push(start);

/**
 * finishs is an adapter which is obviously the last adapter
 * in the work flow. By default it is configured to end in
 * Smart Connector's log with INFO level.
 */
var finish = paper.circle(paperW - 80, paperH - 75, 15)
  .attr({"fill": "#D21C00",
        "fill-opacity": 100,
        "stroke-width": 3,
        "stroke": "#990000",
        cursor: "move"})
  .data("props", {"type":"FINISH", "name":"Finish", "url":null});
adapters.push(finish);

/**
 * Add a connecto between adapters
 * @method
 * @param {Raphael.Element} artifact connector
 * */
var connectionPush = function(artifact) {
  if (connect.length < 2) {   // If the connection queue's length < 2
    if(connect[0] != artifact) {  // and If adapter isn't already in queue
      connect.push(artifact);     // add adapter to queue
    }
  }
  property = artifact.data("props");
  $('#adapter-name').val(property.name);
  $('#adapter-url').val(property.url);
  $('#adapter-id').val(artifact.id);
  $('#properties a[href="#adapter"]').tab('show');
}

/**
  Triggered when a adapter starts moving
*/
var dragger = function() {
  // TODO Fix for paths
  switch(this.type) {
    case "rect":
      this.ox = this.attr("x");
      this.oy = this.attr("y");
      break;
    case "circle":
    case "ellipse":
      this.ox = this.attr("cx");
      this.oy = this.attr("cy");
      break;
  }
  this.animate({"fill-opacity": .2}, 500);
}

/**
  Triggered when a adapter is moving
  */
var move = function(dx, dy) {
  var coordinates;
  if (this.type == "rect") {
    coordinates = {x: this.ox + dx, y: this.oy + dy};
  } else {
    coordinates = { cx: this.ox + dx, cy: this.oy + dy};
  }
  this.attr(coordinates);
  // TODO
  for(var i = connections.length; i--;) {
    paper.connection(connections[i]);
  }
  paper.safari();
}

/**
  Triggered when a adapter stops moving
  */
var up = function() {
  this.animate({"fill-opacity": 0}, 500);
}

/**
 * Triggers when an element from Toolbar is dragged into adapters
 * @method toolUp
 * */
var release = function() {
  this.attr("x", tbX + 5);
  this.attr("y", tbY + 5);
  addToDiagram(this);
}

/**
 * Triggered when a adapter is clicked, populates the
 * properties panel
 * @method modify
 * */
var modify = function() {
  if(this.type != "path") {
     connectionPush(this);
  } else {
    $('#properties a[href="#connector"]').tab('show');
  }
}

var setData = function(adapter) {
  var data = null;
  if(adapter.type != "path") {
    // TODO switch-case here to apply apropriate data model
    data = new DataModel();
  } else {
    data = {"id": adapter.id, "name": adapter.attrs.title};
  }
  adapter.data("props", data);
}

/**
 * Add a new element from Toolbar to adapters
 * @method addToDiagram
 * @param {Object} adapter Raphael Element object
 * */
var addToDiagram = function (adapter) {
  var color = Raphael.getColor(); // Get next color in spectrum
  if(adapter.type === "path") {
    if(connect.length == 2) {     // Create a connection between adapters
      var newConnection = paper.connection(connect[0], connect[1], "#000");
      newConnection.line.attr({
          "title": id = connect[0].id + 'to' + connect[1].id,
          "stroke-width": 3});
      setData(newConnection.line);
      newConnection.line.click(modify);
      connections.push(newConnection);
      adapters.push(newConnection.line);
      connect = [];               // Empty queue
    } else {
      return;                     // 2 adapters in queue are required
    }                             // to make a connection
  } else {
    var newadapter = adapter.clone();   // Hello Dolly!
    setData(newadapter);              // Give Dolly a Soul
    newadapter.attr({fill: color,
                  stroke: color,
                  "fill-opacity": 0,
                  "stroke-width": 3,
                  "width": 50,
                  "height": 30,
                  "x": 50 + Math.floor(Math.random()*160),
                  "y": 70 + Math.floor(Math.random()*160)})
              .data("props", {"type": "GENERIC",
                    "id":this.id,
                    "name":null,
                    "url": null})
              .drag(move, dragger, up)
              .click(modify);
    adapters.push(newadapter);   // Append new adapter to adapters
    if(adapters.length == 3) {  // First adapter of new workspace
      connect.push(start);
      connect.push(newadapter);
      var firstConnection = paper.connection(connect[0], connect[1], "#000");
      firstConnection.line.attr({
          "title": id = connect[0].id + 'to' + connect[1].id,
          "stroke-width": 3});
      setData(firstConnection.line);
      firstConnection.line.click(modify);
      connections.push(firstConnection); // connect to the begining
      connect = [];
    }
  }
}

/////////// Adapter functions
/**
 * Remove adapter from adapters
 * @method remove
 * @param {Integer} id of the Raphael element
 * */
var remove = function(id) {
  // TODO Fix this, should be donw within w.remove
  var adapter = paper.getById(id);
  adapters.exclude(adapter);
  adapter.remove();
}

/**
 * Clone a adapter by id
 * @method cloneBlk
 * @param {Integer} id of the Raphael element
 * */
var clone = function(id) {
  addToDiagram(paper.getById(id));
}

/**
 * Update the adapter properties data 'props' with values from
 * the properties panel
 * @method updateadapter
 * @param {Integer} id of Raphael element
 * */
var update_adapter = function(id) {
  // TODO Fix it, values get borked in the panel
  var adapter = paper.getById(id);
  adapter.data("props").id = id;
  adapter.data("props").type = $('#adapter-type').val();
  adapter.data("props").name = $('#adapter-name').val();
  adapter.data("props").url = $('#adapter-url').val();
  adapter.attr({'title': adapter.data("props").name,
          'text':adapter.data("props").name});
  test_connection($('#adapter-url').val());
}

/**
 * Save adapters to YAML in the server
 * @method save
 * */
var save = function() {
  var payload = {'meta': null, 'data': []};
  payload.meta = JSON.stringify(work_meta);
  adapters.forEach(function(adapter) {
    payload.data.push(JSON.stringify(adapter.data("props")));
  });
  $.post("/api/", {"__anti-forgery-token": $('#__anti-forgery-token').val(),
         "workspace":payload});
}

/**
 * Update work properties
 * @method
 */
var update_workspace = function() {
  work_meta.type = $('#work-type').val();
  work_meta.guid = $('#work-guid').val();
  work_meta.name = $('#work-name').val();
  work_meta.comments = $('#work-comments').val();
  if($('#work-draft').is(':checked')) {
    work_meta.draft = true;
  } else {
    work_meta.draft = false;
  }
}

////////// Connectors functions
/**
 * Update Connector properties
 * @method
 * @param id connector id
 */
var update_connector = function(id) {
  var connector = paper.getById(id);
  connector.data("props").id = id;
  connector.data("props").name = connector.title;
}

// Initialize some properties for a new workspace
// TODO Check if the values aren't overwriten when refreshing webpage
$('#work-guid').val(work_guid);
$('#work-guid-label').html("Id: " + work_guid);
// Bind listeners to Properties controls
// TODO Do this in one iteration of all
// adapters
//Attach listeners to Toolbar elements
//
generic_adapter.drag(move, dragger, release);
connector.click(function(){addToDiagram(this)});
start.drag(move, dragger, function(){});
finish.drag(move, dragger, function(){}).click(modify);
$('#work-type-lst li a').on("click change", function(){
  var type = $(this).text().toUpperCase().replace(' ','').replace('.','');
  $('#btn-work-type').html(type + '<span class="caret"></span>');
  $('#work-type').val(type);
});
$('#workspace :input').on("click change keyup", function() {
    update_workspace();
});
//
// Adapters
$('#adapter :input').on("click change keyup", function() {
    update_adapter($('#adapter-id').val());
});

$('#adapter-type-lst li a').on("click change", function(){
  var type = $(this).text().toUpperCase().replace(' ','');
  $('#btn-adapter-type').html(type + '<span class="caret"></span>');
  $('#adapter-type').val(type);
  update_adapter($('#adapter-id').val());
});
//
// Connectors
//
$('#connector :input').on("click change keyup", function(){
  update_adapter($('#adapter-id').val());
});
//
// Form controls
//
// TODO Remove from workspace is badly broken
$('#remove-btn').click(function(){remove($('#adapter-id').val())});
$('#clone-btn').click(function(){clone($('#adapter-id').val())});
$('#save-btn').click(function(){save()});

