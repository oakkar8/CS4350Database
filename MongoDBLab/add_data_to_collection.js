var MongoClient = require('mongodb').MongoClient;
var url = "mongodb://localhost:27017/";

MongoClient.connect(url,{useNewUrlParser:true} ,function(err, db) {
  if (err) throw err;
  var dbo = db.db("DatabaseLab")
  var myobj = { name: process.argv[3], age:process.argv[4]  };
  dbo.collection(process.argv[2]).insertOne(myobj, function(err, res) {
    if (err) throw err;
    console.log("1 document inserted");
    db.close();
  });
});
