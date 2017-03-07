angular.module("adExtreme")

.service('HttpRequestService', function($http) {

	return function(url, method, data, callback) {
		var requestParams = {
			method: method,
			url: url,
			headers: { 'Content-type': 'application/json'},
			data: data
		};

		$http(requestParams).then(
			function successCallback(response) {
				callback && callback(response);
			},

			function errorCallback(response) {

			}
		);
	};

});