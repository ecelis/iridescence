/**
 *   Drag & Drop Web Workspace
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

/**
 * Workspace Metadata
 */
var workspace_metadata = function() {};
workspace_metadata.prototype.type;
workspace_metadata.prototype.guid;
workspace_metadata.prototype.name;
workspace_metadata.prototype.comments;

/**
  Triggered when a shape starts moving
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
  Triggered when a shape is moving
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
  Triggered when a shape stops moving
  */
var up = function() {
  this.animate({"fill-opacity": 0}, 500);
}

/**
 * Triggers when an element from Toolbar is dragged into workspace
 * @method toolUp
 * */
var release = function() {
  this.attr("x", toolbarX + 5);
  this.attr("y", toolbarY + 5);
  addToDiagram(this);
}

/**
 * Triggered when a shape is clicked, populates the
 * properties panel
 * @method modify
 * */
var modify = function() {
  var property = this.data("props");
  switch(this.type) {
    case "rect":
      if (connect.length < 2) {   // If the connection queue's length < 2
        if(connect[0] != this) {  // and If shape isn't already in queue
          connect.push(this);     // add shape to queue
        }
      }
      $('#step-name').val(property.name);
      $('#step-url').val(property.url);
      $('#step-id').val(this.id);
      $('#properties a[href="#connector"]').tab('show');
      break;
    case "path":
      $('#properties a[href="#connection"]').tab('show');
      break;
  }
}

var setData = function(shape) {
  var d = new DataModel();
  shape.data("props", d);
}

/**
 * Add a new element from Toolbar to Workspace
 * @method addToDiagram
 * @param {Object} shape Raphael Element object
 * */
var addToDiagram = function (shape) {
  var color = Raphael.getColor(); // Get next color in spectrum
  if(shape.type === "path") {
    if(connect.length == 2) {     // Create a connection between adapters
      var newConnection = paper.connection(connect[0], connect[1], "#000");
      newConnection.line.attr({
          "title": id = connect[0].id + 'to' + connect[1].id,
          "stroke-width": 3});
      newConnection.line.click(modify);
      connections.push(newConnection);
      workspace.push(newConnection.line);
      connect = [];               // Empty queue
    } else {
      return;                     // 2 shapes in queue are required
    }                             // to make a connection
  } else {
    var newShape = shape.clone();   // Hello Dolly!
    setData(newShape);              // Give Dolly a Soul
    newShape.attr({fill: color,
                  stroke: color,
                  "fill-opacity": 0,
                  "stroke-width": 3,
                  "width": 50,
                  "height": 30,
                  "x": 50 + Math.floor(Math.random()*160),
                  "y": 70 + Math.floor(Math.random()*160)});
    newShape.drag(move, dragger, up).click(modify);
    workspace.push(newShape);   // Append new shape to workspace
    if(workspace.length == 1) {   // If workspace empty we start from scratch
      connect.push(startShape);
      connect.push(newShape);
      var firstConnection = paper.connection(connect[0], connect[1], "#000");
      firstConnection.line.attr({
          "title": id = connect[0].id + 'to' + connect[1].id,
          "stroke-width": 3});
      firstConnection.line.click(modify);
      connections.push(firstConnection); // connect to the begining
      workspace.push(firstConnection.line);
    }
  }
}


var util = new Util();
// Global settings
var connections = [];               // Connections between shapes
var connect = [];                   // Temporary queue for connections
var workspace_meta = new workspace_metadata();

var allInputs = $(":input");                // Properties list
var toolbarX = 4,
  toolbarY = 4,
  toolbarWidth = 40,
  toolBarHeigth = 300,
  paperWidth = 768,
  paperHeigth = 500;
var paper = Raphael("work-canvas",
                    paperWidth, paperHeigth);  // Creates canvas 320×200@10,50
var workspace = paper.set();            // Create a default workspace
var work_guid = util.guid();            // Generate Workspace GUID
var toolbar = paper.rect(toolbarX, toolbarY,
                         toolbarWidth,
                         toolBarHeigth); // Placeholder for the tools
