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

var util = new Util();    // Utilities such as guid generator and crypto

/**
* Workspace Metadata
*
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

// Global settings
var work_guid = util.guid();          // Generate adapters GUID
var connector_tab = $('#properties a[href="#connector"]');
var adapter_tab = $('#properties a[href="#adapter"]');
var adapter_tab_fields = $('#adapter :input');
var treeview_events = 'nodeChecked nodeCollapsed nodeDisabled nodeEnabled nodeExpanded nodeSelected nodeUnchecked nodeUnselected searchComplete searchCleared';

//var tree_events = 'nodeChecked nodeCollapsed nodeDisabled nodeEnabled nodeExpanded nodeSelected nodeUnchecked nodeUnselected searchComplete searchCleared';
// TODO Check if the values aren't overwriten when refreshing webpage
$('#work-guid').val(work_guid);                   // Workspace GUID field
$('#work-guid-label').html("Id: " + work_guid);   // Workspace GUID label

$('#msg-template-lst li a').on('click change',
  function()
  {
    var template = $(this).text().toUpperCase();
    $('#msg-template-btn').html(template + '<span class="caret"></span>');
    // TODO Assign the template
    $('#msg-template').treeview({data: sample,
    multiSelect: true});
  }
);

$('#workspace :input').on("click change keyup", // Workspace properties listener
  function()
  {
    update_workspace();
  }
);

var src_driver, src_host, src_src, src_user, src_password, src_url;
var tgt_driver, tgt_host, tgt_message, tgt_user, tgt_password, tgt_url;

var build_query = function(event, node)
{
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

/**
 * Update the adapter properties data 'props' with values from
 * the properties panel
 *
 * @method update_adapter
 * */
var update_adapter = function()
{
  // TODO Fix it, values get borked in the panel
  adapter.type = $('#adapter-type').val();
  adapter.name = $('#adapter-name').val();
  adapter.url = $('#adapter-url').val();
  adapter.from = $('#adapter-from').val();
  adapter.query = $('#adapter-query').val();
  srcdata_treview();
};

/**
 * Update work properties
 *
 * @method update_workspace
 */
var update_workspace = function()
{
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
 * Build Adapter URL
 *
 * @method update_srcurl
 */
var update_srcurl = function()
{
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
 * Build the Connector URL
 *
 * @method update_tgturl
 */
var update_tgturl = function()
{
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

var fill_templates = function(templates)
{
  var items = [];
  templates.forEach(function(item)
  {
    items.push('<li><a href="#">' + item + '</a></li>');
  });
  $('#msg-template-lst').find('li').remove().end();
  $('#msg-template-lst').append(items.join(''));
  $('#msg-template-lst li a')
    .on('click change keyup',function()
     {
       $('#msg-template-btn')
        .html($(this).text() + '<span class="caret"></span>');
       get_template($(this).text());
     });
};

/**
 * Populate Adapter's JDBC Dropdown
 *
 * @method fill_adapter_driver
 * @param {String} src_type
 */
var fill_adapter_driver = function(src_type)
{
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

/**
 * Populate Adapter's Type Dropdown
 *
 * @method fill_adapter_types
 */
var fill_adapter_types = function()
{
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

/**
 * Populate Connector JDBC Dropdown
 *
 * @method fill_connector_driver
 */
var fill_connector_driver = function(tgt_type) {
  $('#connector-driver-lst').find('li').remove().end();
  var items = [];
  util.tgttype[tgt_type].forEach(function(item) {
    items.push('<li><a href="#">'+item+'</a></li>');
  });
  $('#connector-driver-lst').append(items.join(''));
  $('#connector-driver-lst li a').on("click change",
  function()
  {
    tgt_driver = $(this).text().toLowerCase().replace(' ','');
    $('#btn-connector-driver').html(tgt_driver + '<span class="caret"></span>');
    // TODO update_connector(adapters[2]);
  });
};

/**
 * Populate Connector Type Dropdown
 *
 * @method fill_connector_types
 */
var fill_connector_types = function()
{
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

var makeDraggable = function()
{
  $('.treeview ul li.node-srcdata span.draggable').parent().draggable({
    cursor: 'move',
    containment: 'window',
    helper: 'clone',
    appendTo: 'body',
    revert: true,
    zIndex: 255
  });
};

/**
 * Make parent element of class droppable, well... droppable
 * @method makeDroppable
 */
var makeDroppable = function()
{
  $('.treeview ul li.node-template span.droppable').parent().droppable(
    {
    drop: function(event, ui)
    {
      $(this).addClass("ui-state-highlight");
      console.log($(this));
      console.log($(this).context.textContent +
                  ' ' + ui.helper.context.textContent);
      tpl_clone = $.extend(true, tpl_clone, tplsrc);
      console.log(tpl_clone);
    }
  });
};

/**
 * Populate Template Treeview
 *
 * @method tplsrc_treeview
 */
var tplsrc_treeview = function()
{
  $('#template').treeview({
    data: tplsrc,
    levels: 16,
    multiSelect: false,
    state: { expanded: true}
  });
  makeDroppable();
  $('#template').on(treeview_events, function(event, node)
                   {
                     makeDroppable();
                   });
};

/**
 * Populate source data treeview
 *
 * @method srcdata_treeview
 */
var srcdata_treview = function()
{
  $('#srcdata').treeview({
    data: srcdata,
    levels: 16,
    multiSelect: false,
    state: {
      expanded: true
    }
  });
  makeDraggable();
  $('#srcdata').on(treeview_events, function(event, node)
   {
     makeDraggable();
   });
};

/**
 * Search srcdata_treeview
 *
 * @method search_srcdata_treeview
 */
var search_srcdata_treeview = function()
{
  var pattern = $('#input-srcdata-search').val();
  var options = {
    ignoreCase: true,
    exactMatch: false,
    revealResults: true
  };
  var results = $('#srcdata').treeview('search', [pattern, options]);
  var htmlOut;
  results.forEach(function(column)
                  {
                    htmlOut += column.text;
                  });
  $('#to_map').html(results);
};

$('#search-srcdata-btn').on('click', search_srcdata_treeview);

$('#clear-srcdata-search').on('click',
  function()
  {
    $('#srcdata').treeview('clearSearch');
    $('#input-srcdata-search').val('');
    $('#srcdata').treeview('collapseAll');
  }
);

$('#adapter-test-btn').on("keyup",
  function()
  {
    update_srcurl();
    update_adapter();
    if (adapter.url != null) {
      test_connection(adapter.url);
    }
    /* TODO
    if($('#adapter-query').val() != '') {
      build_select();
    }*/
  });

$('#connector :input').on("click change keyup",   // Connector properties listener
  function()
  {
    // TODO update_adapter($('#adapter-id').val());
    update_tgturl();
  });

$('#save-btn').click(function()        // Save workspace button listener
{
  save_workspace();
});

///////////////////////////////////////////////////////////////////////////////
// KEEP AT BOTTOM OF THE FILE
$(document).ready(function() {
  fill_adapter_types();
  fill_connector_types();
  get_template();
});

