var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url, {useNewUrlParser: true },function(err, db) {
  if (err) throw err;
  var dbo = db.db("DatabaseLab");
  dbo.createCollection(process.argv[2], function(err, res) {
    if (err) throw err;
    console.log("Collection created!");
    console.log(process.argv[2]);
    db.close();
  });
});
