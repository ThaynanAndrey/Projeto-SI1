angular.module("adExtreme")

.service('RestService', function(HttpRequestService) {
	var restFactory = {};
	
	var urlInicial = "https://ad-extreme-projeto.herokuapp.com";
		
	restFactory.find = function(url, callback) {
		HttpRequestService(urlInicial + url, "GET", {}, callback);
	};

	restFactory.add = function(url, data, callback) {
		HttpRequestService(urlInicial + url, "POST", data, callback);
	};

	restFactory.edit = function(url, data, callback) {
		HttpRequestService(urlInicial + url, "PUT", data, callback);
	};

	restFactory.delete = function(url,callback) {
		HttpRequestService(urlInicial + url, "DELETE", {}, callback);
	};

	return restFactory;
});