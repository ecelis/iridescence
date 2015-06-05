/**
 *   Iridescence Smart Connector Web UI RESTful Client
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
// TODO Unused it seems
var get_objects = function(url) {
  //console.log('Fetching objects...');
  $.get("/api/adapter/object/",
        {"__anti-forgery-token": $("#__anti-forgery-token").val(),
        "url": url},
        function(e) {
          console.log(e);
        });
};
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
  //payload.data.push(hl7message);
  $.post("/api/", {"__anti-forgery-token": $('#__anti-forgery-token').val(),
         "workspace":payload});
}

/**
 * Stores in a variable the Table Definition retrieved by test_connection
 *
 * @method adapter_connection_handler
 * @param {Object} JSON response
 */
var adapter_connection_handler = function(json_data) {
  var table_schema = [];
  var table_definition = [];
  // Get table names
  if(json_data.tables != null) {
    json_data.tables.forEach(function(item){
      table_schema.push({"text":Object.getOwnPropertyNames(item)[0],
        "nodes":item.valueOf()});
    });
    // Get columns and return associative array of tables:columns
    table_schema.map(function(table){
      var cols = [];
      table.nodes[table.text].map(function(column){
        cols.push({text: column.column_name});
      });
      adapter_items.push({"text": table.text, "nodes": cols});
    });
    var srcdata = [{text: "src", nodes: adapter_items}];
  }
  if(adapter_items.length > 0) {
    $('#srcdata').treeview({data: srcdata});
  }
  console.log(adapter_items);
};

/**
 * Makes AJAX requests to adapter source and fills workspace's adapter_items
 * global variable
 * @method test_connection
 * @param {String} url
 */
var test_connection = function (url) {
  $.get("/api/adapter/test/",
        {"__anti-forgery-token": $("#__anti-forgery-token").val(),
          "url": url
        },
        function(res) {
          adapter_connection_handler(res);
        });
};

var build_select = function() {
  $.get("/api/adapter/build_select/",
        {"__anti-forgery-token": $("#__anti-forgery-token").val(),
          "url": $('#adapter-url').val(),
          "query": $('#adapter-query').val()
        },
       function(res) {
        console.log(res);
       });
};
