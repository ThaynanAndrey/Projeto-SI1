angular.module("adExtreme")

.service('RestService', function(HttpRequestService) {
	var restFactory = {};

	restFactory.find = function(url, callback) {
		HttpRequestService(url, "GET", {}, callback);
	};

	restFactory.add = function(url, data, callback) {
		HttpRequestService(url, "POST", data, callback);
	};

	restFactory.edit = function(url, data, callback) {
		HttpRequestService(url, "PUT", data, callback);
	};

	restFactory.delete = function(url) {
		HttpRequestService(url, "DELETE", null, null);
	};

	return restFactory;
});