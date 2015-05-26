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
var tbX = 4,                // Toolbar X position
  tbY = 4,                  // Toolbar Y position
  tbW = 40,                 // Toolbar Width
  tbH = 80,                // Toolbar Heigth
  paperW = 768,             // Workspace Width
  paperH = 100;             // Workspace Height
var paper = Raphael("work-canvas",
                    paperW, paperH);  // Workspace

/**
* Workspace Metadata
* @property
*/
var work_meta = {'type': null,        // Workspace metadata
  'guid': null,
  'name': null,
  'comments': null,
  'draft': true
};
/**
 * Adapter property model
 * @property
 */
var property = {                      // TODO Get rid of it
  //this.data("props");               // Artifact's properties
  'name': null,
  'type': null,
  'url': null,
  'items': null
};

var adapter_items = [];               // Adapter Items from source
// Global settings
var connections = [];                 // Connections between adapters
var connect = [];                     // Temporary queue for connections
var adapters = paper.set();           // Create a default adapters
var work_guid = util.guid();          // Generate adapters GUID
// We'll create adapters based on generic_adapter
// TODO avoid cloning in alt-yaout branch
var generic_adapter = paper
  .rect(-100, -100, 30, 20)
  .attr({"fill": "#CCC",
        "fill-opacity": 0,
        "stroke-width": 3,
        cursor: "move"});

// Same as generic_adapter its a base connector, derive others from it
// TODO avoid cloning in alt-yaout branch
var connector = paper
  .path("M-10 0L0 0")
  .attr({"stroke-width": 3,
        cursor: "move"});

/**
* start does nothing, only serves as a visual aid for the flow
*/
var start = paper.circle(tbX + 80, paperH - 75, 15)
  .attr({"fill": "#7AC200",
        "fill-opacity": 100,
        "stroke-width": 3,
        "stroke": "#298500",
        cursor: "move"})
  .data("props", {"type":"START", "name":"Start", "url":null});
adapters.push(start);

/**
 * Finish is an adapter which is obviously the last adapter
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
 * Connect queue, Adapters are pushed to the queue in order to add a Connector
 * between them
 * @method
 * @param {Raphael.Element} artifact connector
 * */
var connect_queue = function(artifact) {
  if (connect.length < 2) {       // If the connection queue's length < 2
    if(connect[0] != artifact) {  // and If adapter isn't already in queue
      connect.push(artifact);     // add adapter to queue
    }
  }
}

/**
 * Triggered when an adapter starts moving
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
 * Triggered when a adapter is moving
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
 * Triggered when a adapter stops moving
 */
var up = function() {
  this.animate({"fill-opacity": 0}, 500);
}

/**
 * Triggers when an element from Toolbar is dragged into adapters
 * @method toolUp
 */
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
  property = this.data("props");
  // TODO clean all those ugly hacks introduced since alt-layout
  if(this.type != "circle") {
    $('#adapter-name').val(property.name);
    $('#adapter-url').val(property.url);
    $('#adapter-id').val(this.id);
    $('#properties a[href="#adapter"]').tab('show');
    connect_queue(this);
  } else {
    // TODO initialize properties, Name, etc
    $('#properties a[href="#connector"]').tab('show');
    var adapter_id = property.name.split("to")[0];
    update_connector(this);
  }
}

/**
 * Set default model to new Adapters or Connector
 */
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
      // TODO remove newConnection.line.click(modify);
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
                  "x": 390,
                  "y": 8})
              .data("props", {"type": "GENERIC",
                    "id":this.id,
                    "name":null,
                    "url": null,
                    "items": null})
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
      //firstConnection.line.click(modify);
      connections.push(firstConnection); // connect to the begining
      connect = [];     // Empty queue
    }
  }
}

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
  if(adapter_items.length > 0) {
    adapter.data("props").items = adapter_items;
  }
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

/**
 * Update Connector properties
 * @method
 * @param {Integer} connector id
 */
var update_connector = function(connector) {
  // TODO adapter_src??
  var adapter_src = paper.getById(connector.id);
  if(adapter_src.data("props").items != undefined)
    adapter_src.data("props").items.forEach(function(item) {
      $("#connector-items-lst")
        .append('<option value="' +
                item.name + '">' + item.name + '</option>');
    });
}

// TODO Check if the values aren't overwriten when refreshing webpage
$('#work-guid').val(work_guid);                   // Workspace GUID field
$('#work-guid-label').html("Id: " + work_guid);   // Workspace GUID label
// TODO Do this in one iteration of all
// adapters
//generic_adapter.drag(move, dragger, release);     // Adapter onDrag listener

connector.click(function() {                      // Connector onClick listener
  addToDiagram(this)
});

