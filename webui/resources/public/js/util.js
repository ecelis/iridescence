// Copyright and licenses can vary for each snippet of code

// http://guid.us/GUID/JavaScript
// then to call it, plus stitch in '4' in the third group
// guid = (S4() + S4() + "-" + S4() + "-4" + S4().substr(0,3) + "-" + S4() + "-" + S4() + S4() + S4()).toLowerCase();
// alert(guid);
function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
}

var Util = function() {}

Util.prototype.guid = function() {
  return (S4() + S4() + "-" + S4() + "-4" +
   S4().substr(0,3) + "-" + S4() + "-" +
   S4() + S4() + S4()).toLowerCase();
}


