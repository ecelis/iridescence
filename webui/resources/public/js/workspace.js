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
var paper = Raphael("work-canvas", paperW, paperH);  // Workspace

/**
* Workspace Metadata
* @property
*/
var work_meta = {        // Workspace metadata
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
  'items': null,
  'from': null,
  'query': null
};

var adapter_items = [];               // Adapter Items from source
// Global settings
var connections = [];                 // Connections between adapters
var connect = [];                     // Temporary queue for connections
var adapters = paper.set();           // Create a default adapters
var work_guid = util.guid();          // Generate adapters GUID
// We'll create adapters based on generic_adapter
// TODO avoid cloning in alt-layout branch
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

var connector_tab = $('#properties a[href="#connector"]');
var adapter_tab = $('#properties a[href="#adapter"]');
var adapter_tab_fields = $('#adapter :input');

/**
 * Connect queue, Adapters are pushed to the queue in order to add a Connector
 * between them
 * @method
 * @param {Object} artifact connector
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
};

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
};

/**
 * Triggered when a adapter stops moving
 */
var up = function() {
  this.animate({"fill-opacity": 0}, 500);
};

/**
 * Triggers when an element from Toolbar is dragged into adapters
 * @method toolUp
 */
var release = function() {
  this.attr("x", tbX + 5);
  this.attr("y", tbY + 5);
  addToDiagram(this);
};

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
    adapter_tab.tab('show');
    connect_queue(this);
  } else {
    // TODO initialize properties, Name, etc
    connector_tab.tab('show');
    var adapter_id = property.name.split("to")[0];
    update_connector(this);
  }
};

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
 * @param {Object} adapter Raphael Element
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
                    "items": null,
                    "from": null,
                    "query": null})
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
 * @method clone
 * @param {Integer} id of the Raphael element
 * */
var clone = function(id) {
  addToDiagram(paper.getById(id));
}

var build_query = function(event, node) {
  var nodes = $('#srcdata').treeview('getSelected', node.nodeId);
  var col_names = '';
  var table_names = '';
  nodes.forEach(function(column) {
    table_names += $('#srcdata').treeview('getParent', column).text + ' ';
    col_names += $('#srcdata').treeview('getParent', column).text +
                          '.' + column.text + ' ';
  });
  $('#adapter-from').val(table_names);
  $('#adapter-query').val(col_names);
};

var srcdata_treview = function() {
  $('#srcdata').treeview({data: srcdata,
    multiSelect: true,
    onNodeSelected: build_query,
    onNodeUnselected: build_query
  });
};

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
  adapter.data("props").from = $('#adapter-from').val();
  adapter.data("props").query = $('#adapter-query').val();
  adapter.attr({'title': adapter.data("props").name,
          'text':adapter.data("props").name});
  test_connection($('#adapter-url').val());
  if(adapter_items.length > 0) {
    adapter.data("props").items = adapter_items;
    srcdata_treview();
  }
}

/**
 * Update work properties
 * @method
 */
var update_workspace = function() {
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
};

// TODO Check if the values aren't overwriten when refreshing webpage
$('#work-guid').val(work_guid);                   // Workspace GUID field
$('#work-guid-label').html("Id: " + work_guid);   // Workspace GUID label

connector.click(function() {                      // Connector onClick listener
  addToDiagram(this)
});

finish.click(modify); // Finish adapter onDrag

$('#msg-template-lst li a').on('click change',
      function() {
        var template = $(this).text().toUpperCase();
        $('#msg-template-btn').html(template + '<span class="caret"></span>');
        // TODO Assign the template
        $('#msg-template').treeview({data: sample,
        multiSelect: true});
});

$('#workspace :input').on("click change keyup", // Workspace properties listener
      function() {
        update_workspace();
});

var src_driver, src_host, src_src, src_user, src_password, src_url;
var tgt_driver, tgt_host, tgt_message, tgt_user, tgt_password, tgt_url;

/**
 * Fill the URL for adapters
 *
 * @method
 */