//start.drag(move, dragger, function(){});          // Start adapter onDrag

//finish.drag(move, dragger, function(){}).click(modify); // Finish adapter onDrag
finish.click(modify); // Finish adapter onDrag

$('#work-type-lst li a').on("click change",       // Workspace type listener
      function() {
      var type = $(this).text().toUpperCase().replace(' ','').replace('.','');
      $('#btn-work-type').html(type + '<span class="caret"></span>');
      $('#work-type').val(type);
});

$('#workspace :input').on("click change keyup", // Workspace properties listener
      function() {
        update_workspace();
});

$('#adapter :input').on("click change keyup",   // Adapter properties listener
      function() {
        update_adapter($('#adapter-id').val());
});

$('#adapter-type-lst li a').on("click change",  // Adapter type listener
      function() {
        var type = $(this).text().toUpperCase().replace(' ','');
        $('#btn-adapter-type').html(type + '<span class="caret"></span>');
        $('#adapter-type').val(type);
        update_adapter($('#adapter-id').val());
});

var s_driver, s_host, s_src, s_user, s_password, s_url;
var t_driver, t_host, t_target, t_user, t_password, t_url;

var update_srcurl = function() {
  //s_driver = $('#adapter-driver-lst li a').text().toLowerCase().replace(' ','');
  s_host = $('#adapter-host').val();
  s_src = $('#adapter-source').val();
  s_user = $('#adapter-user').val();
  s_password = $('#adapter-password').val();
  s_url = s_driver + "://" + s_host + "/" + s_src;
  // TODO use Array for URL parameters
  if(s_user != null) {
    s_url += "?user=" + s_user + "&password=" + s_password
  }
  $('#adapter-url').val(s_url);
};

var update_tgturl = function() {
  //t_driver = $('#connector-driver-lst li a').text().toLowerCase().replace(' ','');
  t_host = $('#connector-host').val();
  t_target = $('#connector-source').val();
  t_user = $('#connector-user').val();
  t_password = $('#connector-password').val();
  t_url = t_driver + "://" + t_host + "/" + t_target;
  // TODO use Array for URL parameters
  if(t_user != null) {
    t_url += "?user=" + t_user + "&password=" + t_password
  }
  $('#adapter-url').val(t_url);
};

$('#adapter-driver-lst li a').on("click change",
      function() {
        s_driver = $(this).text().toLowerCase().replace(' ','');
        $('#btn-adapter-driver').html(s_driver + '<span class="caret"></span>');
        $('#adapter-url').val(s_driver + "://host:port/source?user=someone&password=secret");
        update_adapter($('#adapter-id').val());
});

$('#connector-driver-lst li a').on("click change",
      function() {
        t_driver = $(this).text().toLowerCase().replace(' ','');
        $('#btn-connector-driver').html(t_driver + '<span class="caret"></span>');
        $('#connector-url').val(t_driver + "://host:port/target?user=someone&password=secret");
        update_connector(adapters[2]);
});

$('#adapter :input').on("click change keyup",
      function() {
        update_srcurl();
});
$('#connector :input').on("click change keyup",   // Connector properties listener
      function() {
        // TODO update_adapter($('#adapter-id').val());
        update_tgturl();
});

// TODO Remove from workspace is badly broken
$('#remove-btn').click(function() {     // Remove item button listener
  remove($('#adapter-id').val())
});

$('#clone-btn').click(function() {      // Clone item button listener
  clone($('#adapter-id').val())
});

$('#save-btn').click(function() {       // Save workspace button listener
  save()
});

$("#files").change(util.handleFileSelect);   // File upload listener

$("#connector-items-lst").on("click change", function() {   // Connector source listener
  $("#connector-sub_items-lst").find("option").remove().end();
  var name = $(this).val();
  var values = [];
  adapter_items.filter(function(obj) {
    if(name == obj.name) {
      values = obj.columns;
    }
  });
  values.map(function(value) {
    $("#connector-sub_items-lst")
        .append('<option value="' + name + '.' + value + '">' +
                value + '</option>');
  });
});

$("#connector-sub_items-lst").on("click", // Connector sub-items listener
  function() {
    var selected_sub_item = $(this).val();
    console.log(selected_sub_item);
});

$("#connector-out_items-lst").on("click", // HL7 segments chooser listener
  function() {
    var selected_segment = $(this).val().pop();
    //build_hl7json(selected_segment);
});



/// To execute onLoad() TODO temporary since alt-layout
// Add adapter TODO its temporary since alt-layout
addToDiagram(generic_adapter);
connect_queue(adapters[2]);
connect_queue(finish);
addToDiagram(connector);
