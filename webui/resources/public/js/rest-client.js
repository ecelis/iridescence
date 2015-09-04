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

var srcdata, tplsrc;

var leaf = function()
{
  this.text = '';
  this.nodes = [];
};

/**
 * Save adapters to YAML in the server
 * @method save
 * */
var save_workspace = function()
{
  var payload = {'meta': null, 'data': []};
  payload.meta = JSON.stringify(work_meta);
  adapters.forEach(function(adapter) {
    payload.data.push(JSON.stringify(adapter.data("props")));
  });
  $.post("/api/",
     {"__anti-forgery-token": $('#__anti-forgery-token').val(),
       "workspace":payload
     },
    function(res) {
      $("#info-box").fadeIn("slow");
      $("#info-box").html("Workspace " + $("#work-name").val() + " saved");
      $("#info-box").fadeOut("slow");
    });
};

/**
 * Wrap element in draggable span
 *
 * @method drag_wrapper
 * @param String string
 */
var drag_wrapper = function(string)
{
  return '<span class="draggable ui-widget-content ui-draggable">' +
    string + '</span>';
};

/**
 * Handles JSON response for HL7v2 adapter, bootstrap-treeview ready
 *
 * @method hl7v2_handler
 * @param {Object} json_data response
 */
var hl7v2_handler = function(json_data)
{
  var tree = [];
  json_data['template'].forEach(function(item)
  {
    var a_leaf = Object.create(leaf.prototype);
    a_leaf.text = item.id;
    a_leaf.nodes = [];
    item.fields.forEach(function(field)
    {
      var b_leaf = Object.create(leaf.prototype);
      var isArray = field.content instanceof Array;
      if(isArray) {
        if(field.content.length > 1) {
          b_leaf.text = field.content.join('^');
          b_leaf.nodes = typeof b_leaf.nodes === 'undefined' ? [] : b_leaf.nodes;
          field.content.forEach(function(comp)
                       {
                        b_leaf.nodes.push({text: comp});
                       });
        } else {
          b_leaf.text = typeof field.content[0] !== 'undefined' ? field.content[0] : '';
        }
      } else {
        b_leaf.text = field.content;
      }
      a_leaf.nodes.push(b_leaf);
    });
    tree.push(a_leaf);
  });
  return [{text: 'template', nodes: tree}];
};

/**
 * Handles JSON response for CSV adapter
 * TODO handle first row column names
 *
 * @method csv_handler
 * @param {Object} json_data response
 */
var csv_handler = function(json_data)
{
  var matrix = [];
  var row_number = 0;
  json_data.matrix.forEach(function(row) {
    var cells = [];
    row.forEach(function(cell) {
      cells.push({text: cell});
    });
    matrix.push({text: row_number.toString(), nodes: cells});
    row_number++;
  });
  return [{text: "src", nodes: matrix}];
};

/**
 * Handles JSON response for RDMS
 *
 * @method db_handler
 * @param {Object} json_data response
 */
var db_handler = function(json_data) {
  var table_schema = [];
  var table_definition = [];
  // Get table names
  if(json_data.tables != null) {
    json_data.tables.forEach(function(item){
      table_schema.push({
        "text": Object.getOwnPropertyNames(item)[0],
        "nodes":item.valueOf()
      });
    });
    // Get columns and return associative array of tables:columns
    table_schema.map(function(table){
      var cols = [];
      table.nodes[table.text].map(function(column){
        cols.push({
          text: drag_wrapper(column.column_name)});
      });
      table_definition.push({"text": table.text, "nodes": cols});
    });
    return [{text: "tables", nodes: table_definition}];
  }
};

/**
 * Stores in a variable the Table Definition retrieved by test_connection
 *
 * @method adapter_connection_handler
 * @param {String} url Adapters URL
 * @param {Object} json_data response
 */
var adapter_connection_handler = function(url, json_data)
{
  var proto = new URI(url).scheme();
  switch(proto) {
    case "postgres":
    case "postgresql":
    case "mysql":
      srcdata = db_handler(json_data);
      break;
    case "csv":
      srcdata = csv_handler(json_data);
      break;
    default:
      console.log("Empty response");
  }
};

/**
 * POST file
 */
var file_upload_handler = function ()
{
  // Change this to the location of your server-side upload handler:
  var url = context + '/upload';
  $.post(context + "/api/upload",
     {"__anti-forgery-token": $("#__anti-forgery-token").val(),
       "file": $('#file').val()
     },
     function(res)
     {
       console.log(res);
     }
  );
};

/**
 * GET HL7 Template
 */
var get_template = function(id)
{
  id = typeof id !== 'undefined' ? id : '';
  $.get('/api/template/' + id,
    {'__anti-forgery-token': $('#__anti-forgery-token').val()},
    function(res)
    {
      if(id !== '') {
        tplsrc = hl7v2_handler(res);
        tplsrc_treeview();
      } else {
        fill_templates(res['template']);
      }
    });
};

/**
 * GET Data Source Schema
 *
 * @method test_connection
 * @param {String} url
 */
var test_connection = function (url)
{
  $.get("/api/adapter/test/",
    {"__anti-forgery-token": $("#__anti-forgery-token").val(),
      "url": url
    },
    function(res) {
      adapter_connection_handler(url, res);
    });
};

/**
 * GET Data From Source
 */
var build_select = function()
{
  $.get("/api/adapter/build_select/",
    {"__anti-forgery-token": $("#__anti-forgery-token").val(),
      "url": $('#adapter-url').val(),
      "tables": $('#adapter-from').val(),
      "query": $('#adapter-query').val()
    },
   function(res) {
    console.log(res);
   });
};