// We'll create shapes based it
var basicShape = paper
  .rect(toolbarX + 5, toolbarY + 5, 30, 20)
  .attr({"fill": "#CCC",
        "fill-opacity": 0,
        "stroke-width": 3,
        cursor: "move"});
// Same as basicShape its a basic connector, derive other from it
var connectShape = paper
  .path("M9 35L40 60")
  .attr({"stroke-width": 3,
        cursor: "move"});
// TODO Moar shapes

// Start and End shapes, there should be one of each by default
// in every workspace
var startShape = paper
  .circle(toolbarX + 80, toolbarY + 75, 15)
  .attr({"fill": "#7AC200",
        "fill-opacity": 100,
        "stroke-width": 3,
        "stroke": "#298500",
        cursor: "move"})
  .data("props", {"type":"start"});

var endShape = paper
  .circle(paperWidth - 80, paperHeigth - 75, 15)
  .attr({"fill": "#D21C00",
        "fill-opacity": 100,
        "stroke-width": 3,
        "stroke": "#990000",
        cursor: "move"})
  .data("props", {"type":"end"});

/**
 * Remove shape from workspace
 * @method remove
 * @param {Integer} id of the Raphael element
 * */
var remove = function(id) {
  // TODO Fix this, should be donw within w.remove
  var shape = paper.getById(id);
  workspace.exclude(shape);
  shape.remove();
}

/**
 * Clone a shape by id
 * @method cloneBlk
 * @param {Integer} id of the Raphael element
 * */
var clone = function(id) {
  addToDiagram(paper.getById(id));
}

/**
 * Update the shape properties data 'props' with values from
 * the properties panel
 * @method updateShape
 * @param {Integer} id of Raphael element
 * */
var update = function(id) {
  // TODO Fix it, values get borked in the panel
  var s = paper.getById(id);
  s.data("props").id = id;
  s.data("props").type = $('#step-type').val();
  s.data("props").name = $('#step-name').val();
  s.data("props").url = $('#step-url').val();
  s.attr({'title': s.data("props").name,
          'text':s.data("props").name});
}

/**
 * Save workspace to YAML in the server
 * @method save
 * */
var save = function() {
  //TODO
  var payload = [];
  workspace.forEach(function(s) {
    payload.push(s.data("props"));
  });
  $.post("/api/", {"__anti-forgery-token": $('#__anti-forgery-token').val(),
         "workspace":{"data": JSON.stringify(payload),
                    "meta": JSON.stringify(workspace_meta)}});
}

/**
 * Update workspace properties
 * @method
 */
var updateWorkspace = function() {
  workspace_meta.type = $('#work-type').val();
  workspace_meta.guid= $('#work-guid').val()
  workspace_meta.name = $('#work-name').val()
  workspace_meta.comments = $('#work-comments').val()

}

// Initialize some properties for a new workspace
// TODO Check if the values aren't overwriten when refreshing webpage
$('#work-guid').val(work_guid);
$('#work-guid-label').html("Id: " + work_guid);
// Attach listeners to Toolbar elements
basicShape.drag(move, dragger, release);
connectShape.click(function(){addToDiagram(this)});
startShape.drag(move, dragger, function(){});
endShape.drag(move, dragger, function(){});
// Bind listeners to Properties controls
// TODO Do this in one iteration of all
// Workspace
//
$('#work-type-lst li a').on("click change", function(){
  var type = $(this).text().toUpperCase().replace(' ','').replace('.','');
  $('#btn-work-type').html(type + '<span class="caret"></span>');
  $('#work-type').val(type);
});
$('#workspace :input').on("click change keyup", function() {
  updateWorkspace();
});
//
// Adapters
//
$('#step-type-lst li a').on("click change", function(){
  var type = $(this).text().toUpperCase().replace(' ','');
  $('#btn-step-type').html(type + '<span class="caret"></span>');
  $('#step-type').val(type);
  update($('#step-id').val());
});
//
// Connectors
//
$('#connector :input').on("click change keyup", function(){
  update($('#step-id').val());
});
//
// Form controls
//
// TODO Remove from workspace is badly broken
$('#remove-btn').click(function(){remove($('#step-id').val())});
$('#clone-btn').click(function(){clone($('#step-id').val())});
$('#save-btn').click(function(){save()});

