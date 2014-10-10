var user = require('config/user');
var article = require('config/article');

module.exports = function(app) {
  app.get('/', function(req, res) {
    res.end("Node-Android-Project");
  });
  
  app.post('/addarticle', function(req, res) {
	  console.log("received file:\n" + JSON.stringify(req.files));
	  
	  var photoname = req.files.source.name;
	  var photopath = req.files.source.path;
	  var name = req.body.name;
	  var description = req.body.description;
	  var price = req.body.price;
	  
	  article.addArticle(photopath, photoname, name, description, price, function (found) {
		  res.json(found);
	  });
  });
  
  app.post('/login',function(req,res){
	  var email = req.body.email;
      var password = req.body.password;
    
      user.login(email, password, function (found) {
    	  console.log(found);
    	  
    	  res.json(found);
      });
  });
  
  app.post('/register',function(req, res){
    var email = req.body.email;
    var password = req.body.password;
    
    register.register(email, password ,function (found) {
    	console.log(found);
    	res.json(found);
    });
  });
  
  app.post('/api/chgpass', function(req, res) {
	  var id = req.body.id;
	  var opass = req.body.oldpass;
	  var npass = req.body.newpass;
	  
	  chgpass.cpass(id, opass, npass, function(found){
		  console.log(found);
		  res.json(found);
	  });
  });
  
  app.post('/api/resetpass', function(req, res) {
	  var email = req.body.email;
    
	  chgpass.respass_init(email, function(found){
		  console.log(found);
		  res.json(found);
	  });
  });
  
  app.post('/api/resetpass/chg', function(req, res) {
	  var email = req.body.email;
	  var code = req.body.code;
	  var npass = req.body.newpass;
	  
	  chgpass.respass_chg(email, code, npass, function(found){
		  console.log(found);
		  
		  res.json(found);
	  });
  });
  
  app.post('/getarticles', function(req, res) {	
	  article.getArticles(function(found) {
		  console.log(found);
		  
		  res.json(found);
	 });
  });
};