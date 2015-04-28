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

var get_objects = function(url) {
  //console.log('Fetching objects...');
  $.get("/api/adapter/object/",
        {"__anti-forgery-token": $("#__anti-forgery-token").val(),
        "url": url},
        function(e) {
          console.log(e);
        }
        );
};

var test_connection = function (url) {
  $.get("/api/adapter/test/",
        {"__anti-forgery-token": $("#__anti-forgery-token").val(),
          "url": url},
       function(e) {
        console.log('Adapter Test OK');
       // get_objects(url);
       }
       );
};
