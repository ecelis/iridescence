/**
 *   HL7 JSON builder
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

var SEGMENT = '\r';
var FIELD = '|';
var COMPONENT = '^';
var FIELDREPEAT = '~';
var ESCAPE = '\\';
var SUBCOMPONENT = '&';
var seg_types = [];

var set_field = function(name, value) {
  switch(name) {
    case "seq":
      this.seq = value;
      break;
    case "len":
      this.len = value;
      break;
    case "clen":
      this.clen = value;
      break;
    case "dt":
      this.dt = value;
      break;
    case "opt":
      this.opt = value;
      break;
    case "rp":
      this.rp = value;
      break;
    case "tbl":
      this.tbl = value;
      break;
    case "item":
      this.item = value;
      break;
    case "element_name":
      this.element_name = value;
      break;
  }
  var isSegment = seg_types.forEach(function(segment) {
    if(name == segment.type)
      return segment.type;
  });
  if(isSegment = name) {
    return isSegment;
  } else {
    return {name: value};
  }
};

var Segment = function() {
  Segment.prototype.set_field = set_field;
};
Segment.prototype.type = null;

var MSH = new Segment();
MSH.type = 'MSH';
seg_types.push(MSH);
var NTE = new Segment();
NTE.type = 'NTE';
seg_types.push(NTE);
var OBR = new Segment();
OBR.type = 'OBR';
seg_types.push(OBR);
var OBX = new Segment();
OBX.type = 'OBX';
seg_types.push(OBX);
var PID = new Segment();
PID.type = 'PID';
seg_types.push(PID);

var message;        // Message

var construct_occurrence = function(occurrence) {
  // gather populated components
  var components = [];
  components.forEach(function(component){
    var subcomponents = get_subcomponent_data(component);
    // gather all data for subcomponents
    subcomponents.forEach(function(subcomponent) {
      // TODO
      // escape the field separator
      // substitute( field_separator,        \F\ );
			// escape the encoding characters
			// substitute( component_separator,    \S\ );
			// substitute( repetition_separator,   \R\ );
			// substitute( escape_character,       \E\ );
			// substitute( subcomponent_separator, \T\ );
      // insert subcomponent;
			if(subcomponent == null) {
        //insert    subcomponent_separator;     /*   e.g., &   */
      }
      if(component == null) {
        // insert component_separator;               /*   e.g., ^   */
      }
    });
  });
  // return;
}

var construct_message = function(data) {
  segment_list = [];
  // identidy_message_needed();
  // identify_separators_used();
  // validate(data);
  // order_segments(data, segment_list);
  segment_list.forEach(function(segment) {
    var fields = [];
    fields.push(segment);     // Insert segment name
    // Gather all data for fields
    fields.forEach(function(field) {
      // TODO insert field separator
      // Gather occurrences // May be multiple only for fields that are allowed to repeat
      field.forEach(function(occurences){
        construct_occurrence(occurences);
        if(true) {
          // TODO insert repetition_separator /* e.g. ~ */
        }
        // TODO break if last (populated field)
      });
    });
    // TODO insert segment_terminator
  });
  // return
}

//main module that exports all other sub modules
//http://python-hl7.readthedocs.org/en/latest/accessors.html


//Message[segment][field][repetition][component][sub-component]
function parseComponent(data) {
    var result = [];
    var subcomponents = data.split(SUBCOMPONENT);

    var s;
    if (subcomponents.length === 1) {
        s = subcomponents[0];
        result = s;

    } else {

        for (var i = 0; i < subcomponents.length; i++) {
            s = subcomponents[i];
            result.push(s);
        }
    }

    return result;
}

function parseRepeat(data) {
    var result = [];
    var components = data.split(COMPONENT);

    var c;
    if (components.length === 1000) {
        c = parseComponent(components[0]);
        result = c;

    } else {
        for (var i = 0; i < components.length; i++) {
            c = parseComponent(components[i]);
            result.push(c);
        }
    }

    return result;
}

