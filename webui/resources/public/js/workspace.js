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

var util = new Util();    // Utilities wuch as guid generator and crypto
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

var adapter = {
  id: null,
  name: null,
  type: null,
  url: null,
  items: null,
  from: null,
  query: null
};

var adapter_items = [];               // Adapter Items from source
// Global settings
var connections = [];                 // Connections between adapters
var connect = [];                     // Temporary queue for connections
var work_guid = util.guid();          // Generate adapters GUID
// TODO  .data("props", {"type":"START", "name":"Start", "url":null});
//adapters.push(start);
//  .data("props", {"type":"FINISH", "name":"Finish", "url":null});
//adapters.push(finish);

var connector_tab = $('#properties a[href="#connector"]');
var adapter_tab = $('#properties a[href="#adapter"]');
var adapter_tab_fields = $('#adapter :input');

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
var update_adapter = function()
{
  // TODO Fix it, values get borked in the panel
  adapter.type = $('#adapter-type').val();
  adapter.name = $('#adapter-name').val();
  adapter.url = $('#adapter-url').val();
  adapter.from = $('#adapter-from').val();
  adapter.query = $('#adapter-query').val();

  if(adapter_items.length > 0) {
    adapter.items = adapter_items;
    srcdata_treview();
  }
};

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

// TODO Check if the values aren't overwriten when refreshing webpage
$('#work-guid').val(work_guid);                   // Workspace GUID field
$('#work-guid-label').html("Id: " + work_guid);   // Workspace GUID label

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
        var src_type = $(this).text(); // TODO useful?
        $('#adapter-type').val($(this).text());
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
    function()
    {
      var tgt_type = $(this).text();
      $('#btn-connector-type').html(tgt_type+'<span class="caret"></span>');
      fill_connector_driver($(this).text());
    });
};

$('#adapter-test-btn').on("click change keyup",
      function() {
        update_srcurl();
        update_adapter();
        if (adapter.url != null) {
          test_connection(adapter.url);
        }
        if($('#adapter-query').val() != '') {
          build_select();
        }
});

$('#connector :input').on("click change keyup",   // Connector properties listener
      function() {
        // TODO update_adapter($('#adapter-id').val());
        update_tgturl();
});

$('#save-btn').click(function()        // Save workspace button listener
{
  save();
});
/// To execute onLoad() TODO temporary since alt-layout
// Add adapter TODO its temporary since alt-layout

// Keep it in the bottom
$(document).ready(function() {
  fill_adapter_types();
  fill_connector_types();
});

