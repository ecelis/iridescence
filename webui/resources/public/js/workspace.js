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
  }
}

/**
 * Triggers when an element from Toolbar is dragged into workspace
 * @method toolUp
 * */
var release = function() {
  this.attr("x", tx + 5);
  this.attr("y", ty + 5);
  addToDiagram(this);
}

var util = new Util();
// Global settings
var tx = 4, ty = 4;
var paper = Raphael("work-canvas", 600, 500);  // Creates canvas 320×200@10,50
var workspace = paper.set();            // Create a default workspace
var connections = [];                       // Connections between shapes
var connect = [];                           // Temporary queue for connections
var toolbar = paper.rect(tx, ty, 40, 300); // Placeholder for the tools
// We'll derive other shapes from this one
var basicShape = paper
  .rect(tx + 5, ty + 5, 30, 20)
  .attr({"fill": "#CCC",
        "fill-opacity": 0,
        "stroke-width": 3,
        cursor: "move"});
// Same as basicShape its a basic connector, derive other from it
var connectShape = paper
  .path("M9 55L45 80")
  .attr({"stroke-width": 3,
        cursor: "move"});

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
         "workspace":JSON.stringify(payload)});
}

/**
 * Remove shape from workspace
 * @method remove
 * @param {Integer} id of the Raphael element
 * */
var remove = function(id) {
  // TODO Fix this, should be donw within w.remove
  var shape = paper.getById(id);
  workspace.exclude(s);
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


// Attach listeners to Toolbar elements
basicShape.drag(move, dragger, release);
connectShape.click(function(){addToDiagram(this)});
// Bind listeners to Properties controls
// TODO Do this in one iteration of all form controls
$('#type-lst li a').click(function(){
  var type = $(this).text().toUpperCase().replace(' ','');
  $('#btn-type').html(type + '<span class="caret"></span>');
  $('#step-type').val(type);
  update($('#step-id').val());
});
$('#step-name').change(function(){update($('#step-id').val())});
$('#step-url').change(function(){update($('#step-id').val())});
$('#remove-btn').click(function(){remove($('#step-id').val())});
$('#clone-btn').click(function(){clone($('#step-id').val())});
$('#save-btn').click(function(){save()});

