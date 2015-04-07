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
  if(this.type == "rect") {
    this.ox = this.attr("x");
    this.oy = this.attr("y");
  } else {
    this.ox = this.attr("cx");
    this.oy = this.attr("cy");
  }
  this.animate({"fill-opacity": .2}, 500);
}

/**
  Triggered when a shape is moving
  */
var move = function(dx, dy) {
  // TODO Fix for paths
  if (this.type == "rect") {
    att = {x: this.ox + dx, y: this.oy + dy};
  } else {
    att ={ cx: this.ox + dx, cy: this.oy + dy};
  }
  this.attr(att);
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

var modify = function() {
  var p = this.data("props");
  $('#blk-name').val(p.name);
  $('#blk-url').val(p.url);
  $('#blk-id').val(this.id);
}

/**
  Workspace is a linked list of configuration objects
  @class Workspace
  @constructor
  */
Workspace = function() {};

Workspace.prototype = {
  lenght: 0,
  first: null,
  last: null
};

/**
  Append a new element to workspace
  @method append
  @param {Object} shape a Raphael Element object
  */
Workspace.prototype.append = function(shape) {
  if(this.first === null) {
    shape.prev = shape;
    shape.next = shape;
    this.first = shape;
    this.last = shape;
  } else {
    shape.prev = this.last;
    shape.next = this.first;
    this.first.prev = shape;
    this.last.next = shape;
    this.last = shape;
  }
  this.lenght++;
};

/**
  Insert a new element in the workspace before one specific element
  which is already in the workspace, useful for workflow.
  @method insertAfter
  @param {Object} shape a Raphael Element object
  @param {Object} newShape the new Raphael Element object
  */
Workspace.prototype.insertAfter = function(shape, newShape) {
  newShape.prev = shape;
  newShape.next = shape.next;
  shape.next.prev = newShape;
  shape.next = newShape;
  if (newShape.prev == this.last) { this.last = newShape; }
  this.lenght++;
};

/**
 * Remove an element from workspace
 * @method remove
 * @param {Object} shape a Raphael Element object
 * */
Workspace.prototype.remove = function(shape) {
  if(this.lenght > 1) {
     shape.prev.next = shape.next;
     shape.next.prev = shape.prev;
     if(shape == this.first) { this.first = shape.next; }
     if(shape == this.last) { this.last = shape.prev; }
  } else {
     this.first = null;
     this.last = null;
  }
  shape.prev = null;
  shape.next = null;
  this.lenght--;
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
  var newShape = shape.clone();   // Hello Dolly!
  setData(newShape);              // Give Dolly a Soul
  if(newShape.type === "path") {
    newShape.attr({});
  } else {
    newShape.attr({fill: color,
                  stroke: color,
                  "fill-opacity": 0,
                  "stroke-width": 2,
                  "width": 50,
                  "height": 30,
                  "x": 50 + Math.floor(Math.random()*160),
                  "y": 70 + Math.floor(Math.random()*160)});
  }
  newShape.drag(move, dragger, up);
  connections.push(paper
                   .connection(shape, newShape, "#000"));
  w.append(newShape);   // Append new shape to workspace
  newShape.click(modify);
}

/**
 * Triggers when an element from Toolbar is dragged into workspace
 * @method toolUp
 * */
var toolUp = function() {
  this.attr("x", tx + 5);
  this.attr("y", ty + 5);
  addToDiagram(this);
}

// Create a default workspace
var w = new Workspace();
var util = new Util();
// Global settings
var tx = 20, ty = 20;
// Creates canvas 320 × 200 at 10, 50
var paper = Raphael(10, 50, 640, 480);
// A set of connections between shapes
connections = [];
// Just a placeholder for the tools
var toolbar = paper.rect(tx, ty, 40, 452);
// We'll derive other shapes from this one
var basicShape = paper
  .rect(tx + 5, ty + 5, 30, 20)
  .attr({"fill": "#CCC",
        "fill-opacity": 0,
        "stroke-width": 2,
        cursor: "move"});
// Same as basicShape its a basic connector, derive other from it
var connectShape = paper
  .path("M25 55L55 80")
  .attr({"stroke-width": 2,
        cursor: "move"});

/**
 * Save workspace to YAML in the server
 * @method save
 * */
var save = function() {
  //TODO
  alert("What's up Linda?");
}

/**
 * Remove shape from workspace
 * @method remove
 * @param {Integer} id of the Raphael element
 * */
var remove = function(id) {
  // TODO Fix this, should be donw within w.remove
  var s = paper.getById(id);
  w.remove(s);
  s.remove();
}

/**
 * Clone a shape by id
 * @method cloneBlk
 * @param {Integer} id of the Raphael element
 * */
var cloneBlk = function(id) {
  addToDiagram(paper.getById(id));
}


// Attach listeners to Toolbar elements
basicShape.drag(move, dragger, toolUp);
connectShape.drag(move, dragger, toolUp);
// Bind listeners to Properties controls
$('#remove-btn').click(function(){remove($('#blk-id').val())});
$('#clone-btn').click(function(){cloneBlk($('#blk-id').val())});
$('#save-btn').click(function(){save($('#blk-id').val())});

