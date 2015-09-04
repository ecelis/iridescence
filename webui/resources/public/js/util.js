// Copyright and licenses can vary for each snippet of code

var Util = function() {};

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
 // 'Channel Reader': [],
 // 'DICOM Listener': [],
  'Database Reader': db_driver,
  'File Reader': file_driver,
 // 'HTTP Listener': [],
 // 'JMS Listener': [],
 // 'JavaScript Reader': [],
 // 'TCP Listener': [],
 // 'Web Service Listener': []
};

Util.prototype.tgttype = {
 // 'Channel Writer': [],
 // 'DICOM Sender': [],
  'Database Writer': db_driver,
 // 'Document Writer': [],
  'File Writer': file_driver,
 // 'HTTP Sender': [],
 // 'JMS Sender': [],
 // 'SMTP Sender': [],
 // 'TCP Sender': [],
 // 'Web Service Sender': []
};

/**
 * Generate pseudo-random GUID
 * http://guid.us/GUID/JavaScript
 * then to call it, plus stitch in '4' in the third group
 * guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
 * alert(guid);
 *
 * @method S4
 */
function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}

/**
 * Generate pseudo-random GUID
 * http://guid.us/GUID/JavaScript
 * then to call it, plus stitch in '4' in the third group
 * guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
 * alert(guid);
 *
 * @method guid
 */
Util.prototype.guid = function() {
  return (S4() + S4() + "-" + S4() + "-4" +
   S4().substr(0,3) + "-" + S4() + "-" +
   S4() + S4() + S4()).toLowerCase();
}

/**
 * Check various HTML5 API's
 *
 * @method check_api
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

