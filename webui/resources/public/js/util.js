// Copyright and licenses can vary for each snippet of code

/**
 * Generate pseudo-random GUID
 * http://guid.us/GUID/JavaScript
 * then to call it, plus stitch in '4' in the third group
 * guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
 * alert(guid);
 */
function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}

var Util = function() {}

/**
 * Generate pseudo-random GUID
 * http://guid.us/GUID/JavaScript
 * then to call it, plus stitch in '4' in the third group
 * guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
 * alert(guid);
 *
 * @method
 */
Util.prototype.guid = function() {
  return (S4() + S4() + "-" + S4() + "-4" +
   S4().substr(0,3) + "-" + S4() + "-" +
   S4() + S4() + S4()).toLowerCase();
}

/**
 * Handle the input file
 * @method
 * @param {String} evt
 */
Util.prototype.handleFileSelect = function (evt) {
  evt.stopPropagation();
  evt.preventDefault();

  var files = evt.dataTransfer.files; // FileList object.

  // files is a FileList of File objects. List some properties.
  var output = [];
  for (var i = 0, f; f = files[i]; i++) {
    output.push('<li><strong>', escape(f.name), '</strong> (', f.type || 'n/a', ') - ',
                f.size, ' bytes, last modified: ',
                f.lastModifiedDate ? f.lastModifiedDate.toLocaleDateString() : 'n/a',
                '</li>');
  }
  document.getElementById('list').innerHTML = '<ul>' + output.join('') + '</ul>';
}

/**
 * Handle the drop zone
 * @method
 * @param {String} evt
 */
Util.prototype.handleDragOver = function(evt) {
  evt.stopPropagation();
  evt.preventDefault();
  evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
}

/**
 * Check various HTML5 API's
 * @method
 */
Util.prototype.check_api = function() {
  // Check for the various File API support.
  if (window.File && window.FileReader && window.FileList && window.Blob) {
     // Setup the dnd listeners.
    var dropZone = $('#drop_zone');
    dropZone.addEventListener('dragover', handleDragOver, false);
    dropZone.addEventListener('drop', handleFileSelect, false);
  } else {
    alert('The File APIs are not fully supported in this browser.');
  }
};

var file_driver = [
  'hl7v2',
  'hl7v3',
  'csv'
];

var db_driver = [
  'PostgreSQL',
  'MySQL',
  'SQL Server',
  'Oracle'
];


Util.prototype.srctype = {
  'Channel Reader': [],
  'DICOM Listener': [],
  'Database Reader': db_driver,
  'File Reader': file_driver,
  'HTTP Listener': [],
  'JMS Listener': [],
  'JavaScript Reader': [],
  'TCP Listener': [],
  'Web Service Listener': []
};

Util.prototype.tgttype = {
  'SMTP Sender': [],
  'Channel Writer': [],
  'DICOM Sender': [],
  'Database Writer': db_driver,
  'Document Writer': [],
  'File Writer': file_driver,
  'HTTP Sender': [],
  'JMS Sender': [],
  'SMTP Sender': [],
  'TCP Sender': [],
  'Web Service Sender': []
};