var update_srcurl = function() {
  src_host = $('#adapter-host').val();
  src_src = $('#adapter-source').val();
  src_user = $('#adapter-user').val();
  src_password = $('#adapter-password').val();
  src_url = src_driver + "://" + src_host + "/" + src_src;
  // TODO use Array for URL parameters
  if(src_user != null) {
    src_url += "?user=" + src_user + "&password=" + src_password;
  }
  $('#adapter-url').val(src_url);
};

/**
 * Fill the URL for connectors
 * @method
 */
var update_tgturl = function() {
  tgt_host = $('#connector-host').val();
  tgt_target = $('#connector-target').val();
  tgt_user = $('#connector-user').val();
  tgt_password = $('#connector-password').val();
  tgt_url = tgt_driver + "://" + tgt_host + "/" + tgt_target;
  // TODO use Array for URL parameters
  if(tgt_user != null) {
    tgt_url += "?user=" + tgt_user + "&password=" + tgt_password;
  }
  $('#adapter-url').val(tgt_url);
};

/**
 * Fill the adpter's driver dropdown
 *
 * @method
 * @param {String} src_type
 */
var fill_adapter_driver = function(src_type) {
  $("#adapter-driver-lst").find("li").remove().end();
  var items = [];
  util.srctype[src_type].forEach(function(item) {
    items.push('<li><a href="#">'+item+'</a></li>');
  });
  $('#adapter-driver-lst').append(items.join(''));
  $('#adapter-driver-lst li a').on("click change",
      function() {
        src_driver = $(this).text().toLowerCase().replace(' ','');
        $('#btn-adapter-driver').html(src_driver + '<span class="caret"></span>');
        update_adapter($('#adapter-id').val());
      });
};

var fill_adapter_types = function() {
  var items = [];
  for(var key in util.srctype) {
    items.push('<li><a href="#">'+key+'</a></li>');
  };
  $('#adapter-type-lst').append(items.join(''));
  $('#adapter-type-lst li a').on('click change',
      function() {
        var src_type = $(this).text();
        $('#btn-adapter-type').html(src_type + '<span class="caret"></span>');
        fill_adapter_driver($(this).text());
        update_adapter($('#adapter-id').val());
      });
};

var fill_connector_driver = function(tgt_type) {
  $('#connector-driver-lst').find('li').remove().end();
  var items = [];
  util.tgttype[tgt_type].forEach(function(item) {
    items.push('<li><a href="#">'+item+'</a></li>');
  });
  $('#connector-driver-lst').append(items.join(''));
  $('#connector-driver-lst li a').on("click change",
        function() {
          tgt_driver = $(this).text().toLowerCase().replace(' ','');
          $('#btn-connector-driver').html(tgt_driver + '<span class="caret"></span>');
          // TODO update_connector(adapters[2]);
  });
};

var fill_connector_types = function() {
  var items = [];
  for(var key in util.tgttype) {
    items.push('<li><a href="#">'+key+'</a></li>');
  };
  $('#connector-type-lst').append(items.join(''));
  $('#connector-type-lst li a').on('click change',
    function() {
      var tgt_type = $(this).text();
      $('#btn-connector-type').html(tgt_type+'<span class="caret"></span>');
      fill_connector_driver($(this).text());
      // TODO update_connector($('#connector-id').val());
    });
};

/*adapter_tab_fields.on('change', function() {
  update_adapter($('#adapter-id').val());
});*/

$('#adapter-test-btn').on("click change keyup",
      function() {
        update_srcurl();
        update_adapter(adapters[2].id); // TODO fixed value since alt-layout
        if($('#adapter-query').val() != '') {
          build_select();
        }
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

// TODO change this to work for workspace
$('#clone-btn').click(function() {      // Clone item button listener
  clone($('#adapter-id').val())
});

$('#save-btn').click(function() {       // Save workspace button listener
  save()
});

$('#up').click(function() {
  build_select();
});
//$("#files").change(util.handleFileSelect);   // File upload listener

connector_tab.on('show.bs.tab', function(e) {
  console.log(e);
});

/// To execute onLoad() TODO temporary since alt-layout
// Add adapter TODO its temporary since alt-layout
addToDiagram(generic_adapter);
connect_queue(adapters[2]);
connect_queue(finish);
addToDiagram(connector);

// Keep it in the bottom
$(document).ready(function() {
  fill_adapter_types();
  fill_connector_types();
});