function parseField(data) {
    var result = [];
    var repeats = data.split(FIELDREPEAT);

    for (var i = 0; i < repeats.length; i++) {
        var r = parseRepeat(repeats[i]);
        result.push(r);
    }

    return result;
}

function parseSegment(data) {
    var result = {};
    var fields = data.split(FIELD);

    //var seg_name = fields[0];

    result = [];
    var start = 0;

    //adjusting header segment, inserting | as first field
    if (fields[0] === "MSH") {
        fields[0] = FIELD;
        fields = ["MSH"].concat(fields);

        //ignore MSH1 and MSH2
        start = 3;

        result.push("MSH"); //segment name
        result.push(FIELD); //pipe
        result.push(fields[2]); //separators
    } else {
        result.push(fields[0]); //segment name

        start = 1;
    }

    for (var i = start; i < fields.length; i++) {
        //skip empty fields
        //if (fields[i] === "") continue;

        var f = parseField(fields[i]);
        result.push(f);
    }

    return result;
}

function parse(data) {
    //MSH check
    if (data.substr(0, 3) !== 'MSH') {
        //TODO: throw a proper error here
        return null;
    }

    //define field separator from MSH header
    FIELD = data[3];

    //define all other separators from MSH header
    COMPONENT = data[4];
    FIELDREPEAT = data[5];
    ESCAPE = data[6];
    SUBCOMPONENT = data[7];

    //parse into result object
    var result = [];

    var segments = data.split(SEGMENT);
    for (var i = 0; i < segments.length; i++) {
        if (segments[i] === "") {
            continue;
        }
        var seg = parseSegment(segments[i]);

        result.push(seg);
    }

    return result;

}

function parseString(data, options) {
    //data must be a string
    if (!data || typeof (data) !== "string") {
        //TODO: throw a proper error here
        return null;
    }

    if (arguments.length === 1) {
        options = {};
    }

    data = parse(data);

    return data;
}

//Message[segment][field][repetition][component][sub-component]
function serializeComponent(data) {
    if (typeof (data) === "string") {
        return data;
    }
    return data.join(SUBCOMPONENT);
}

function serializeRepeat(data) {
    if (typeof (data) === "string") {
        return data;
    }
    return data.map(serializeComponent).join(COMPONENT);
}

function serializeField(data) {
    if (typeof (data) === "string") {
        return data;
    }
    return data.map(serializeRepeat).join(FIELDREPEAT);
}

function serializeSegment(data) {
    if (typeof (data) === "string") {
        return data;
    }

    //handling header in special way...
    if (data[0] === "MSH") {
        //clone MSH array, since we are modifying it to remove separator elements
        var msh = JSON.parse(JSON.stringify(data));
        msh.shift();
        msh.shift();
        return "MSH" + FIELD + msh.map(serializeField).join(FIELD);
    }

    return data.map(serializeField).join(FIELD);
}

function serialize(data) {
    return data.map(serializeSegment).join(SEGMENT);
}

function serializeJSON(data, options) {
    if (arguments.length === 1) {
        options = {};
    }

    var result = serialize(data);

    return result;
}

function translateSegment(segment) {

    try {
        var seg_name = segment[0];

        var format = fs.readFileSync(path.dirname(fs.realpathSync(__filename)) +
                                     "/segments/" + seg_name + ".txt").toString();

        format = format.split("\n");

        var data = {};
        data["Segment"] = segment[0];

        for (var i = 1; i < Math.min(segment.length, format.length - 1); i++) {
            var field_name = format[i + 1].split("\t")[5];
            data[field_name] = segment[i];
        }

        return data;
    } catch (ex) {
        console.log(ex); //for debug
    }
}

function translate(data) {
    var tr = [];
    for (var seg in data) {
        tr.push(translateSegment(data[seg]));
    }

    return tr;
}