// From http://raphaeljs.com/graffle.html
Raphael.fn.connection = function (obj1, obj2, line, bg) {
    if (obj1.line && obj1.from && obj1.to) {
        line = obj1;
        obj1 = line.from;
        obj2 = line.to;
    }
    var bb1 = obj1.getBBox(),
        bb2 = obj2.getBBox(),
        p = [{x: bb1.x + bb1.width / 2, y: bb1.y - 1},
        {x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1},
        {x: bb1.x - 1, y: bb1.y + bb1.height / 2},
        {x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2},
        {x: bb2.x + bb2.width / 2, y: bb2.y - 1},
        {x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1},
        {x: bb2.x - 1, y: bb2.y + bb2.height / 2},
        {x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2}],
        d = {}, dis = [];
    for (var i = 0; i < 4; i++) {
        for (var j = 4; j < 8; j++) {
            var dx = Math.abs(p[i].x - p[j].x),
                dy = Math.abs(p[i].y - p[j].y);
            if ((i == j - 4) || (((i != 3 && j != 6) || p[i].x < p[j].x) && ((i != 2 && j != 7) || p[i].x > p[j].x) && ((i != 0 && j != 5) || p[i].y > p[j].y) && ((i != 1 && j != 4) || p[i].y < p[j].y))) {
                dis.push(dx + dy);
                d[dis[dis.length - 1]] = [i, j];
            }
        }
    }
    if (dis.length == 0) {
        var res = [0, 4];
    } else {
        res = d[Math.min.apply(Math, dis)];
    }
    var x1 = p[res[0]].x,
        y1 = p[res[0]].y,
        x4 = p[res[1]].x,
        y4 = p[res[1]].y;
    dx = Math.max(Math.abs(x1 - x4) / 2, 10);
    dy = Math.max(Math.abs(y1 - y4) / 2, 10);
    var x2 = [x1, x1, x1 - dx, x1 + dx][res[0]].toFixed(3),
        y2 = [y1 - dy, y1 + dy, y1, y1][res[0]].toFixed(3),
        x3 = [0, 0, 0, 0, x4, x4, x4 - dx, x4 + dx][res[1]].toFixed(3),
        y3 = [0, 0, 0, 0, y1 + dy, y1 - dy, y4, y4][res[1]].toFixed(3);
    var path = ["M", x1.toFixed(3), y1.toFixed(3), "C", x2, y2, x3, y3, x4.toFixed(3), y4.toFixed(3)].join(",");
    if (line && line.line) {
        line.bg && line.bg.attr({path: path});
        line.line.attr({path: path});
    } else {
        var color = typeof line == "string" ? line : "#000";
        return {
            bg: bg && bg.split && this.path(path).attr({stroke: bg.split("|")[0], fill: "none", "stroke-width": bg.split("|")[1] || 3}),
            line: this.path(path).attr({stroke: color, fill: "none"}),
            from: obj1,
            to: obj2
        };
    }
};

var sample = [
{
  text: 'MESSAGE',
         nodes: [
         {
          text: "MSH",
                nodes: [
                {text: "^~\\&"},
                {text: "SEDNA HOSPITAL, S.A. DE C.V."},
                {text: "NIMBOIMG"},
                {text: "201503241450"},
                {text: "ORM^O01"},
                {text: "Message_Control_ID"},
                {text: "P"},
                {text: "2.6"}
                  ]
         },
         {
          text: "PID",
          nodes: [
            {text: "1"},
            {text: "8888888"},
            {text: "XXXXXXX^XXXXXX^XXXXXXXXX"},
            {text: "MALDONADO"},
            {text: "888888888888"},
            {text: "F"},
            {text: "^XXXXX 88 XXXXXXXXXXX XXXXXXXXX^XXXXXX^XX^88888^XX"},
            {text: "88888888"}
          ]
         },
         {text: "PV1",
           nodes: [
             {text: "1"},
             {text: "I"},
             {text: "^URGENCIAS CONSULTORIO^CON. 01^URGENCIAS"},
             {text: "1002895"},
             {text: "20150324"}
           ]},
           {
            text: "ORC",
            nodes: [
              {text: "NW"},
              {text: "1006457"},
              {text: "IP"},
              {text: "^^^20150324143000^^^ROUTINE"},
              {text: "201503241430"},
              {text: "910057^ITURBE&FUENTES^ARMANDO^"},
              {text: "URGENCIAS"}
            ]},
            {
              text: "OBR",
              nodes: [
                {text: "1"},
                {text: "197537"},
                {text: "32980^CR Columna Cervical 1 Proyeccion"},
                {text: "20150324"}
            ]}
           ]
}
];
